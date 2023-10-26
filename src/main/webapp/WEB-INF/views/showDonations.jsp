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
<table class="unstyledTable">
    <tbody>
    <tr>
        <td>cell1_1</td>
        <td>cell2_1</td>
    </tr>
    <tr>
        <td>cell1_2</td>
        <td>cell2_2</td>
    </tr>
    <tr>
        <td>cell1_3</td>
        <td>cell2_3</td>
    </tr>
    </tbody>
    </tr>
</table>
${donations[0]}
<c:set var="donationList" value="${['${donations[0]}', 'S','M','L','XL']}"/>


<c:forEach items='' var="donation">
    <li>
    ${donation.institution.name}<br>
            Oddaję:<br>
            <c:forEach items="${donation.categories}" var="category">
            <ul>
            <li>${category.name}</li>
            </ul>
            </c:forEach>
            Liczba worków: ${donation.quantity}<br>
            Adres odbioru:<br>
            ${donation.street}<br>
                    ${donation.zipCode} ${donation.city}<br>
            Termin dostawy:
            Data: ${donation.pickUpDate}<br>
            Godzina: ${donation.pickUpTime}<br>
            Uwagi dla kuriera: ${donation.pickUpComment}<br>
            Status:
            <c:choose>
            <c:when test="${donation.status=='TAKEN'}">
            odebrana
            </c:when>
            <c:otherwise>
            nieodebrana
            </c:otherwise>
            </c:choose>

                    <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/manage-donation?id=${institution.id}"
            class="btn btn--highlighted" type="submit">Edytuj</a>
            <a href="${pageContext.request.contextPath}/delete-donation?id=${institution.id}"
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
