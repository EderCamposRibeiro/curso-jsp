package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
	
	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanProduto produto) {
		try {
			String sql = "INSERT INTO produto (nome, quantidade, valor) VALUES(?, ?, ?) ";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
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
	
	public List<BeanProduto> listar() throws Exception{
		List<BeanProduto> listar = new ArrayList<BeanProduto>();
		
		String sql = "select * from produto ";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();
		
		while (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			
			listar.add(beanProduto);
		}
		
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "DELETE FROM produto WHERE id = '" + id + "'";
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

	public BeanProduto consultar(String id) throws Exception{
		String sql = "select * from produto where id = '" + id + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			return beanProduto;
		}
		
		return null;
	}	
	
	//É possível usar esse nome de produto??????
	public boolean validarNome(String nome) throws Exception{
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*Retorna True - sim é possivel usar este nome*/
		}
		
		return false; //Não é possível usar esse nome!
	}
	

	public boolean validarProprioNome(String nome, String id) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "' and id <> " + id + "";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;/*Retorna True - sim é possivel usar este nome*/
		}
		return false;
	}
	
	public void atualizar(BeanProduto produto) {
		try {
			String sql = "UPDATE produto SET nome= ?, quantidade= ?, valor= ? where id = " + produto.getId(); 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
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
