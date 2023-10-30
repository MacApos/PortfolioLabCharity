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
    <h2>Ustaw nowe hasło</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Nowe hasło" required="true" minLength="3" value="H@slo123"/>
            <form:errors path="password" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
        </div>
        <div class="form-group">
            <input type="password" name="password2" required placeholder="Powtórz hasło" value="H@slo123" />
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
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
</body>
</html>