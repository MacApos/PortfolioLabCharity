document.addEventListener("DOMContentLoaded", function () {
    const formTest = document.querySelector("form#formTest");
    formTest.addEventListener("submit", function (event) {
        console.log(document.querySelector(".was-validated:invalid > .invalid-feedback"));
        if (!formTest.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        formTest.classList.add('was-validated');
    }, false)
});