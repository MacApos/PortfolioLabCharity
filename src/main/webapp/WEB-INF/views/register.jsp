<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header>
    <jsp:include page="headerUserPage.jsp"/>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate" cssClass="register-form">
        <div class="form-group">
            <form:input path="username" placeholder="Nazwa użytkownika" cssClass="form-control"
                        required="true" minLength="3" value="User"/>
            <form:errors path="username" element="div" cssClass="error-div"/>
            <c:if test="${not empty usernameExists}">
                <div class="error-div">
                        ${usernameExists}
                </div>
            </c:if>
            <div class="invalid-feedback">
                Podaj poprawną nazwę użytkownika.
            </div>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email" cssClass="form-control" required="true" minLength="3"
                        value="email@gmail.com"/>
            <form:errors path="email" element="div" cssClass="error-div"/>
            <c:if test="${not empty emailExists}">
                <div class="error-div">
                        ${emailExists}
                </div>
            </c:if>
            <div class="invalid-feedback">
                Podaj poprawny adres email.
            </div>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło"  required="true" minLength="3"
                        value="haslo"/>
            <form:errors path="password" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
        </div>
        <div class="form-group">
            <input type="password" name="password2" required placeholder="Powtórz hasło" value="haslo" />
            <c:choose>
                <c:when test="${not empty differentPasswords}">
                    <div class="invalid-feedback">
                            ${differentPasswords}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">Potwierdź hasło.</div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>