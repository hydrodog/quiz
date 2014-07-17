package org.adastraeducation.quiz.equation;

import java.util.ArrayList;

public class Stack<T>{
	private ArrayList<T> stack;
	private int cur;
	
	public Stack(){
		stack = new ArrayList<T>();
		cur=0;
	}
	
	public void push(T c){
		stack.add(c);
		cur++;
	}
	
	public T pop(){
		T r = stack.get(cur-1);
		stack.remove(cur-1);
		cur--;
		return r;
	}
	
	public int getCur(){
		return cur;
	}
	
}
