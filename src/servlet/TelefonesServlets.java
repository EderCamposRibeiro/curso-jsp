package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.BeanTelefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet(name = "salvarTelefones", urlPatterns = { "/salvarTelefones" })
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao.equals("addFone")) {

				String user = request.getParameter("user");

				BeanCursoJsp usuario = daoUsuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);
			} else if (acao.equals("deleteFone")) {
				String foneId = request.getParameter("foneId");
				daoTelefone.delete(foneId);
				
				BeanCursoJsp benBeanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(benBeanCursoJsp.getId()));
				request.setAttribute("msg", "Removido com sucesso!");
				view.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanCursoJsp benBeanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setNumero(numero);
			beanTelefone.setTipo(tipo);
			beanTelefone.setUsuario(benBeanCursoJsp.getId());

			daoTelefone.salvar(beanTelefone);

			request.getSession().setAttribute("userEscolhido", benBeanCursoJsp);
			request.setAttribute("userEscolhido", benBeanCursoJsp);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daoTelefone.listar(benBeanCursoJsp.getId()));
			request.setAttribute("msg", "Salvo com sucesso!");
			view.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
