<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<!--  <link rel="stylesheet" href="resources/css/cadastroUsuario.scss">-->
</head>
<body>
	<h1 align="center">Cadastro de usuário</h1>

	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<thead>
				<tr>
					<th scope="col">Id Usuário</th>
					<th scope="col">Login</th>
					<th scope="col">Senha</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${usuario}" var="user">
				<tr>
					<th scope="row"><c:out value="${user.id}"></c:out></th>
					<td data-title="Login"><c:out value="${user.login}"></c:out>
					</td>
					<td data-title="Senha"><c:out value="${user.senha}"></c:out></td>

					<td data-title="Excluir"><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a>
					</td>
					<td data-title="Editar"><a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>