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
    <div class="slogan container container--90">
        <div class="slogan--item">
            <div class="form-text">
                <ol>
                    <div class="wrapper">
                        <c:if test="${admins == null}">
                            <div>
                                Brak adminów.
                            </div>
                        </c:if>
                        <h2>
                            Lista adminów:
                        </h2>
                        <c:forEach items="${admins}" var="admin">
                            <li>
                                <div class="content">
                                    <div>
                                            ${admin.username}<br>
                                            ${admin.email}<br>
                                    </div>
                                    <div class="form-group form-group--buttons sidebar">
                                        <a href="${pageContext.request.contextPath}/edit-admin?id=${admin.id}"
                                           class="btn btn--highlighted" type="submit">Edytuj</a>
                                        <c:choose>
                                            <c:when test="${currentUser.id eq admin.id}">
                                                <button type="button" class="btn btn--highlighted" data-bs-toggle="modal"
                                                        data-bs-target="#exampleModal">
                                                    Usuń
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/delete-admin?id=${admin.id}"
                                                   class="btn btn--highlighted" type="submit">Usuń</a>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        <a href="${pageContext.request.contextPath}/add-admin" class="btn btn--highlighted"
                           type="submit">Dodaj</a>
                    </div>
                </ol>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Uwaga!</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Nie możesz usunąć uprawnień administratora użytkownikowi, który jest obecnie zalogowany.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>
