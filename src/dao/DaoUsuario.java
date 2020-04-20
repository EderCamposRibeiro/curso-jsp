package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanCursoJsp usuarios)   {
		try {
			String sql = "insert into usuario(login, senha) values (?, ?) ";
			PreparedStatement insert = connection.prepareStatement(sql); 
			insert.setString(1, usuarios.getLogin());
			insert.setString(2, usuarios.getSenha());
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
		 
}
