/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adastraeducation.quiz.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.adastraeducation.quiz.MultiAnswer;

/**
 *
 * @author zhangchenyi
 */
public class CPE642 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        // TODO code application logic here
        
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
        
        
        try {
               PrintWriter PW_html = new PrintWriter("test.html");
               PrintWriter PW_xml = new PrintWriter("test.xml");
               PW_html.println(html);
               System.out.println(html);
               
               PW_xml.println(xml);
               System.out.println(xml);
               
               PW_html.close();
               PW_xml.close();
	}
        catch (FileNotFoundException e) {
		}
        
        Mysql_Connector conn = new Mysql_Connector();
        
        conn.get_connection();
        
        String sql = "DELETE FROM USERS WHERE email = \"czhang@cctv.com\"";
        conn.single_process(sql);
        sql = "SELECT * FROM USERS;";
        conn.where(sql,"email");
        
        System.out.println("-------------------------------");
        
        sql = "INSERT INTO USERS (email,password) VALUES (\"czhang@cctv.com\", \"czhang0727\")";
        conn.single_process(sql);
        sql = "SELECT * FROM USERS;";
        conn.where(sql,"password");
        
    }   
}