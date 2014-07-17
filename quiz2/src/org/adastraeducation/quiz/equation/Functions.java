package org.adastraeducation.quiz.equation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class Functions {
	public static final ArrayList<String> functions = new ArrayList<String>(
			Arrays.asList("+","-","*","/","abs","asin","atan","cos","div","minus"
					,"multi","neg","sin","sqrt","tan"));
	
	public static final HashMap<String,Integer> level = new HashMap<String,Integer>() {
		{
			put("+",1);
			put("-",1);
			put("*",2);
			put("/",2);
		}
	};
}