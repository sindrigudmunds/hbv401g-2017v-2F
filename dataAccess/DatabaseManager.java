package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager
{
	private Connection connection = null;
	
	
	// Creates a connection to the database
	public void connect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		try {		
			this.connection = DriverManager.getConnection("jdbc:sqlite:db1.db");
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	  
	// Closes the database connection
	public void disconnect() {
		try
	    {
			if(this.connection != null)
				this.connection.close();
	    }
	    catch(SQLException e)
	    {
	    	System.err.println(e);
	    }
	}
	  
	// Queries the database with the sql statements in the input
	public ResultSet queryDatabase(String sql) {
		ResultSet rs = null;
		try {
			Statement statement = this.connection.createStatement();
			statement.setQueryTimeout(30);
		System.out.println(sql);
			rs = statement.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return rs;
	}
	  
	// Updates the database with the sql statements in the input
	public void updateDatabase(String sql) {
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
}