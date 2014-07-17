package org.adastraeducation.quiz;
/**
 * Represent a set of answers to multiple choice questions that are reused
 * @author qiang
 *
 */
public class StdChoice {
	private String[] values;
	private String[] answers;
	public StdChoice(String[] value){
		values = new String[value.length/2];
		answers = new String[value.length/2];
		for (int i = 0, j = 0; i < value.length; i+=2, j++) {
			answers[j] = value[i];
			values[j] = value[i+1];
		}
	}
	public void writeHTML(StringBuilder b){
		for(int i = 0; i < answers.length; i++)
			b.append("<input type=\"radio\" name=\"stdopinion\">").append(answers[i]).append("<br>");
	}
	public void writeXML(StringBuilder b){
		for(int i = 0; i < answers.length; i++)
			b.append("<A v=\"").append(values[i]).append("\">").append(answers[i]).append("</A>");
	}
}
