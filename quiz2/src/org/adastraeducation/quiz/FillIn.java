package org.adastraeducation.quiz;

import java.io.FileNotFoundException;
//import java.io.PrintWriter;
import java.util.ArrayList;

public class FillIn extends Question{
	
	private ArrayList<FillInAnswer> fillInAnswers;
//	private String userAnswer;

	public FillIn(String title, String level, String question, boolean imgQuestion) {
		super(title, level, question, imgQuestion);
		this.fillInAnswers = new ArrayList<FillInAnswer>();
	}
	
//	public void addFillInAnswer(String answerList) {}
//	public void deleteFillInAnswer(int index) {}
//	public void printFillInAnswer(int index) {
//		fillInAnswers[index].writeHTML(boolean withReminder);
//	}
	
	public String getTagName() { return "FillIn"; }
//	public void setUserAnswer(String userAnswer) {
//		this.userAnswer = userAnswer;
//	}

	public void writeHTMLContent(StringBuilder b) {
		b.append("<input type=\"text\" ");
		super.writeAttr(b, "name", getName());
		b.append(">\n");
	}
	public void writeXMLContent(StringBuilder b) {
		super.endTagWriteQuestion(b);
		for(int i = 0; i < this.fillInAnswers.size(); i++) {
			this.fillInAnswers.get(i).writeXML(b);
		}
	}
	public static void testHTMLAndXML(Quiz quiz) {
		FillIn f1 = new FillIn("arithmetic", "1", "What is 30 x 4?", false);
		FillInAnswer a1 = new FillInAnswer();
		a1.setAnswerByKeyword("120");
		a1.setScore(5);
		f1.fillInAnswers.add(a1);
		quiz.addQuestion(f1);
	}
	
	public double gradeUserAnswer(String userAnswer) {
		double score = 0;
		for(FillInAnswer f: this.fillInAnswers) {
			if(userAnswer.matches(f.getAnswerRegex())) {
				score = f.getScore();
			}
		}
		return score;
	}
//	public static void main(String[] args) {
//		StringBuilder b = new StringBuilder();
//		String []answerList = { "120", "t", "120.0", "t", "12", "f"};
//		FillIn f1 = new FillIn("8", "arithmetic", "1", "What is 30 x 4?", answerList); 
//		
//		f1.writeHTML(b);
//		
//		try {
//			PrintWriter pw = new PrintWriter("FillIn.html");
//			pw.println(b);
//			pw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(b);
//			
//		b.setLength(0);
//		f1.writeXML(b);
//		try {
//			PrintWriter pw = new PrintWriter("FillIn.xml");
//			pw.println(b);
//			pw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public boolean isCorrect(String ans) {
		for(FillInAnswer fa: this.fillInAnswers) {
			if(ans.matches(fa.getAnswerRegex())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double gradeIt(String ans) {
		double result = 0;
		for(FillInAnswer fa: this.fillInAnswers) {
			if(ans.matches(fa.getAnswerRegex())) {
				double temp = fa.getScore();
				if(temp > result) {
					result = temp;
				}
			}
		}
		return result;
	}


}
