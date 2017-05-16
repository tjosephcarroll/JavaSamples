/*
 * Testing code to show other ways we might call a stored procedure.
 * If we're encountering a problem with the CallableStatement code
 * then we need to know our options with particular server versions
 * 
 * javac TestStoredProcedure.java
 * 
 * java -cp /intersystems/cache/dev/java/lib/JDK18/cache-jdbc-2.0.0.jar:. TestStoredProcedure
 */

import java.io.*;

import java.sql.*;

public class TestStoredProcedure {

	public static void main(String[] args){
		try{
			//establish the connection
			String connectionString = "jdbc:Cache://127.0.0.1:1972/SAMPLES/myjdbc.log";
			String usernameString = "_SYSTEM";
			String passwordString = "SYS";
			
			System.out.print("Connecting to server...");
			Connection connectionOBJ = DriverManager.getConnection(connectionString,usernameString,passwordString);
			System.out.print("Connected!");
			System.out.println();
			
			//Stored procedure call
			String query = "call Sample.SP_Sample_By_Name()";
			
			//prepared statement
			PreparedStatement pstmt = connectionOBJ.prepareStatement(query);
			java.sql.ResultSet rs = pstmt.executeQuery();
			System.out.println("Prepared Statment Results:");
 			ResultSetMetaData metadata = rs.getMetaData();
 			int count = metadata.getColumnCount();
			int rowcount=0;
 			while (rs.next()) {
				System.out.print(++rowcount+". ");
 				for (int i=1; i<=count; i++) {
 					System.out.print(rs.getString(i) + "  ");
 				}
 				System.out.println();
 			}
 			rs.close();
 			
 			//Regular statement
 			//This does not work
 			Statement stmt = connectionOBJ.createStatement();
 			java.sql.ResultSet rs1 = stmt.executeQuery(query);
 			System.out.print("Regular Statement Results:");
 			ResultSetMetaData metadata1 = rs1.getMetaData();
 			int count1 = metadata1.getColumnCount();
 			int rowcount1 = 0;
 			while (rs1.next()){
 				System.out.print(++rowcount1+". ");
 				for (int j=1; j<=count1; j++){
 					System.out.print(rs1.getString(j) + "  ");
 				}
 				System.out.println();
 			}
 			
 			connectionOBJ.close();
			
		}
		catch (Exception ex) {
 			System.out.println("Exception: " +
                               ex.getClass().getName()
                               + ": " + ex.getMessage());
		}
		
	}
	
}
