package ro.georgefuior.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ro.georgefuior.entity.Login;
import ro.georgefuior.util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String authenticate(Login login) {
		String sql = "SELECT * FROM tbl_login WHERE email = ? and password = ?";
		
		try {	
			Connection connection = DBConnectionUtil.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,login.getEmail());
			preparedStatement.setString(2,login.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				return "true";
			}else {
				return "false";
			}
		}catch(SQLException e) {
			System.out.println("Fail to authenticate "+e.getMessage());
		}
		return "error";
	}

}
