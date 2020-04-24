package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanCursoJsp usuarios)   {
		try {
			String sql = "insert into usuario(login, senha, nome, telefone) values (?, ?, ?, ?) ";
			PreparedStatement insert = connection.prepareStatement(sql); 
			insert.setString(1, usuarios.getLogin());
			insert.setString(2, usuarios.getSenha());
			insert.setString(3, usuarios.getNome());
			insert.setString(4, usuarios.getTelefone());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<BeanCursoJsp> listar() throws Exception {
		
		List<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
		
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(); 
		
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			
			lista.add(beanCursoJsp);
		}
		return lista; 
	}
	
	public void delete(String id) {
		try {
			String sql = "DELETE FROM usuario WHERE id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception{
		String sql = "select * from usuario where id = '" + id + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			return beanCursoJsp;
		}
		
		return null;
	}
	
	//É possível usar esse login??????
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*Retorna True - sim é possivel usar este login*/
		}
		
		return false; //Não é possível usar esse login!
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*Retorna True - sim é possivel usar este login*/
		}
		
		return false; //Não é possível usar esse login!
	}
	

	public boolean senhaDiferente(String senha, String id) throws SQLException {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <> " + id ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*Retorna True - A senha é diferente*/
		}
		
		return false; //Senha repetida!
	}	

	public void atualizar(BeanCursoJsp usuario) {
		try {
			String sql = "UPDATE usuario SET login= ?, senha= ?, nome= ?, telefone=? where id = " + usuario.getId(); 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getTelefone());
			statement.executeUpdate();
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
		
	}

	
	
}
