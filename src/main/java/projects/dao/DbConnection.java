package projects.dao;
/**
 * This class will obtain a JDBC connection from the driver manager by using DriverManager.getConnection() method.
 * if connection cannot be established, it throws a checked  SQLException which is converted to unchecked exception
 * in a catch block
 * */

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
	// Creating constants
	public static final String HOST = "localhost";
	public static final String PASSWORD = "projects";
	public static final int PORT = 3306;
	public static final String SCHEMA = "projects";
	public static final String USER = "projects";
	
	// creating method getConnection 
	public static Connection getConnection () {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", 
				HOST,PORT, SCHEMA, USER, PASSWORD);
		
		System.out.println("Connecting with url = " + url);
		
		try {
		Connection conn = DriverManager.getConnection(url);
		System.out.println("Connection is Succesfully Obtained");
		return conn;
		} catch (SQLException e) {
			throw new DbException (e); 
			
		}
	}

}
