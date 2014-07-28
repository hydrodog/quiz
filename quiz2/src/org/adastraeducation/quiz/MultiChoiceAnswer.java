package org.adastraeducation.quiz;

public class MultiChoiceAnswer {
	private String ans;
	private boolean correct;
	private double score;
	private String index;
	
	public MultiChoiceAnswer(){
		ans = null;
		correct = false;
		score = 0;
	}
	public MultiChoiceAnswer(String ans, boolean correct){
		this.ans = ans;
		this.correct = correct;
		this.setScore(1);
	};
	
	/*
	 * Set and Get methods.
	 */
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
	public void setScore(double score) {
		if(this.getCorrect()) {
			this.score = score;
		}
	}
	public double getScore() {
		return this.score;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String textanswer(){
		return "<input type=\"radio\" name=\"dynosaur1\" value=\"" + this.getIndex() +"\">" + ans + "<br>";
	}
	public String graphanswer(){
		return "<input type=\"radio\" name=\"dynosaur2\" value=\"" + this.getIndex() +"\"><img src=\"../img/" + ans + "\" alt=\"" + ans + "\" width=\"300\" height=\"150\"><br>";
	}
	public String writeXML(){
		return "<A correct=\"" + correct + "\">" + ans + "</A>";
	}
}
