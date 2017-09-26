package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	public static Connection getConnection(){
		Connection conn= null;
		
		try{
			String url= "jdbc:apache:commons:dbcp:minishop";
			conn= DriverManager.getConnection(url);
			
			//System.out.println(conn);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(ResultSet rs){
		if(rs!= null){
			try{
				rs.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	public static void close(PreparedStatement pstmt){
		if(pstmt!= null){
			try{
				pstmt.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	public static void close(Connection conn){
		if(conn!= null){
			try{
				conn.close();
			}catch(SQLException e){
				
			}
		}
	}
}
