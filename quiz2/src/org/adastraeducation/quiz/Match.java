package org.adastraeducation.quiz;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Match Question is kind of question that to choose the right answer to match what the question describes.
 * 
 * @author Chang Li
 * created by date 7/15/2014 
 * 1st modified 7/17/2014
 */

public class Match extends Question {
	private String[] answers;
	private boolean imgAnswers;


	private String[] questions;
	private boolean imgQuestions;

	public Match() {}

	public Match(String title, String level, String question, String imgQuestion,
			String[] questions, String imgQuestions,String[] answers, String imgAnswers){
		super(title, level, question, imgQuestion.equals("t"));
		this.questions= questions;
		this.imgQuestions= imgQuestions.equals("t");
		this.answers = answers;		
		this.imgAnswers = imgAnswers.equals("t");
	}

	public static void testHTMLAndXML(Quiz quiz){
		String[] ans1 = { "Choose..","Fish", "Dynosaur", "Crocodilia"};
		String[] questions1 = {"tyrannosaurus.jpg", "shark.jpg", 
				"alligator.jpg", "crocodile.jpg",
				"apatosaurus.jpg", "guppy.jpg"};
		Match m1 = new Match("Match Question 1", "1", 
				"choose the right answer to match the picture on the left", "f",
				questions1,"t", ans1, "f"); 
		String[] questions2 = {"Tyrannosaurus", "Shark", "Alligator", 
				"Crocodile", "Apatosaurus", "Guppy"};
		Match m2 = new Match("Match Question 2", "1", 
				"choose the right answer to match the description on the left", "f",
				questions2, "f", ans1, "f");
		quiz.addQuestion(m1);
		quiz.addQuestion(m2);
	}

	public void writeHTMLContent(StringBuilder b) {
		b.append("<ol type=\"A\">");
		for (int i = 0; i < this.questions.length; i++) {
			b.append("<li/>");

			if (imgQuestions)
				b.append("<br/><img src=\"../img/temp/")
					.append(questions[i]).append("\" width=\"200\" height=\"140\"")
				.append(">");//modification
			else 
				b.append("<p1/>").append(questions[i]);//modification
			
			b.append("<select name=\"").append(getName()).
			append("\">");
			for(int j = 0; j < this.answers.length; j++) {		
				if (imgAnswers)
					b.append("");//TODO:if the answers are pictures.
				else {
					b.append("<option value=\"").append(j).append("\">").append(answers[j])//modificaiton
					.append("</option>");				
				}		
			}
			b.append("</select>");
		}
	   b.append("</ol>");
	}
	
	public String getTagName() { return "Match"; }

	public void writeXMLContent(StringBuilder b) {
		writeOptAttr(b, "imgAnswers", imgAnswers);
		endTagWriteQuestion(b);
		for(int j = 0; j < questions.length ; j++)
			b.append("Q m=\" \">").append(questions[j]).append("/Q");
		for(int i = 0; i < answers.length ; i++) 
			b.append("A m=\" \">").append(answers[i]).append("/A");	//TODO: the value of m?	
	}
}
