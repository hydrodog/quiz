package org.adastraeducation.quiz;

public class FillInAnswer{
	private String answerRegex;
	private boolean correct;
	private double score;
	//private String response;
	
	public FillInAnswer() {
		this.answerRegex = ".*";
		this.correct = true;
		//this.response = null;
	}
	
	public FillInAnswer(String answerRegex, boolean correct) {
		this.answerRegex = answerRegex;
		this.correct = correct;
		//this.response = null;
	}
	
	public void setAnswerByKeyword(String keyword) {
		this.answerRegex = ".*(\\s+)" + keyword + "(\\s+).*";
	}
	public String getAnswerRegex() {
		return this.answerRegex;
	}
	public void setCorrect(String correct) {
		this.correct = correct.equals("t");
	}
	public boolean getCorrect() {
		return this.correct;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getScore() {
		return this.score;
	}
//	public void setReminder(String reminder) {
//		this.response = reminder;
//	}
//	public String getReminder() {
//		return this.response;
//	}
	
	public void writeXML(StringBuilder b) {
		b.append("\n<A correct=\"").append(correct? "t" : "f").append("\">").append(answerRegex).append("</A>");
	}
}
