package org.adastraeducation.quiz.equation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class View {
	
	private String driver;
	private String url;
	private String userName;
	private String password;
	private Connection conn=null;
	private Statement stmt=null;
	
	public View(){	
		driver ="org.postgresql.Driver";
		url="jdbc:postgresql://localhost:5432/postgres";
		userName="postgres";
		password="65254408";
	}
	
	public void viewAll(){
		try{
			Class.forName(driver);
			System.out.println("Driver Successfully!");
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFoundException");
		}
		try{
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("connect database successfully!");
			stmt = conn.createStatement();
			String sqlSelect="select * from equation";
			ResultSet rs=stmt.executeQuery(sqlSelect);
			while(rs.next()){
						System.out.println(rs.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void viewOne(String id){
		try{
			Class.forName(driver);
			System.out.println("Driver Successfully!");
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFoundException");
		}
		try{
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("connect database successfully!");
			stmt = conn.createStatement();
			String sqlSelect="select * from equation where id='"+id+"'";
			ResultSet rs=stmt.executeQuery(sqlSelect);
			while(rs.next()){
						System.out.println(rs.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		View v= new View();
		v.viewAll();
	}

}
