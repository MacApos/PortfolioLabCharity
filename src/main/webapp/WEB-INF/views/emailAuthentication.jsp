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
    <h2>Potwierdzenie adresu email</h2>
    <h3>Podaj kod przesłany na podany adares email, aby ustawić nowe hasło.</h3>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="token" placeholder="Kod autoryzacyjny" cssClass="form-control" required="true" minLength="3"/>
            <form:errors path="token" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawny kod.
            </div>
        <div class="form-group form-group--buttons">
            <button class="btn btn--highlighted" type="submit">Potwierdź</button>
        </div>
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
</body>
</html>