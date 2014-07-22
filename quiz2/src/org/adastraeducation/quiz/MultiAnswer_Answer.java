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
public class MultiAnswer_Answer {
        
        String Description;
        boolean Result;
    
        MultiAnswer_Answer(String Description, boolean Result)
        {
            this.Description = Description;
            this.Result = Result;
        }

    MultiAnswer_Answer() {
        throw new UnsupportedOperationException("Lost Arguments!!"); //To change body of generated methods, choose Tools | Templates.
    }
        
        public String get_answer_XML()
        {
            String result = "";
            
            result = "<A correct = \"" + this.Result + "\">" + this.Description + "<\\A>\n";
            
            return result;
        }
        
        public String get_answer_HTML()
        {
            String result = "";
            
            result = "value =\"true\">"+this.Description+"<br/>\n";
            
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
