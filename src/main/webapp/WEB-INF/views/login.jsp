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
    <h2>Zaloguj się</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="email" placeholder="Email" cssClass="form-control" required="true"
            value="email@gmail.com"/>
            <c:if test="${not empty emptyEmail}">
                <div class="error-div">
                        ${emptyEmail}
                </div>
            </c:if>
            <c:if test="${not empty emailNotFound}">
                <div class="error-div">
                        ${emailNotFound}
                </div>
            </c:if>
            <div class="invalid-feedback">
                Podaj adres email.
            </div>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło" cssClass="form-control" required="true"
                        value="haslo"/>
            <c:if test="${not empty wrongPassword}">
                <div class="error-div">
                        ${wrongPassword}
                </div>
            </c:if>
            <div class="invalid-feedback">
                Podaj hasło.
            </div>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
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