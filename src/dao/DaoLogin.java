package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {
	
	//2� Precisa ter um objeto do Connection
	private Connection connection;
	
	//1� Criar um construtor default
	public DaoLogin() {
		//3� Atribuir a conex�o do banco de dados � vari�vel connection
		connection = SingleConnection.getConnection();
	}
	
	//4� Select para validar o login e senha no Banco de Dados
	public boolean validarLogin(String login, String senha)  throws Exception{
		String sql = "select * from usuario where login = '"+login+"'"
				   + "and senha = '"+senha+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return true; //Possui Usu�rio;
		} else {
			return false;//N�o validou Usu�rio;
		}
		
	}

}
