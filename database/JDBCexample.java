package database;

import java.sql.*;

public class JDBCexample {
	
	public static ResultSet queryTwo(String type, String table, String data) throws SQLException {
		 
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:63333/group6_db", "tacohen",
					//"password");//This is for non-BG6 connections
					"jdbc:postgresql://127.0.0.1:5432/group6_db", "tacohen",
					"password");//Connection for working from bg6
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("Connected Sucessfully");
		} else {
			System.out.println("Failed to make connection!");
		}
		

        if( connection != null ) {
            ResultSet result = execQuery( connection, "SELECT date FROM " + table + " WHERE (" + type + " = " + "'" +data+"')" +";");
            if( result != null ){
            	//printResults(result);
            	return result;
            }
          }
		return null;
        
        
        
	}
 
	public static ResultSet findDateQuery(String type, String table, String date) throws SQLException {
 
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:63333/group6_db", "tacohen",
					//"password");//This is for non-BG6 connections
					"jdbc:postgresql://127.0.0.1:5432/group6_db", "tacohen",
					"password");//Connection for working from bg6
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		

        if( connection != null ) {
            ResultSet result = execQuery( connection, "SELECT " + type + " FROM " + table + " WHERE (date = " + "'" +date+"')" +";");
            if( result != null ){
            	//printResults(result);
            	return result;
            }
          }
		return null;
        
        
        
	}
	
	public static ResultSet findAverageQuery(String type, String table, String date1, String date2) throws SQLException {
		 
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:63333/group6_db", "tacohen",
					//"password");//This is for non-BG6 connections
					"jdbc:postgresql://127.0.0.1:5432/group6_db", "tacohen",
					"password");//Connection for working from bg6
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("Connected!!");
		} else {
			System.out.println("Failed to make connection!");
		}
		

        if( connection != null ) {
            //ResultSet result = execQuery( connection, "SELECT " + type + " FROM " + table + " WHERE (date = " + "'" +date+"')" +";");
        	//SELECT AVG(PRICE) AS AveragepRICE FROM GAS WHERE('Dd1' < DATE) AND (date < 'd2');
        	
        	ResultSet result = execQuery( connection, "SELECT ROUND(AVG(" + type + "),2) AS AVERAGEPRICE FROM " + table + " WHERE ('"+ date1 + "' < DATE) AND (DATE < '" + date2 +"');");
        	if( result != null ){
            	//printResults(result);
            	return result;
            }
          }
		return null;
        
        
        
	}
	
public static ResultSet insertQuery(String type, String table, String date, String dataDesired) throws SQLException {
		
		//Convert data to an integer
		System.out.println(dataDesired);
		Float data = Float.parseFloat(dataDesired);
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:63333/group6_db", "tacohen",
					//"password");//This is for non-BG6 connections
					"jdbc:postgresql://127.0.0.1:5432/group6_db", "tacohen",
					"password");//Connection for working from bg6
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("Connected!!");
		} else {
			System.out.println("Failed to make connection!");
		}
		

        if( connection != null ) {
        	//ResultSet result = execQuery( connection, "SELECT " + type + " FROM " + table + " WHERE (date = " + "'" +date+"')" +";");
        	//SELECT AVG(PRICE) AS AveragepRICE FROM GAS WHERE('Dd1' < DATE) AND (date < 'd2');

        	//INSERT INTO gas VALUES (d, p);
        	if (table != "exchange"){
        		ResultSet result = execQuery( connection, "INSERT INTO "+ table +" (date, "+type+") VALUES ('"+date +"',"+data+");");
        	}
        	else{
        		ResultSet result = execQuery( connection, "UPDATE "+ table +" SET "+type+ " = "+ data+" WHERE DATE = '"+date+"';");
        	}
        	//if( result != null ){
        	//printResults(result);
        	//	return result;
            //}
          }
		return null;
		
	}
	
public static ResultSet deleteQuery(String date, String table) throws SQLException {
		
	
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:63333/group6_db", "tacohen",
					//"password");//This is for non-BG6 connections
					"jdbc:postgresql://127.0.0.1:5432/group6_db", "tacohen",
					"password");//Connection for working from bg6
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("Connected!!");
		} else {
			System.out.println("Failed to make connection!");
		}
		

        if( connection != null ) {
            
        	//DELETE FROM gas WHERE date=d;
        	ResultSet result = execQuery( connection, "DELETE FROM "+table+" WHERE DATE='"+date+"';");
        	
          }
		return null;
		
	}
	
	static ResultSet execQuery( Connection con, String query )
	  {
	    try {
	      Statement stmt = con.createStatement();
	      System.out.println( query );
	      return( stmt.executeQuery( query ));
	    }
	    catch( SQLException e ) {
	      //System.err.println( "Query failed - " + e.getMessage());
	      return( null );
	    }
	  }
	
	static void printResults( ResultSet res )
	  {
	    System.out.println( "    Customer_name   |   Account_Number" );
	    System.out.println( "--------------------+--------------------" );
	    try {
	      while( res.next()) {
	        System.out.printf("%20s", res.getString( 1 ));
	        //System.out.print( "|");
	        //System.out.printf("%20s", res.getString( 2 ));
	        System.out.println( "" );
	      }
	    }
	    catch( SQLException e ) {
	      System.err.println( "Fetch failed: " + e.getMessage());
	    }
	  }
	}
 




