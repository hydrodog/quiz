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
public class MultiAnswer_Answer extends Answer {
    MultiAnswer_Answer(String answer, boolean isCorrect) {
    	super(answer, isCorrect);
    }

    MultiAnswer_Answer() {}

    public String textanswer() {
    	return "<A correct = \"" + this.getCorrect() + "\">" +
    			this.getAnswer() + "</A>\n";
//    	return "<A correct = \"" + this.getCorrect() + "\">" +
//		this.getAnswer() + "</A>" + "<img src = \""+this.Image+"\"/>\n";
    }

    public String graphanswer() {
    	return "value =\"true\">" + "<img src = \"" + 
    			this.getAnswer() + "\"/><br/>" + "\n";
    }
    public void writeXMLContent(StringBuilder b) {
    	b.append("<A correct = \"" + getCorrect() + "\">")
    		.append(getAnswer()).append("</A>");
    }
}
