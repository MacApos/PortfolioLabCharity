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
    <jsp:include page="header.jsp"/>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="email" placeholder="Email" cssClass="form-control" required="true" minLength="3"
                        value="u1326546@gmail.com"/>
            <form:errors path="email" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podany adres email nie został znaleziony.
            </div>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Nowe hasło" required="false" minLength="3"
                        value="H@slo123"/>
            <form:errors path="password" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
            <a href="${pageContext.request.contextPath}/password-reset"
               class="btn btn--small btn--without-border password-reset">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
</body>
</html>