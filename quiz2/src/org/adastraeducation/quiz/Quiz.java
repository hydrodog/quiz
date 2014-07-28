package org.adastraeducation.quiz;
import java.io.*;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.adastraeducation.quiz.test.*;

public class Quiz {
	private ArrayList<Question> questions;
	
	public Quiz() {
		questions = new ArrayList<Question>();
	}
	/*
	 * get questionlist from quiz
	 */
	public ArrayList<Question> getQuestion(){
		return questions;
	}
	
	/*
	 * Temporarily, load the test quiz so we can display in JSP
	 * Eventually, load from database
	 */
	public void load(int id) {
		/*
		 * TODO: Database logic looks something like this
		 * Open questions: design of database?
		 * Move all database code out of individual classes?
		Connection conn = DatabaseMgr.getConnection();
		PreparedStatement getQuizInfo = conn.prepareStatement
				("SELECT id, name, instructions FROM QUIZZES WHERE id = ?");
		getQuizInfo.setInt(1, id);
		PreparedStatement getQuizQuestions = conn.prepareStatement
				("SELECT qid, title, question, imgQuestion FROM Questions,QuestionMatch " +
		"WHERE quizid = ? AND Questions.qid = QuestionsMatch.qid");
		getQuizQuestions.setInt(1, id);
		*/
		addToQuiz();
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
	public void writeHTMLContents(StringBuilder b) {
		b.append("<div style='background-color: #007020;'>");
		b.append("<form method=\"GET\" action=\"gradeQuiz.jsp\">");

		for (Question q : questions) {
			q.writeHTML(b);
		}
		b.append("<input type=\"submit\" />");
		b.append("</form>");
		b.append("<div>");
	}
	private void writeHTML(String filename) {
		StringBuilder html = new StringBuilder();
		writeHTMLContents(html);
		try {
 			PrintWriter pw = new PrintWriter(filename);
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
			PrintWriter pw = new PrintWriter(filename);
			pw.println(xml);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addToQuiz() {
		MultiChoice.testHTMLAndXML(this);
		FillIn.testHTMLAndXML(this);
		Match.testHTMLAndXML(this);
		//MultiAnswer.testHTMLAndXML(this);
		Equation.testHTMLAndXML(this);
		CodeQuestionTest.buildTestQuestions(this);
	}
	public static Quiz buildSampleQuiz() {
		Quiz quiz = new Quiz();
		quiz.addToQuiz();
		return quiz;
	}
	
	private static void testAllQuestionsTogether() {
		Quiz quiz = buildSampleQuiz();
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
			String[] classes = {"MultiChoice", "FillIn", "Match", "Equation"} ;//, "MultiAnswer"
			for (String className : classes) {
				Quiz quiz = new Quiz();
				Class c = Class.forName("org.adastraeducation.quiz." + className);
				Method m = c.getMethod("testHTMLAndXML", Quiz.class);
				m.invoke(c, new Object[]{quiz});				
				quiz.writeHTML("html/" + className + ".html");
				quiz.writeXML("html/" + className + ".xml");
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
