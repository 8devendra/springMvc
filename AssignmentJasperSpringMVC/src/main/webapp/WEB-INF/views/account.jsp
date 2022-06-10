<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 <c:url value = "/jsp/index.htm"/>
<a  href=<c:url value = "/jsp/index.htm"/>>welcome</a>
<button onclick="abcd()"
${client!='hrcu'? 'hidden="hidden"':'' }
>ABCD</button>

<input type="text" onchange="abc"/>











src="data:application/pdf;base64,${image}" 

<iframe id="iframes" width="300" height="500"></iframe>
</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">

function abc(){
	alert("HI");
}
function abcd(){

	$.ajax({                                            
	    url: '<c:url value = "/account/showpdf"/>',                        
	  //  data: 'id1='+q+'',                                                         
	  //  dataType: 'json',
	    async:false,                    
	    success: function(data)          
	    {   
	    	console.log("SUCC "+data);
		     //$('iframes').attr('src',"data:application/pdf;base64,"+data);
		     document.getElementById('iframes').setAttribute("src","data:application/pdf;base64,"+data);
	       
	    },
	   complete: function (data) {
	     console.log("CALL "+data);
	     //$('iframes').attr('src',"data:application/pdf;base64,"+data);
	     //document.getElementById('iframes').setAttribute("src","data:application/pdf;base64,"+data);
	    }
	   });	
}
</script>
</html>