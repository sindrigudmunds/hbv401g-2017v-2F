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
	
	public void connect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		try {		
			this.connection = DriverManager.getConnection("jdbc:sqlite:db.db");
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	  
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
	  
	
	// Vandamál hér, það má ekki skila ResultSet út úr fallinu, gætum þurft lista eða eitthvað 
	public ResultSet queryDatabase(String sql) {
		//this.connect();
		ResultSet rs = null;
		try {
			Statement statement = this.connection.createStatement();
			statement.setQueryTimeout(30);
		 System.out.println(sql);
			rs = statement.executeQuery(sql);
		System.out.println(rs.getString("flightID"));
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		//this.disconnect();
		return rs;
	}
	  
	
	public void updateDatabase(String sql) {
		//this.connect();
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		//this.disconnect();
	}
}