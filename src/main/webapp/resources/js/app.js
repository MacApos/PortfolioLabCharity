document.addEventListener("DOMContentLoaded", function () {

        /**
         * Form Select
         */
        class FormSelect {
            constructor($el) {
                this.$el = $el;
                this.options = [...$el.children];
                this.init();
            }

            init() {
                this.createElements();
                this.addEvents();
                this.$el.parentElement.removeChild(this.$el);
            }

            createElements() {
                // Input for value
                this.valueInput = document.createElement("input");
                this.valueInput.type = "text";
                this.valueInput.name = this.$el.name;

                // Dropdown container
                this.dropdown = document.createElement("div");
                this.dropdown.classList.add("dropdown");

                // List container
                this.ul = document.createElement("ul");

                // All list options
                this.options.forEach((el, i) => {
                    const li = document.createElement("li");
                    li.dataset.value = el.value;
                    li.innerText = el.innerText;

                    if (i === 0) {
                        // First clickable option
                        this.current = document.createElement("div");
                        this.current.innerText = el.innerText;
                        this.dropdown.appendChild(this.current);
                        this.valueInput.value = el.value;
                        li.classList.add("selected");
                    }

                    this.ul.appendChild(li);
                });

                this.dropdown.appendChild(this.ul);
                this.dropdown.appendChild(this.valueInput);
                this.$el.parentElement.appendChild(this.dropdown);
            }

            addEvents() {
                this.dropdown.addEventListener("click", e => {
                    const target = e.target;
                    this.dropdown.classList.toggle("selecting");

                    // Save new value only when clicked on li
                    if (target.tagName === "LI") {
                        this.valueInput.value = target.dataset.value;
                        this.current.innerText = target.innerText;
                    }
                });
            }
        }

        document.querySelectorAll(".form-group--dropdown select").forEach(el => {
            new FormSelect(el);
        });

        /**
         * Hide elements when clicked on document
         */
        document.addEventListener("click", function (e) {
            const target = e.target;
            const tagName = target.tagName;

            if (target.classList.contains("dropdown")) return false;

            if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
                return false;
            }

            if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
                return false;
            }

            document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
                el.classList.remove("selecting");
            });
        });

        /**
         * Switching between form steps
         */
        class FormSteps {
            constructor(form) {
                this.$form = form;
                this.$next = form.querySelectorAll(".next-step");
                this.$prev = form.querySelectorAll(".prev-step");
                this.$step = form.querySelector(".form--steps-counter span");
                this.currentStep = 1;

                this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
                const $stepForms = form.querySelectorAll("form > div");
                this.slides = [...this.$stepInstructions, ...$stepForms];

                this.init();
            }

            /**
             * Init all methods
             */
            init() {
                this.events();
                this.updateForm();
            }

            /**
             * All events that are happening in form
             */
            events() {
                // Next step
                this.$next.forEach(btn => {
                    btn.addEventListener("click", e => {
                        const activeDiv = document.querySelector("div.active");
                        if (isInputValid(activeDiv)) {
                            e.preventDefault();
                            this.currentStep++;
                            this.updateForm();
                        }
                    });
                });

                // Previous step
                this.$prev.forEach(btn => {
                    btn.addEventListener("click", e => {
                        e.preventDefault();
                        this.currentStep--;
                        this.updateForm();
                    });
                });

                // Form submit
                this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
            }

            /**
             * Update form front-end
             * Show next or previous section etc.
             */
            updateForm() {
                this.$step.innerText = this.currentStep;

                // TODO: Validation

                this.slides.forEach(slide => {
                    slide.classList.remove("active");

                    if (slide.dataset.step == this.currentStep) {
                        slide.classList.add("active");
                    }
                });

                this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
                this.$step.parentElement.hidden = this.currentStep >= 5;

                // TODO: get data from inputs and show them in summary
                if (this.currentStep >= 5) {
                    const form = this.$form.querySelector("form");

                    const quantity = findInput("quantity");
                    const categories = findInput("categories");
                    let summary1 = `${quantity}`;
                    if (quantity < 2) {
                        summary1 += " worek, w którym znajdują się ";
                    } else {
                        summary1 += " worki, w których znajdują się ";
                    }
                    summary1 += categories;

                    form.querySelector("#summary1").innerText = summary1;
                    form.querySelector("#summary2").innerText = findInput("institution");
                    form.querySelector("#summary3").innerText = findInput("street");
                    form.querySelector("#summary4").innerText = findInput("city");
                    form.querySelector("#summary5").innerText = findInput("zipCode");
                    form.querySelector("#summary6").innerText = findInput("phone");
                    form.querySelector("#summary7").innerText = findInput("pickUpDate");
                    form.querySelector("#summary8").innerText = findInput("pickUpTime");

                    let pickUpComment = findInput("pickUpComment");
                    if (pickUpComment === "") {
                        pickUpComment = "Brak uwag";
                        form.querySelector("[name=pickUpComment]").value = pickUpComment;
                    }
                    form.querySelector("#summary9").innerText = pickUpComment;
                }
            }
        }

        const form = document.querySelector(".form--steps");
        if (form !== null) {
            new FormSteps(form);
        }

        function findInput(name) {
            const form = document.querySelector("form");
            const inputs = form.querySelectorAll(`[name='${name}']`);
            const values = []
            Array.from(inputs).forEach(function (input) {
                const type = input.getAttribute("type");
                if ((type === "checkbox" || type === "radio")) {
                    if (input.checked) {
                        values.push(input.id);
                    }
                } else {
                    values.push(input.value);
                }
            });
            if (values.length > 1) {
                const lastElement = values.splice(-1, 1);
                return values.join(", ").concat(" i ", lastElement[0]);
            }
            return values.join(", ");
        }

        function isInputValid(activeDiv) {
            const inputs = Array.from(activeDiv.querySelectorAll("input"));
            let inputValid = true;

            for (let i = 0; i < inputs.length; i++) {
                const input = inputs[i];
                const type = input.getAttribute("type");

                if ((type === "checkbox" || type === "radio")) {
                    let invalidFeedback = activeDiv.getElementsByClassName("invalid-feedback")[0];
                    invalidFeedback.classList.add("is-invalid");
                    inputValid = false;
                    if (input.checked) {
                        invalidFeedback.classList.remove("is-invalid");
                        inputValid = true;
                        break;
                    }
                } else {
                    input.classList.add("was-validated");
                    if (!input.checkValidity()) {
                        inputValid = false;
                    }
                }
            }
            return inputValid;
        }
    }
)