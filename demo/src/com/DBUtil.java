package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	Connection con=null;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	String password = "root";
	String user = "root";

	public  Connection getConnection() {
				try {
					Class.forName(driver);
					try {
						con = DriverManager.getConnection(url,user,password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return con;
	}
	public void close(ResultSet rs){
		   if(rs!=null){
			   try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	}
   public void close(PreparedStatement ps){
	   if(ps!=null){
		   try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   public void close(Connection conn){
	   if(conn!=null){
		   try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
}
}
