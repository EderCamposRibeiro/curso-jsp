<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoLiberado.jsp">Início</a>
	<br>
	<a href="index.jsp">Sair</a>

	<h1 align="center">Cadastro de produto</h1>


	<form action="salvarProduto" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${product.id}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${product.nome}" placeholder="Digite o nome do produto"></td>
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${product.quantidade}" placeholder="Digite a quantidade"></td>
					</tr>
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" 
							value="${product.valor}" placeholder="Valor do produto em Reais"></td>
					</tr>		
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formProduct').action = 'salvarProduto?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>
	
	<h3 align="center" style="color: red;">${msg}</h3>

	<div class="container">
		<table class="responsive-table">
		<caption>Produtos cadastrados</caption>
			<thead>
				<tr>
					<th scope="col">Id Produtos</th>
					<th scope="col">Nome</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor R$</th>
					<th scope="col">Excluir</th>
					<th scope="col">Editar</th>
					
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${produtos}" var="product">
				<tr>
					<th scope="row"><c:out value="${product.id}"></c:out></th>
					<td data-title="Nome"><c:out value="${product.nome}"></c:out></td>
					<td data-title="Quantidade"><c:out value="${product.quantidade}"></c:out></td>
					<td data-title="Valor"><c:out value="${product.valor}"></c:out></td>			
					<td data-title="Excluir"><a href="salvarProduto?acao=delete&product=${product.id}">
						<img src="resources/img/excluir.png" width="20px" height="20px" title="Excluir" alt="Excluir"> </a>
					</td>
					<td data-title="Editar"><a href="salvarProduto?acao=editar&product=${product.id}">
						<img src="resources/img/editar.png" width="20px" height="20px" title="Editar" alt="Editar"></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('Informe o Nome do Produto!')
				return false;
			}
			if (document.getElementById("quantidade").value == '') {
				alert('Informe a Quantidade do Produto!')
				return false;
			}
			if (document.getElementById("valor").value == '') {
				alert('Informe o Valor do Produto!')
				return false;
			}
			return true;
		}
	</script>
</body>
</html>