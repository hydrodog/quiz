package org.adastraeducation.quiz;

public class Answer {
	private String ans;
	private boolean correct;
	public Answer(){
		ans = null;
		correct = false;
	}
	public Answer(String ans, boolean correct){
		this.ans = ans;
		this.correct = correct;
	};
    public void setAnswer(String answer){
		ans = answer;
	}
	public String getAnswer(){
		return ans;
	}
	public void setCorrect(boolean correct){
		this.correct = correct;
	}
	public  boolean getCorrect(){
		return correct;
	}
	public String textanswer(){
		return "<input type=\"radio\" name=\"dynosaur1\">" + ans + "<br>";
		
	}
	public String graphanswer(){
		return "<input type=\"radio\" name=\"dynosaur2\"><img src=\"../img/" + ans + "\" alt=\"" + ans + "\" width=\"300\" height=\"150\"><br>";
	}
	public String writeXML(){
		return "<A correct=\"" + correct + "\">" + ans + "</A>";
	}
}
