<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula" />
	<h3>Seja bem vindo ao sistema em JSP!</h3>

	<a href="salvarUsuario?acao=listartodos"> <img
		src="resources/img/cadastro.png" width="100px" height="100px"
		title="Cadastrar/Alterar usuário" alt="Cadastrar/Alterar usuário">
	</a>
	<a href="salvarProduto?acao=listartodos"> <img
		src="resources/img/produtos.png" width="100px" height="100px"
		title="Cadastrar/Alterar produto" alt="Cadastrar/Alterar produto">
	</a>


</body>
</html>