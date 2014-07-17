package org.adastraeducation.quiz.equation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation extends Question{
	
	private Expression func;
	
	public Equation(String id, String name, int level, Expression func){
		super(id,name,level);
		this.func = func;
	}

	@Override
	public void writeHTML(StringBuilder b) {
		// TODO Auto-generated method stub
		b.append("<p>").append(this.getId()).append(":   ");
		func.infix(b);
		b.append("<p><br>");
		b.append("<form id=\"").append(this.getId()).append("\"method=get\" ").append(">");
		b.append("<input type=\"text\" name=\"").append(this.getName()).append("\"><br>");
		b.append("<input type=\"submit\" value=\"submit\"></form>");
	}

	@Override
	public void writeXML(StringBuilder b) {
		b.append("<Equation id=\"").append(this.getId())
		.append("\" name=\"").append(this.getName())
		.append("\" level=\"").append(this.getLevel()).append("\">");
		b.append("<Question question=\">");
		func.infix(b);
		b.append("\"></Question>");
		b.append("</Equation>");
		
	}
	
	public void writeDatabase(){
		
		String driver ="org.postgresql.Driver";
		String url="jdbc:postgresql://localhost:5432/postgres";
		String userName="postgres";
		String password="65254408";
		
		Connection conn=null;
		Statement stmt=null;
		String id =this.getId();
		String name = this.getName();
		int level = this.getLevel();
		StringBuilder b = new StringBuilder();
		func.infix(b);
		String question = b.toString();
		double result = func.eval();		
		//System.out.println(question);
		//System.out.println(result);
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
	
	public static Expression parseInfix(String s){
		
		return null;
	}
	
	public static Expression parseRPN(String[] s){
		Stack<Expression> stack = new Stack<Expression>();
		String regex = "^[0-9]+$";
		for(int i=0;i<s.length;i++){
			String temp = s[i];
			if(Functions.functions.contains(temp)){
				Expression op1 ;
				Expression op2 ;
				switch(temp){
				case "+": 
					op2=stack.pop();
					op1=stack.pop();
					stack.push( new Plus(op1,op2));break;
				case "-": 
					op2=stack.pop();
					op1=stack.pop();
					stack.push( new Minus(op1,op2));break;
				case "*": 
					op2=stack.pop();
					op1=stack.pop();
					stack.push( new Multi(op1,op2));break;
				case "/": 
					op2=stack.pop();
					op1=stack.pop();
					stack.push( new Div(op1,op2));break;
				default:break;
				}

			}
			else{
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(temp);
				if(m.matches()){
					double x = Double.parseDouble(temp);
					stack.push(new Var(temp,x));
				}
				else{
					stack.push(new Var(temp,1,3,20));
				}
			}
		}
		return stack.pop();
		
	}
	
	public static void main(String[] args){
	
		String[] s = {"3","x","2","*","+"};
		Expression e = Equation.parseRPN(s);
		StringBuilder b = new StringBuilder();
		e.infixReplaceVar(b);
		System.out.println(b.toString());
	}
	/*
	public static void main(String[] args){
		Var x = new Var("x",1,2,20);
		Var y = new Var("y",5,3,36);
		
		Plus func = new Plus(x,y);
		Equation eq = new Equation("103","Plus",1,func);
		
		StringBuilder b = new StringBuilder();
		b.append("<%@ page contentType=\"text/html\" pageEncoding=\"UTF-8\" %>");
		b.append("<html><head>");
        b.append("<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"UTF-8\">");
        b.append("<title>Equation</title></head>");
        b.append("<body>");
        eq.writeHTML(b);
        b.append("</body></html>");
		try {
			PrintWriter pw = new PrintWriter("/Users/wyz/Documents/tomcat/webapps/Equation/equation.jsp");
			pw.println(b);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.setLength(0);
		b.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		b.append("\n");
		b.append("<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN\"");
		b.append("\n");
		b.append("\"http://java.sun.com/dtd/web-app_2_3.dtd\">");
		b.append("\n");
		eq.writeXML(b);
		try {
			PrintWriter pw = new PrintWriter("/Users/wyz/Documents/tomcat/webapps/Equation/WEB-INF/web.xml");
			pw.println(b);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//eq.writeDatabase();
	}
	*/
}
