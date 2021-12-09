<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href='<spring:url value="/resources/css/style.css"></spring:url>'/>
<meta charset="ISO-8859-1">
<title>View All Accounts</title>
</head>
<body>
<br>
<table >
<tr><th>Account No</th><th>Balance</th><th colspan="2">ACTION</th></tr>
<c:forEach items="${ accounts }" var="c" >

<tr>

<td>${ c.accountNo } </td>
<td>${ c.balance }</td>

<td><a href="update?accountNo=${ c.accountNo }"> &#9998; Edit </a></td>
<td><a href="delete?accountNo=${ c.accountNo }"> &#x1f5d1; Delete</a></td>

</tr>

</c:forEach>
</table>
<a href="add">Add Account</a>
<br>
<a href="update">Update Account</a>

</body>
</html>