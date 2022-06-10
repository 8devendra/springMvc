<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<spring:url value="/account/printDirect" var="actionURL" />
<form:form modelAttribute="printerModal" method="GET"  action="${ actionURL }">
<form:select path="printerName">
<form:option value="0" label="Select Printer"/>
<form:options items="${printerModalList}" itemValue="printerName" itemLabel="printerName"/>

</form:select>

<button type="submit" >PRINT</button>

</form:form>





</body>
</html>