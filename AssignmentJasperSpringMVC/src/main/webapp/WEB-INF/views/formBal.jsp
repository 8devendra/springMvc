<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<body>
<spring:url value="/account/addAccountBalCall" var="url"></spring:url>

<%-- <c:url value = "/account/addAccount"/> --%>
<form:form action='${ url }' id="accountForm"
 name="accountForm" modelAttribute="account" method="GET">
<label>Account Number:  </label><form:input path="accountNo" />
<br>
<label>Account Balance:  </label><form:input path="balance" />
<br>
<label>Account Type:  </label><form:input path="accountType" />
<br>
<button type="submit" onclick="document.accountForm.command.value='SUBMIT'">SUBMIT</button>
<button type="submit" onclick="document.accountForm.command.value='RESET'">RESET</button>
<br>
<input type="text" id="command">
<br>
<br>


<br>

<br>

<br>




</form:form>






</body>
</html>