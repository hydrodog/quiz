/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpe642;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zhangchenyi
 */
public final class MultiAnswer extends Question{
    
    private final int num_selection;
    private final int level = 0;
    private final String id;
    private final String name;
    private final MultiAnswer_Answer Answers[];
    private final String desc;
    
    
    MultiAnswer(int num_selection, String id, String name, String desc, String Answer[], String Image[],boolean results[]) throws SQLException
    {
         this.num_selection = num_selection;
         this.name = name;
         this.id = id;
         this.desc = desc;
         
         this.Answers = new MultiAnswer_Answer[this.num_selection];
         for(int i =0;i<this.num_selection;i++)
         {
             this.Answers[i] = new MultiAnswer_Answer(Answer[i],Image[i],results[i]);
         } 
         
         update_database();
    }

    public void update_database() throws SQLException
    {
         String sql = "INSERT INTO MULTIANSWER"
				+ "(LEVEL, NAME, DESCRIPTION, ID) " + "VALUES (\""
				+ level+"\",\""+name+"\",\""+desc+"\",\""+id+"\")";
         Mysql_Connector con = new Mysql_Connector();
         con.single_process(sql);
         
        int P_ID = 0;
        
        sql = "SELECT * FROM MultiAnswer;";
        ResultSet res;
        res = con.where(sql,"P_ID");
        while(res.next())
                       {
                           P_ID = res.getInt("p_id");
                       }
        
        System.out.println(P_ID);
        for (int i =0;i<Answers.length;i++) 
        {
            sql = "INSERT INTO MULTIANSWER_ANSWER"
				+ "(P_ID, A_CORRECT,A_CONTENT,A_IMG) " + "VALUES (\""
				+ P_ID +"\",\""
                                +Answers[i].Result+"\",\""
                                +Answers[i].Description+"\",\""
                                +Answers[i].Image+"\")";
        }
    }
    
    public void writeHTML(StringBuilder b) {
        String result = "";
    
        
        result = this.desc +"\n<br/>";
        for(int i =0;i<this.num_selection;i++)
            {
                result = result + "<input type = \"checkbox\"name = \""+this.name+i+"\" ";
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
    
    public void testXMLAndHTML() throws SQLException
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
}