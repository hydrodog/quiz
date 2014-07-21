package org.adastraeducation.quiz;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Stack;

public class DatabaseMgr {
	private Stack<Connection> connections;
	static {
		Properties p = new Properties(); // TODO: LOAD PROPERTIES!
		p.load(new FileInputStream("conf/quiz.properties"));;
		String driver = p.getProperty("driver");
			String driver ="org.postgresql.Driver";
			String url="jdbc:postgresql://localhost:5432/postgres";
			String userName="postgres";
			String password="65254408";
			Class.forName(driver);
			final int connections = p.getProperty("connections");
			
			for (int i = 0; i < connections; i++)
				;; // create connections in list...
			Connection conn=null;
			Statement stmt=null;
			String id = getId() +"";
			String name = this.getName();
			int level = this.getLevel();
			StringBuilder b = new StringBuilder();
			func.infix(b);
			String question = b.toString();
			double result = func.eval();		
			//System.out.println(question);
			//System.out.println(result);
			try{
				
				System.out.println("Driver Successfully!");
			}catch(ClassNotFoundException e){
				System.err.print("ClassNotFoundException");
			}
			try{
				conn=DriverManager.getConnection(url,userName,password);
				System.out.println("connect database successfully!");
				stmt = conn.createStatement();
						
				String sqlSelect="INSERT INTO equation VALUES("
				+"'"+id+"','"+name+"',"+level+",'"+question+"',"+result+")";
				
				stmt.executeQuery(sqlSelect);
							
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
		
		
	}

/**
 * use synchronized(obj) to allow only one user at a time 
 * if this code is multithreaded
 * @return
 */
	private Object lock = new Object();
	public static Connection getConnection() {
		synchronized(lock) {
			if (connections.isEmpty()) {
			
			}
			return connections.pop();
		}
	}

	/*
	 * Before giving back the connection, you must CLOSE your statement.
	 */
	public void returnConnection(Connection c) {
		synchronized(lock) {
			stack.push(c);
		
		}
	}

}
