/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adastraeducation.quiz;
/**
 *
 * @author zhangchenyi
 */
public class MultiAnswer extends Question {
    private final MultiAnswer_Answer answers[];
    
    public MultiAnswer(String title, String question, String[] answers, String imgAnswer) {
    	super(title, "1", question, false);
         
         this.answers = new MultiAnswer_Answer[answers.length / 2];
         for (int i = 0; i < answers.length; i += 2) {
             this.answers[i/2] = new MultiAnswer_Answer(answers[i], answers[i+1].equals("t"));
         } 
    }

 
	@Override
	public boolean isCorrect(String[] ans) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double gradeIt(String[] answers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return "MultiAnswer";
	}

	@Override
	public void writeHTMLContent(StringBuilder b) {
	   for (int i = 0; i < answers.length; i++) {
           b.append("<input type = \"checkbox\"name = \"")
           .append(getName()).append(i).append("\" ")
           .append(this.answers[i].textanswer());
       }
	}

	@Override
	public void writeXMLContent(StringBuilder b) {
		endTagWriteQuestion(b);
        for (int i = 0; i < answers.length; i++) {
        	answers[i].writeXMLContent(b);
        }
	}
}