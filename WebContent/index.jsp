<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld" %>
    <jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Index</h1>
	<br/>
	<form action="cabecalho.jsp" method="post">
	
		<% session.setAttribute("user", "javaavancado"); %>
		<a href="cabecalho.jsp">Ver Teste</a>
	</form>

</body>
</html>