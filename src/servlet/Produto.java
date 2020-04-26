package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		try {
			String acao = req.getParameter("acao");
			String product = req.getParameter("product");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(product);
				RequestDispatcher view = req.getRequestDispatcher("/cadastroProduto.jsp");
				req.setAttribute("produtos", daoProduto.listar());
				view.forward(req, resp);
			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanProduto = daoProduto.consultar(product);

				RequestDispatcher view = req.getRequestDispatcher("/cadastroProduto.jsp");
				req.setAttribute("product", beanProduto);
				view.forward(req, resp);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = req.getRequestDispatcher("/cadastroProduto.jsp");
				req.setAttribute("produtos", daoProduto.listar());
				view.forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String acao = req.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = req
						.getRequestDispatcher("/cadastroProduto.jsp");
				req.setAttribute("produtos", daoProduto.listar());
				view.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = req.getParameter("id");
			String nome = req.getParameter("nome");			
			String quantidade = req.getParameter("quantidade");
			String valor = req.getParameter("valor");

			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);	
			
			if (quantidade != null && !quantidade.isEmpty()) {
				produto.setQuantidade(Double.parseDouble(quantidade));
			}
			if (valor != null && !valor.isEmpty()) {
				produto.setValor(Double.parseDouble(valor));
			}			
			
			try {

				String msg = null;
				boolean podeInserir = true;
				
				if (nome == null || nome.isEmpty()) {
					msg = "O Nome deve ser informado!";
					podeInserir = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "A Quantidade deve ser informada!";
					podeInserir = false;
				} else if (valor == null || valor.isEmpty()) {
					msg = "O Valor deve ser informado!";
					podeInserir = false;
				} else if (id == null || id.isEmpty()
						&& !daoProduto.validarNome(nome)) {//QUANDO FOR PRODUTO NOVO
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;
				} else 	if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {
					daoProduto.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir && daoProduto.validarProprioNome(nome, id)) {
					daoProduto.atualizar(produto);
				} else {
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;
				}
				
				if (msg != null) {
					req.setAttribute("msg", msg);
				}
				
				if (!podeInserir) {
					req.setAttribute("product", produto);
				}

				RequestDispatcher view = req
						.getRequestDispatcher("/cadastroProduto.jsp");
				req.setAttribute("produtos", daoProduto.listar());
				view.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
