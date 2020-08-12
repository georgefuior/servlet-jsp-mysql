package ro.georgefuior.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import ro.georgefuior.entity.Employee;
import ro.georgefuior.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;

	@Override
	public List<Employee> get() {

		//Create reference variables
		List<Employee> employees = null;
		Employee employee = null;	

		//Create a sql query
		String sql = "SELECT * FROM tbl_employee";

		try {
			employees = new ArrayList<>();

			//Get the database connection

			connection = DBConnectionUtil.openConnection();

			//Create a statement
			statement = connection.createStatement();
			//Execute the sql query
			resultSet = statement.executeQuery(sql);

			//Process the resultSet
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDob(resultSet.getString("dob"));
				employee.setDepartment(resultSet.getString("department"));
				//Add employee to the list
				employees.add(employee);
			}		

		}catch(SQLException e) {
			System.out.println("Database fail: "+e.getMessage());
		}

		//return the list
		return employees;
	}

	@Override
	public boolean save(Employee e) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO tbl_employee(name, dob, department) VALUES ('"+e.getName()+"', '"
					+ e.getDob()+"', '"+e.getDepartment()+"')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeLargeUpdate();
			flag = true;
		}catch(SQLException ex) {
			System.out.println("Error in inserting data into table "+ex.getMessage());
			ex.printStackTrace();

		}
		return flag;
	}

	public Employee get(int id) {
		Employee employee = null;
		try {
			employee = new Employee();
			String sql = "SELECT *FROM tbl_employee WHERE id = "+id;
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));

			}
		}catch(SQLException ex) {
			System.out.println("Failed to get the employee for specific id: "+ex.getMessage());
		}
		System.out.println(employee);
		return employee;
	}

	public boolean update(Employee e) {
		boolean flag = false;
		try {
			String sql = "UPDATE tbl_employee SET name = '"+e.getName()+"', dob ='"+e.getDob()+
					"', department = '"+e.getDepartment()+"' WHERE id ="+e.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			System.out.println("Failed to update the entry "+ex.getMessage());
		}
		return flag;
	}
	
	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_employee WHERE id="+id;
				connection = DBConnectionUtil.openConnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.executeLargeUpdate();
				flag = true;		
		}catch(SQLException ex) {
			System.out.println("Failed to delete the entry :"+ex.getLocalizedMessage());
		}
		return flag;
	}


}
