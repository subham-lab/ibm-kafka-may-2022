package com.ibm.kafka.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetConnection {

	// all variables WRT connection goes here 
	static Connection connection;
	public PreparedStatement ps, ps1, ps2; 
	public ResultSet rs1, rs2; 
	
	public static Connection getMySQLConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/ibmdb", 
							"root", "kanchan@1");

		 
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	public static Connection getPostGresConnection() {

		try {
			Class.forName("com.postgress.Driver");
			connection = DriverManager.getConnection
					("jdbc:postgres://localhost;5431/ibmdb", 
							"root", "kanchan@1");

		 
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
}