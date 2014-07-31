/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.adastraeducation.quiz.test;
import java.sql.*;
/**
 *
 * @author zhangchenyi
 */
public class Mysql_Connector {
    
    Connection con = null;
    String url = "";
    String user = "";
    String pw = "";
    
    Mysql_Connector(String url,String user, String pw)
    {
        this.url = url;
        this.user = user;
        this.pw = pw;
    }
    
    Mysql_Connector()
    {
        this.url = "jdbc:mysql://127.0.0.1:5500/test";
        this.user = "root";
        this.pw = "";
    }
    
    public boolean get_connection()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            con = DriverManager.getConnection(url, user, pw);
            
            //String sql= "SELECT * FROM USERS;";
            
            
            
            System.out.println("Connected");
            
            return true;
        }
        
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println("MYSQL ERROR " + e.getMessage());
        }
        return false;
    }
    
    
    //update, delete, insert
    public void single_process(String sql)
    {
            if(get_connection())
            {
                   try
                   {
                       Statement stmt;
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sql);
                       ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
                       
                       int ret_id;
                       if(res.next())
                        {
                           ret_id = res.getInt(1);
                           //System.out.print(ret_id);
                        }
                   } 
                   
                   catch(SQLException e)
                   
                   {
                       System.out.println("MYSQL ERROR" + e.getMessage());
                   }
            }
            
            else 
            {
                System.out.println("CONNECTION FAILED!!!");
            }
    }
    
    //public void update(){}
    
    //public void delete(){}
    
    public void where(String sql, String Attribute_name) throws SQLException
    {
            try
                   {
                       Statement stmt = null;
                       stmt = con.createStatement();
                       ResultSet Selecet_Res = stmt.executeQuery(sql);
                      
                       while(Selecet_Res.next())
                       {
                           System.out.println(Selecet_Res.getString(Attribute_name));
                       }
                       
                   } 
                   
                   catch(SQLException e)
                   {
                       System.out.println("MYSQL ERROR" + e.getMessage());
                   }
            }
}
