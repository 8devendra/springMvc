<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<ol>
  
  <li><a href='<spring:url value="/account/addAccountBal" />'> Add Account with Bal </a></li>
  <li><a href='<spring:url value="/account/addAccount1" />'> Add Account without Bal </a></li>
  <li><a href='<spring:url value="/account/makePrint" />'> Make Print </a></li>
  
  
</ol>
</body>
</html>