package org.adastraeducation.quiz;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.adastraeducation.quiz.equation.Abs;
import org.adastraeducation.quiz.equation.Asin;
import org.adastraeducation.quiz.equation.Atan;
import org.adastraeducation.quiz.equation.Cos;
import org.adastraeducation.quiz.equation.Div;
import org.adastraeducation.quiz.equation.Expression;
import org.adastraeducation.quiz.equation.Functions;
import org.adastraeducation.quiz.equation.Minus;
import org.adastraeducation.quiz.equation.Multi;
import org.adastraeducation.quiz.equation.Neg;
import org.adastraeducation.quiz.equation.Plus;
import org.adastraeducation.quiz.equation.Sin;
import org.adastraeducation.quiz.equation.Sqrt;
import org.adastraeducation.quiz.equation.Stack;
import org.adastraeducation.quiz.equation.Tan;
import org.adastraeducation.quiz.equation.Tree;
import org.adastraeducation.quiz.equation.Var;

/**
 * Present equations with random variables.
 * It has two ways to parse the equations in string[]. One is in infix, and the other is in the RPN.
 * @author Yingzhu Wang
 *
 */

public class Equation extends Question {

	private Expression func;

	public Equation(String title, String level, String question){
		super(title, level, question, false);
	}
	
	public Equation(String title, String level, String question, Expression func){
		super(title, level, question, false);
		this.func = func;
	}
	
	public void setExpression(Expression e){
		this.func=e;
	}
	

	public String getTagName() { return "Equation"; }
	@Override
	public void writeHTMLContent(StringBuilder b) {
		func.infix(b);
		b.append("<p><br>");
		b.append("<input type=\"text\" name=\"").append(this.getName()).append("\"><br>");
	}

	@Override
	public void writeXMLContent(StringBuilder b) {
		b.append("<Question question=\">");
		func.infix(b);
		b.append("\"></Question>");
	}

	public  Expression parseInfix(ArrayList<String> s){
		Tree t = new Tree(s);
		ArrayList<String> rpn = t.traverse();

		return parseRPN(rpn);
	}

	public  Expression parseRPN(ArrayList<String> s){
		Stack<Expression> stack = new Stack<Expression>();
		String regex = "^[0-9]+$";
		for(int i=0;i<s.size();i++){
			String temp = s.get(i);
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
				case "sin":
					op1=stack.pop();
					stack.push(new Sin(op1));break;
				case "cos":
					op1=stack.pop();
					stack.push(new Cos(op1));break;
				case "tan":
					op1=stack.pop();
					stack.push(new Tan(op1));break;
				case "abs":
					op1=stack.pop();
					stack.push(new Abs(op1));break;
				case "Asin":
					op1=stack.pop();
					stack.push(new Asin(op1));break;
				case "Atan":
					op1=stack.pop();
					stack.push(new Atan(op1));break;
				case "neg":
					op1=stack.pop();
					stack.push(new Neg(op1));break;
				case "sqrt":
					op1=stack.pop();
					stack.push(new Sqrt(op1));break;	
				default:break;
				}

			}
			//deal with the space
			else if(temp.equals(""))
				;
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
	
	public ArrayList<String> parseQuestion(String question){
		
		ArrayList<String> s = new ArrayList<String>();
		int beginIndex=0;
		
		for(int i=0;i<question.trim().length();i++){
			if(question.charAt(i)>32&&question.charAt(i)<48)
			{
				if(beginIndex!=i)
				    s.add( question.substring(beginIndex, i));
				s.add(String.valueOf(question.charAt(i)));
				beginIndex = i+1;
			}
		}
		if(beginIndex!=question.length())
		    s.add( question.substring(beginIndex, question.length()));
		
		return s;
	}

	public static void testHTMLAndXML(Quiz quiz){
		Var x = new Var("x",1,3,10);
		Var y = new Var("y",1,3,10);

		Equation e1 = new Equation("plus","2","",new Plus(x,y)); 

		quiz.addQuestion(e1);
	}

	public static void main(String[] args){
		Var x = new Var("x",1,3,10);
		Var y = new Var("y",1,3,10);

		Equation e1 = new Equation("plus","2",""); 
		
		String q = "2+3*sin(x)-6/4";
		ArrayList<String> temp = e1.parseQuestion(q);
		Expression e = e1.parseInfix(temp);
		e1.setExpression(e);
		StringBuilder b = new StringBuilder();
		e.infix(b);
		System.out.println(b);
	}

	@Override
	public boolean isCorrect(String ans) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double score() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
