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
<h1>Add Account</h1>


<form:form action="accountAdd" method="post" modelAttribute="account">
<form:input path="accountNo"/>
<form:input path="balance"/>
<input type="submit" value="Submit">
</form:form>
<%-- 
<form action="accountAdd" method="POST">
<input type="text" id="id" name="accountNo" placeholder="Account No"> 
<br>
<input type="text" id="name" name="balance" placeholder="Balance">
<input type="submit" value="Submit"> 
</form> --%>
</body>
</html>