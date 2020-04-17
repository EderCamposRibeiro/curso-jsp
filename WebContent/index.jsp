<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Index</h1>
	<jsp:forward page="receber-nome.jsp">
		<jsp:param value="curso de jsp site java avancado.com" name="paramforward"/>
	</jsp:forward>

</body>
</html>