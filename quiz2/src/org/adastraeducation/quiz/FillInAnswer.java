package org.adastraeducation.quiz;

public class FillInAnswer extends Answer{
	private String answer;
	private boolean correct;
	private String response;
	
	public FillInAnswer(String answer, boolean correct) {
		this.answer = answer;
		this.correct = correct;
		this.response = null;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return this.answer;
	}
	public void setCorrect(String correct) {
		this.correct = correct.equals("t");
	}
	public boolean getCorrect() {
		return this.correct;
	}
	public void setReminder(String reminder) {
		this.response = reminder;
	}
	public String getReminder() {
		return this.response;
	}
	
	public void writeXML(StringBuilder b) {
		b.append("\n<A correct=\"").append(correct? "t" : "f").append("\">").append(answer).append("</A>");
	}
}
