/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpe642;
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
    
    MultiAnswer(int num_selection, String id, String name, String desc, String Answer[], boolean results[])
    {
         this.num_selection = num_selection;
         this.name = name;
         this.id = id;
         this.desc = desc;
         
         this.Answers = new MultiAnswer_Answer[this.num_selection];
         for(int i =0;i<this.num_selection;i++)
         {
             this.Answers[i] = new MultiAnswer_Answer(Answer[i],results[i]);
         } 
    }

    @Override
    public void writeHTML(StringBuilder b) {
        String result="";
    
        
        result = this.desc +"\n";
        for(int i =0;i<this.num_selection;i++)
            {
                result = result + "<input type = \"checkbox\" name = \""+this.name+"\" ";
                result = result + this.Answers[i].get_answer_HTML();
            }
        
        
        b.append(result);
    }

    @Override
    public void writeXML(StringBuilder b) {
        String result;
        
        result = "<MultiAnswer id=\""+this.id+"\" name=\""+this.name+"\" level=\"1\">\n"
                    +this.desc+"\n";
        
        for(int i =0;i<this.num_selection;i++)
        {
            result = result + Answers[i].get_answer_XML();
        }
        
        result = result + "</MultiAnswer>";
        b.append(result);
    }
}