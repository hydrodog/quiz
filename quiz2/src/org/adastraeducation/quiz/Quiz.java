package org.adastraeducation.quiz;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class Quiz {
	private ArrayList<Question> questions;
	
	public Quiz() {
		questions = new ArrayList<Question>();
	}
	
	/*
	 * Write out the HTML for a quiz.  The quiz is in charge of 
	 * generating the form, and making sure that each question 
	 * knows its unique name (could be q1, q2, q3...)
	 */
	
	public void writeHTML(StringBuilder b) {
		b.append("<form method=\"get\" action=\"somewhere\"");
		// this is the new iteration style, you can use either.
		//for(Question q : questions) {
		//	q.writeHTML(b);
		//}
		
		for(int i = 0; i < questions.size(); i++) {
			questions.get(i).writeHTML(b);
		}
		b.append("</form>\n");
	}
	
	/*
	 * Write out the XML to represent an entire quiz
	 * This is not for display, just to save for purposes of export to other systems
	 * or backup.
	 */
	public void writeXML(StringBuilder b) {
		b.append("<Quiz>");
		for(Question q : questions) {
			q.writeXML(b);
		}
		
		b.append("</Quiz>");
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
		q.setName("q" + questions.size());
	}
	
	private void writeHTML(String filename) {
		StringBuilder html = new StringBuilder();

		html.append("<!DOCTYPE html>\n<html><body>");
		writeHTML(html);
		html.append("</body></html>");

		try {
 			PrintWriter pw = new PrintWriter("html/quiz.html");
 			pw.println(html);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void writeXML(String filename) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" ?>");
		writeXML(xml);
		try {
			PrintWriter pw = new PrintWriter("html/quiz.xml");
			pw.println(xml);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testAllQuestionsTogether() {
		Quiz quiz = new Quiz();
		
		MultiChoice.testHTMLAndXML(quiz);
		FillIn.testHTMLAndXML(quiz);
		Match.testHTMLAndXML(quiz);
		//MultiAnswer.testHTMLAndXML(quiz);
		// Equation.testHTMLAndXML(quiz);
		quiz.writeHTML("html/quiz.html");
		quiz.writeXML("html/quiz.xml");
	}
	public static void testQuestionsForEachClass() {
		// first conduct unit test for each class

		/*
		 * The code below implements the 
		 * equivalent of these four lines for each class
		 *
		Quiz quiz = new Quiz();
		MultiChoice.testHTMLAndXML(quiz);
		quiz.writeHTML("html/MultiChoice.html");
		quiz.writeXML("html/MultiChoice.html");
	
		quiz = new Quiz();
		Equation.testHTMLAndXML(quiz);
		quiz.writeHTML("html/MultiChoice.html"); this would be an error!
		quiz.writeXML("html/MultiChoice.html");
		 */
		
		
		try {		
			String[] classes = {"MultiChoice", "FillIn", "Match"} ;//, "MultiAnswer"
			for (String className : classes) {
				Quiz quiz = new Quiz();
				Class c = Class.forName("org.adastraeducation.quiz." + className);
				Method m = c.getMethod("testHTMLAndXML", Quiz.class);
				m.invoke(c, new Object[]{quiz});				
				quiz.writeHTML("html/" + c.getName() + ".html");
				quiz.writeXML("html/" + c.getName() + ".xml");
			}
		} catch (Exception e) {
				e.printStackTrace(); // class not found? Some other problem?
		}		
	}

	public static void main(String []args){	
		testAllQuestionsTogether();
		testQuestionsForEachClass();
	}
}
