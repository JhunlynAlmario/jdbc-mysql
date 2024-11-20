package com.java.dsa;


import java.sql.*;

public class PreparedStatements {

	    public static void main(String[] args) {

	        try (Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
	             PreparedStatement myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?")) {

	            // First query
	            executeQuery(myStmt, 80000, "Legal");

	            // Reuse the prepared statement for the second query
	            System.out.println("\n\nReuse the prepared statement: salary > 25000, department = HR");
	            executeQuery(myStmt, 25000, "HR");

	        } catch (SQLException exc) {
	            exc.printStackTrace();
	        }
	    }

	    private static void executeQuery(PreparedStatement myStmt, double salary, String department) throws SQLException {
	        myStmt.setDouble(1, salary);
	        myStmt.setString(2, department);

	        try (ResultSet myRs = myStmt.executeQuery()) {
	            display(myRs);
	        }
	    }

	    private static void display(ResultSet myRs) throws SQLException {
	        while (myRs.next()) {
	            String lastName = myRs.getString("last_name");
	            String firstName = myRs.getString("first_name");
	            double salary = myRs.getDouble("salary");
	            String department = myRs.getString("department");

	            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
	        }
	    }
	}
