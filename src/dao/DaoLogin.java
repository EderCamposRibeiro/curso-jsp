package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {
	
	//2° Precisa ter um objeto do Connection
	private Connection connection;
	
	//1° Criar um construtor default
	public DaoLogin() {
		//3° Atribuir a conexão do banco de dados à variável connection
		connection = SingleConnection.getConnection();
	}
	
	//4° Select para validar o login e senha no Banco de Dados
	public boolean validarLogin(String login, String senha)  throws Exception{
		String sql = "select * from usuario where login = '"+login+"'"
				   + "and senha = '"+senha+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return true; //Possui Usuário;
		} else {
			return false;//Não validou Usuário;
		}
		
	}

}
