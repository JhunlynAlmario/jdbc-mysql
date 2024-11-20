package com.java.dsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DeletingData {
	
	private static final String url = "jdbc:mysql://localhost:3306/demo_labag";
	private static final String user = "root";
	private static final String password = "";
	private static final String queryUpdate = "DELETE FROM employees_labag WHERE id = 17";
	private static int updateCount;
	private static final String queryOutput = "SELECT * FROM employees_labag";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			setUpdateCount(statement.executeUpdate(queryUpdate));
			resultSet = statement.executeQuery(queryOutput);
			
			System.out.println("List of Employees");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				String email = resultSet.getString("email");
				String department = resultSet.getString("department");
				double salary = resultSet.getDouble("salary");
				System.out.println("id: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Department: " + department + ", Salary: " + salary);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getUpdateCount() {
		return updateCount;
	}

	public static void setUpdateCount(int updateCount) {
		DeletingData.updateCount = updateCount;
	}
}
