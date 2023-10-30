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
    <h2>Przypomnij hasło</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="email" placeholder="Email" cssClass="form-control" required="true" minLength="3"/>
            <form:errors path="email" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawny adres email.
            </div>
        <div class="form-group form-group--buttons">
            <button class="btn btn--highlighted" type="submit">Potwierdź</button>
        </div>
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
</body>
</html>