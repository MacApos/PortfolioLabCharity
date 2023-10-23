<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<form id="formTest" novalidate="novalidate">
    <label>
        <input type="text" required/>
    </label>
    <button type="submit">Submit</button>
    <div class="invalid-feedback">
        Wpisz wartość.
    </div>
</form>
</body>
<script src="<c:url value="../../resources/js/test.js"/>"></script>
</html>
