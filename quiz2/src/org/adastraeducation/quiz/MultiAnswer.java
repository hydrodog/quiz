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
public class MultiAnswer extends Question{
    
    private final int num_selection;
    private int level;
    private final String id;
    private final String name;
    private final MultiAnswer_Answer Answers[];
    private final String desc;
    
    MultiAnswer(int num_selection, String id, String name, String desc, 
    		String Answer[], boolean results[]) {
         this.num_selection = num_selection;
         this.name = name;
         this.id = id;
         this.desc = desc;
         
         this.Answers = new MultiAnswer_Answer[this.num_selection];
         for(int i =0;i<this.num_selection;i++) {
             this.Answers[i] = new MultiAnswer_Answer(Answer[i],results[i]);
         } 
    }

    public String getTagName() { return "MultiAnswer"; }
    
    @Override
    public void writeHTMLContent(StringBuilder b) {
       b.append(this.desc).append('\n');
        for (int i = 0; i < this.num_selection; i++) {
                b.append("<input type = \"checkbox\" name = \"").append(name)
                .append("\" ").append(this.Answers[i].get_answer_HTML());
            }
    }

    @Override
    public void writeXMLContent(StringBuilder b) {
    	endTagWriteQuestion(b);
    	b.append(desc).append('\n');
      
        for (int i = 0; i < this.num_selection; i++) {
            b.append(Answers[i].get_answer_XML());
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
}