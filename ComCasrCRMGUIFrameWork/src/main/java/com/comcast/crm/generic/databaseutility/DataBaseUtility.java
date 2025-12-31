package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;


public class DataBaseUtility {
	Connection con;
	
	public void getdbConnection(String url, String username, String password) throws SQLException {
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url,username,password);
		}catch(Exception e) {
				
		}
	}
	
	public void getdbConnection() throws SQLException {
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "admin");
		}catch(Exception e) {
				
		}
	}
	
	public void closeDbconnection() throws SQLException {
		con.close();
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet resultset = null;
		try {
		Statement stat = con.createStatement();
		resultset = stat.executeQuery(query);
	    }catch(Exception e){}
		return resultset;
	}
	
	public int executeNonselectQuery(String query) throws SQLException {
		int resultset = 0;
		try {
		Statement stat = con.createStatement();
		resultset = stat.executeUpdate(query);
	    }catch(Exception e){}
		return resultset;
	}
	

}
