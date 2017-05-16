/*
 * Testing code to show other ways we might call a stored procedure.
 * If we're encountering a problem with the CallableStatement code
 * then we need to know our options with particular server versions
 * 
 * 
 */

import java.io.*;

import java.sql.*;

public class TestStoredProcedure {

	public static void main(String[] args){
		try{
			//establish the connection
			String connectionString = "jdbc:Cache://172.16.208.69:1972/SAMPLES/myjdbc.log"
			//String driverString = "com.intersys.jdbc.CacheDriver";
			String usernameString = "_SYSTEM";
			String passwordString = "SYS";
			
			System.out.print("Connecting to server...");
			Connection connectionOBJ = DriverManager.getConnection(connectionString,usernameString,passwordString);
			System.out.print("Connected!");
			
			
			
		}
		catch (Exception ex) {
 			System.out.println("Exception: " +
                               ex.getClass().getName()
                               + ": " + ex.getMessage());
 		}
		}
		
	}
	
}
