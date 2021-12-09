<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Update Customer</h1>
<form:form action="updateCust" method="POST" modelAttribute="customer">
<form:input path="customerId"/>
<form:input path="customerName"/>
<input type="submit" value="Submit">
</form:form>
<%-- 
<form action="updateCust" method="POST">
<input type="text" id="id" name="customerId" value="${ customerId }" placeholder="ID"> 
<br>
<input type="text" id="name" name="customerName" placeholder="Name">
<input type="submit" value="Submit"> 
</form>
 --%>
 </body>
</html>