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
    
    private final int num_selection;
    private final MultiAnswer_Answer Answers[];
    
    public MultiAnswer(int num_selection, String title, String question, String Answer[], String Image[],boolean results[])
    {
    	super(title, "1", question, false);
         this.num_selection = num_selection;
         
         this.Answers = new MultiAnswer_Answer[this.num_selection];
         for(int i =0;i<this.num_selection;i++)
         {
             this.Answers[i] = new MultiAnswer_Answer(Answer[i],Image[i],results[i]);
         } 
    }

 
    
    public void testXMLAndHTML()
    {
        MultiAnswer test;
        
        String answer[] = new String [4];
        answer[0] = "T_Answer1";
        answer[1] = "T_Answer2";
        answer[2] = "T_Answer3";
        answer[3] = "T_Answer4";
        
        String img[] = new String [4];
        img[0] = "1.jpg";
        img[1] = "2.jpg";
        img[2] = "3.jpg";
        img[3] = "4.jpg";
        
        boolean result[] = new boolean [4];
        result[0] = true;
        result[1] = false;
        result[2] = false;
        result[3] = true;
        
        String desc = "This is a Sample Problem!!";
        String id = "ID";
        String name = "name";
        
        
        test = new MultiAnswer(4,id,name,desc,answer,img,result);
        
        StringBuilder html = new StringBuilder();
        StringBuilder xml = new StringBuilder();
        
        test.writeHTML(html);
        test.writeXML(xml);
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
	   for(int i =0;i<this.num_selection;i++)
       {
           b.append("<input type = \"checkbox\"name = \"")
           .append(getName()).append(i).append("\" ")
           .append(this.Answers[i].get_answer_HTML());
       }
	}

	@Override
	public void writeXMLContent(StringBuilder b) {
		endTagWriteQuestion(b);
        for(int i =0;i<this.num_selection;i++)
        {
            b.append(Answers[i].get_answer_XML());
        }
	}
}