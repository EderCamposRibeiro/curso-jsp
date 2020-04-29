package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	
	private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefone telefone) {
		try {
			String sql = "INSERT INTO telefone (numero, tipo, usuario) VALUES(?, ?, ?) ";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanTelefone> listar(Long user) throws Exception{
		List<BeanTelefone> listar = new ArrayList<BeanTelefone>();
		
		String sql = "select * from telefone where usuario = " + user ;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		
		while (resultSet.next()) {
			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));
			
			listar.add(beanTelefone);
		}
		
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "DELETE FROM telefone WHERE id = '" + id + "'";
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
