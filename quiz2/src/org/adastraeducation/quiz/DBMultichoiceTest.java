package org.adastraeducation.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBMultichoiceTest {
	public static ArrayList<Question> exampleOfQuestionQueryPseudocode () {
		Connection conn = null;
		ArrayList<Question> questions = new ArrayList<Question>();
	try{
		conn = DatabaseMgr.getConnection();
		PreparedStatement p = conn.prepareStatement(" SELECT Questions.qid,qtype,title,image,text FROM Questions,QuizzesQuestionsOrder WHERE Questions.qid=QuizzesQuestionsOrder.qid AND QuizzesQuestionsOrder.quizid = ?");
		p.setInt(1, 1);
		// DO A JOIN Above!

		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			int qid = rs.getInt("qid");
			int qtype = rs.getInt("qtype");
			String title = rs.getString("title");
			String image = rs.getString("image");
			String text = rs.getString("text");
			
			//switch(qtype) {
			//case 1:
			MultiChoice m = new MultiChoice(qid,qtype,title,image,text); // but NO ANSWERS
			questions.add(m);
			System.out.println(qid + "\t" + qtype + "\t" + title + "\t" + image + "\t" + text); 
			PreparedStatement p2 = conn.prepareStatement(" SELECT qid, aid, image, text, correct, response FROM Answers WHERE qid = ?");	
			p2.setInt(1, qid);
			ResultSet rs2 = p2.executeQuery();
			while (rs2.next() ) {
			String answer = rs2.getString("text");
			boolean isitright = rs2.getString("correct").equals("1");
			   m.addAnswer(answer, isitright);
			
			}
			rs2.close();
			p2.close();

		}
		
		rs.close();
		p.close();
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	
	} finally {
		DatabaseMgr.returnConnection(conn);
	}
	return questions;
		
	}
	/*ublic static void main(String[] args){
		StringBuilder b = new StringBuilder();
		ArrayList<Question> questions = new ArrayList<Question>();
		questions = exampleOfQuestionQueryPseudocode();
		questions.get(0).writeHTMLContent(b);
		questions.get(1).writeHTMLContent(b);
		System.out.print(b);
		
	}*/
}
