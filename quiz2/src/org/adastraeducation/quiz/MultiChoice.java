package org.adastraeducation.quiz;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * MultiChoice is the class to deal with Multiple Choice, it also includes standard choices which can be reused 
 * 
 * @author qiangzhang
 * date 6/30/2014 
 */

public class MultiChoice extends Question {
	private Answer[] answers;
	private StdChoice stdchoice;
	private boolean imgAnswer;

	public MultiChoice() {}
	
	public MultiChoice(String title, String level, String question, String imgQuestion, String[] answers, String imgAnswer){
		super(title, level, question, imgQuestion.equals("t"));
		this.answers = new Answer[answers.length/2];
		for(int i = 0, j = 0; i < this.answers.length; i++,j+=2)
		{
			this.answers[i] = new Answer(answers[j], answers[j+1].equals("t"));
		}
		this.imgAnswer = imgAnswer.equals("t");
	}
	public MultiChoice(String title, String level, String question, String imgQuestion, StdChoice c){
		super(title, level, question, imgQuestion.equals("t"));
		stdchoice = c;
		//TODO: Make sure imgAnswer is correct!
	}	
	public static void testHTMLAndXML(Quiz quiz){
		String []ans1 = { "A dynosaur", "t", "A fish", "f", "A primate", "f", "A mammal", "f"};
		MultiChoice m1 = new MultiChoice("dynosaur", "1", "What is a Tyranosaurus Rex?", "f", ans1, "f"); 
		
		String []ans2 = { "Tyranosauraus.jpg", "t", "komododragon.jpg", "f", "shark.jpg", "f", "apatosaurus.jpg", "f"};
		MultiChoice m2 = new MultiChoice("dynosaur2", "1", "Identify which of the following is Tyranosauraus Rex", "f", ans2, "t");
		
		String[] a1 = {"Strongly Disagree", "1", "Disagree", "2", "No Opinion", "3", "Agree", "4", "Strongly Agree", "5"};
		StdChoice standardchoice1 = new StdChoice(a1);
		MultiChoice m3 = new MultiChoice("poll1", "1", "I enjoy studying computational complexity.", "f", standardchoice1);
		
		MultiChoice m4 = new MultiChoice("poll1", "1", "I enjoy eating Chinese food.", "f", standardchoice1);
		
		String[] a2 = {"O(1)", "1", "O(log_2 n)", "logn", "O(sqrt n)", "sqrtn", "O(n)", "n", "O(n log_2 n)", "nlogn", "O(n^2)", "n^2", "O(n^3)", "n^3"};
		StdChoice standardchoice2 = new StdChoice(a2);
		MultiChoice m5 = new MultiChoice("complexity", "1", "The complexity of insertion sort is:", "f", standardchoice2);

		quiz.addQuestion(m1);
		quiz.addQuestion(m2);
		quiz.addQuestion(m3);
		quiz.addQuestion(m4);
		quiz.addQuestion(m5);

	}
	
	public String getTagName() { return "MultiChoice"; }
	
	public void writeHTMLContent(StringBuilder b) {
		//b.append("<form id=\"").append(id).append("\" name=\"").append("q1").append("\">");
		if(this.stdchoice == null)
			for (int i = 0; i < this.answers.length; i++) {
				if (imgAnswer)
					b.append(this.answers[i].graphanswer());
				else 
					b.append(this.answers[i].textanswer());
			}
		else
			this.stdchoice.writeHTML(b);
	}
	
	public void writeXMLContent(StringBuilder b) {
		writeOptAttr(b, "imgAnswer", imgAnswer);
		endTagWriteQuestion(b);
		if(this.stdchoice == null)
			for(int i = 0; i < answers.length ; i++)
			{
				b.append(answers[i].writeXML());
			}
		else
			this.stdchoice.writeXML(b);
	}
}

