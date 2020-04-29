<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Cadastro de telefones</title>
	<link rel="stylesheet" href="resources/css/cadastro.css">
</head>

<body>
	<a href="cadastroUsuario.jsp">Início</a>
	<br>
	<a href="index.jsp">Sair</a>
	<h1 align="center">Cadastro de telefones</h1>


	<form action="salvarTelefones" method="post" id="formUser" onsubmit="return validarCampos()? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" 
							class="field-long" value="${userEscolhido.id}"></td>

						<td><input type="text" readonly="readonly" id="nome" name="nome" 
							class="field-long" value="${userEscolhido.nome}"></td>	
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>
						<td>
						<select id="tipo" name="tipo">
							<option>Casa</option>
							<option>Trabalho</option>
							<option>Contato</option>
							<option>Celular</option>
						</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>  
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<h3 align="center" style="color: red;">${msg}</h3>

	<div class="container">
		<table class="responsive-table">
			<caption>Telefones cadastrados</caption>
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col">Excluir</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${telefones}" var="fone">
					<tr>
						<th scope="row">
							<c:out value="${fone.id}"></c:out>
						</th>
						<td data-title="Numero">
							<c:out value="${fone.numero}"></c:out>
						</td>
						<td data-title="Tipo">
							<c:out value="${fone.tipo}"></c:out>
						</td>
						<td data-title="Excluir"><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"> <img
									src="resources/img/excluir.png" width="20px" height="20px" title="Excluir"
									alt="Excluir">
							</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o Número')
				return false;
			} else
			if (document.getElementById("tipo").value == '') {
				alert('Selecione o Tipo')
				return false;
			}			
			return true;
		}

	</script>

</body>

</html>