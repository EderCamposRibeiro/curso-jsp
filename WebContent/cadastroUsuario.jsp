<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Cadastro de usuário</title>
	<link rel="stylesheet" href="resources/css/cadastro.css">
	<!-- Adicionando JQuery -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>

<body>
	<a href="acessoLiberado.jsp">Início</a>
	<br>
	<a href="index.jsp">Sair</a>
	<h1 align="center">Cadastro de usuário</h1>


	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos()? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"
								class="field-long"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login" value="${user.login}"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" value="${user.senha}"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" value="${user.nome}"></td>
					</tr>
					<tr>
						<td>Fone:</td>
						<td><input type="text" id="telefone" name="telefone" value="${user.telefone}"></td>
					</tr>
					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep" onblur="consultaCep();"></td>
					</tr>
					<tr>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"></td>
					</tr>
					<tr>
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"></td>
					</tr>
					<tr>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"></td>
					</tr>
					<tr>
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"></td>
					</tr>
					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar"
								onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<h3 align="center" style="color: red;">${msg}</h3>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<thead>
				<tr>
					<th scope="col">Id Usuário</th>
					<th scope="col">Login</th>
					<th scope="col">Nome</th>
					<th scope="col">Fone</th>
					<th scope="col">Excluir</th>
					<th scope="col">Editar</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<th scope="row">
							<c:out value="${user.id}"></c:out>
						</th>
						<td data-title="Login">
							<c:out value="${user.login}"></c:out>
						</td>
						<!--  <td data-title="Senha"><c:out value="${user.senha}"></c:out></td>-->
						<td data-title="Nome">
							<c:out value="${user.nome}"></c:out>
						</td>
						<td data-title="Telefone">
							<c:out value="${user.telefone}"></c:out>
						</td>
						<td data-title="Excluir"><a href="salvarUsuario?acao=delete&user=${user.id}"> <img
									src="resources/img/excluir.png" width="20px" height="20px" title="Excluir"
									alt="Excluir">
							</a></td>
						<td data-title="Editar"><a href="salvarUsuario?acao=editar&user=${user.id}"> <img
									src="resources/img/editar.png" width="20px" height="20px" title="Editar"
									alt="Editar"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login')
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe A Senha')
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome')
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone')
				return false;
			}
			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

				if (!("erro" in dados)) {
					$("#rua").val(dados.logradouro);
					$("#bairro").val(dados.bairro);
					$("#cidade").val(dados.localidade);
					$("#estado").val(dados.uf);
					$("#ibge").val(dados.ibge);
				} //end if.
				else {
					limpa_formulário_cep()
					alert("CEP não encontrado.");
				}
			});

		}
		function limpa_formulário_cep() {
			// Limpa valores do formulário de cep.
			$("#cep").val("");
			$("#rua").val("");
			$("#bairro").val("");
			$("#cidade").val("");
			$("#estado").val("");
			$("#ibge").val("");
		}
	</script>

</body>

</html>