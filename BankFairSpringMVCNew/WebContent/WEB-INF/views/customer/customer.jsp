<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%-- <c:url value="/resources/css/style.css" var="url"></c:url> --%>
<!-- <link rel="stylesheet" href="/BankFairSpringMVCNew/resources/css/style.css"> -->

<%-- <link rel="stylesheet" href="${url}"> --%>

 <link rel="stylesheet" href='<spring:url value="/resources/css/style.css"></spring:url>'/> 

<title>View All Customer</title>
</head>
<security:authentication property="principal.username"/>
<body>
<%-- <a href="<c:url value="/logout" />">Logout</a> --%>


<br>
<table >
<tr><th>ID</th><th>NAME</th><th colspan="2">ACTION</th></tr>
<security:authorize access="hasRole('ROLE_USER')">


<c:forEach items="${ customer }" var="c" >

<tr>

<td>${ c.customerId } </td>
<td>${ c.customerName }</td>

<td><a href="update?customerId=${ c.customerId }"> &#9998; Edit </a></td>
<td><a href="delete?customerId=${ c.customerId }"> &#x1f5d1; Delete</a></td>

</tr>


</c:forEach>
</security:authorize>
</table>

<a href="add">Add Customer</a>
<br>
<a href="update">Update Customer</a>

</body>
</html>