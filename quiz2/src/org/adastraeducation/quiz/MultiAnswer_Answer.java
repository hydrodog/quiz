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
public class MultiAnswer_Answer extends Answer{
        
        String Description;
        String Image;
        boolean Result;
    
        MultiAnswer_Answer(String Description, String Image, boolean Result)
        {
            this.Description = Description;
            this.Image = Image;
            this.Result = Result;
        }

    MultiAnswer_Answer() {
        throw new UnsupportedOperationException("Lost Arguments!!"); //To change body of generated methods, choose Tools | Templates.
    }
        
        public String get_answer_XML()
        {
            String result = "";
            
            result = "<A correct = \"" + this.Result + "\">" + this.Description + "<\\A>" + "<img src = \""+this.Image+"\"/>\n";
            
            return result;
        }
        
        public boolean insert()
        {
            
            return true;
        }
        
        public String get_answer_HTML()
        {
            String result = "";
            
            result = "value =\"true\">"+this.Description+"<img src = \""+this.Image+"\"/><br/>"+"\n";
            
            return result;
        }
        
        public boolean Answer_Check(boolean check)
        {
            if(this.Result == check)
            return true;
            else return false;
        }
    /**
     *
     * @return
     */
        public boolean get_result()
        {
            return Result;
        }
        
        public String get_desc()
        {
            return Description;
        } 
}
