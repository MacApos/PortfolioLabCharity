<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header class="header--main-page">
    <jsp:include page="headerUserPage.jsp"/>
    <div class="slogan container container--85">
        <div class="slogan--item">
            <h2>
                Lista fundacji:
            </h2>
            <div class="text">
                <ol>
                    <c:forEach items="${institutions}" var="institution">
                        <li>
                                ${institution.name}<br>
                                ${institution.description}
                            <div class="form-group form-group--buttons">
                                <a href="${pageContext.request.contextPath}/manage-institution?id=${institution.id}"
                                   class="btn btn--highlighted" type="submit">Edytuj</a>
                                <a href="${pageContext.request.contextPath}/delete-institution?id=${institution.id}"
                                   class="btn btn--highlighted" type="submit">Usuń</a>
                            </div>
                        </li>
                    </c:forEach>
                    <div class="add-institution">
                        <a href="${pageContext.request.contextPath}/manage-institution"
                           class="btn btn--highlighted" type="submit">Dodaj</a>
                    </div>
                </ol>
            </div>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
