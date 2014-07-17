package org.adastraeducation.quiz.equation;

import java.util.ArrayList;

//the Node used to construct the tree
class Node<T> {
	 T value;
	 int level;
	 Node<T> left;
	 Node<T> right;
	
	public Node(T value,int level){
		this.value=value;
		this.level=level;
		left=null;
		right=null;
	}
}

//pares the infix into a tree which will help to construct an array to RPN
public class Tree{
	private Node<String> head;
	private int len;
	
	public Tree(String[] s){
		head=null;
		this.len=s.length;
		int i=0;
		int hierarchy=1;
		this.insert(s, i, hierarchy);
	}
	
	public void insert(String[] s, int i,int hierarchy){
		String use = s[i];
		int level=0;      //set the level for the operands
		//set level
		if(Functions.functions.contains(use)){
			level=Functions.level.get(use);
		}
		else if(use.equals("(")){
			hierarchy++;
		}
		else if(use.equals(")")){
			hierarchy--;
		}
		else{
			level=99;
		}
		//start to insert
		Node<String> node = new Node<String>(use,level);
		if(head==null){
			head = node;
		}
		else{
			Node<String> cur = head;
			Node<String> previous = null;
			while(node.level>cur.level){
				previous=cur;
				cur=cur.right;
			}
			if(cur==null){
				previous.right=node;
			}
			else{
				previous.right=node;
				node.left=cur;
			}
		}
		i++;
		if(i>s.length-1)
			return;
		else
			insert(s,i,hierarchy);
	}
	
	//get the traverse data
	public String[] traverse(){
		ArrayList<String> array = new ArrayList<String>();
		putdata(head,array);
		String[] result =array.toArray(new String[array.size()]);
		return result;
	}
	
	//traverse the tree
	public void putdata(Node<String> h,ArrayList<String> array){
		if(h==null)
			return;
		else{
			putdata(h.left,array);
			putdata(h.right,array);
			//System.out.println(h.value);
			array.add(h.value);
		}
	}
	
	public static void main(String[] args){
		
		String[] s ={"1","+","2","*","3"};
		
		Tree t= new Tree(s);
		String[] st = t.traverse();
		for(int i=0;i<5;i++){
			System.out.print(st[i]);
		}
	}
}