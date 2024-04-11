<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="post" modelAttribute="newUserForm">
    <label for="phoneInput">User: </label>
    <form:input path="email" id="phoneInput" value="User1"/><br>
    <form:input path="verifyEmail" id="phoneInput" value="User1"/><br>
    <form:errors id="${newUserForm}" element="div" cssClass="error-div" /><br>
    <form:input path="password" id="phoneInput" value="Password1"/><br>
    <form:input path="verifyPassword" id="phoneInput" value="Password2"/><br>
<%--    <form:errors path="email" element="div" cssClass="error-div"/><br>--%>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>
