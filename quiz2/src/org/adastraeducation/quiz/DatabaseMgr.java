package org.adastraeducation.quiz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Stack;

public class DatabaseMgr {
	private static Stack<Connection> connections = new Stack<Connection>();
	private static Object lock = new Object();

	static {
		Properties p = new Properties(); // TODO: LOAD PROPERTIES!
		try {
			p.load(new FileInputStream("conf/postgres.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = p.getProperty("driver");
		String url = p.getProperty("url");
		String userName = p.getProperty("userName");
		String password = p.getProperty("password");
		//System.out.println(password);
		final int connectionsCount = Integer.parseInt(p.getProperty("connectionsCount"));

		Connection conn=null;

		try{
			Class.forName(driver);
			System.out.println("Driver Successfully!");
			for (int i = 0; i < connectionsCount; i++){
				conn = DriverManager.getConnection(url,userName,password);
				System.out.println("Connect Successfully!");
				// create connections in list...
				connections.push(conn);
			}
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFoundException");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

	/**
	 * use synchronized(obj) to allow only one user at a time 
	 * if this code is multithreaded
	 * @return
	 */
	public static Connection getConnection() {
		synchronized(lock) {
			if (connections.isEmpty()) {
				System.out.println("Stack Empty!");
				return null;
			}
			return connections.pop();
		}
	}

	/*
	 * Before giving back the connection, you must CLOSE your statement.
	 */
	public static void returnConnection(Connection c) {
		synchronized(lock) {
			connections.push(c);
		}
	}
	
	
	//do the select query
	public static ResultSet select(String sql){
		Connection conn = DatabaseMgr.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
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
		returnConnection(conn);
		return rs;
	}
	
	//do the update query like insert, delete or update
	public static void update(String sql){
		Connection conn = DatabaseMgr.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
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
		returnConnection(conn);
	}

}
