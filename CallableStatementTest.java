/*
 * JDBCQuery.java 
 * 
 */

import java.io.*;
 
import java.sql.*;

public class CallableStatementTest{
  
    public static void main(String[] args){
 		try {
			String connectionurl = "jdbc:Cache://172.16.208.77:1972/SAMPLES";
			String driver = "com.intersys.jdbc.CacheDriver";
			String username = "_SYSTEM";
			String password = "SYS";
			String call = "call Sample.SP_Sample_By_Name(?)";

			System.out.print("Connecting to server...");
 			Connection conn = DriverManager.getConnection(connectionurl,username,password);
			System.out.println("connected.");

			CallableStatement statement = conn.prepareCall(call);
			statement.setString(1, "J");
 			java.sql.ResultSet result = statement.executeQuery();
			System.out.println("Results:");
 			ResultSetMetaData metadata = statement.getMetaData();
 			int count = metadata.getColumnCount();
			int rowcount=0;
 			while (result.next()) {
				System.out.print(++rowcount+". ");
 				for (int i=1; i<=count; i++) {
 					System.out.print(result.getString(i) + "  ");
 				}
 				System.out.println();
 			}
 			statement.close();
 			conn.close();
 		} catch (Exception ex) {
 			System.out.println("Exception: " +
                               ex.getClass().getName()
                               + ": " + ex.getMessage());
 		}
 	}    
}