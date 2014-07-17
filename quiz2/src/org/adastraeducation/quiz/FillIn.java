package org.adastraeducation.quiz;

import java.io.FileNotFoundException;
//import java.io.PrintWriter;

public class FillIn extends Question{
	
	private FillInAnswer[] fillInAnswers;

	public FillIn(String title, String level, String question, String[] answerList, boolean imgQuestion) {
		super(title, level, question, imgQuestion);
		this.fillInAnswers = new FillInAnswer[answerList.length/2];
		for(int i = 0; i < this.fillInAnswers.length; i++) {
			this.fillInAnswers[i] = new FillInAnswer(answerList[i*2], answerList[i*2+1].equals("t"));
		}
	}
	
//	public void addFillInAnswer(String answerList) {}
//	public void deleteFillInAnswer(int index) {}
//	public void printFillInAnswer(int index) {
//		fillInAnswers[index].writeHTML(boolean withReminder);
//	}
	
	public String getTagName() { return "FillIn"; }
	
	public void writeHTMLContent(StringBuilder b) {
		b.append("<input type=\"text\" ");
		super.writeAttr(b, "name", getName());
		b.append(">\n");
	}
	public void writeXMLContent(StringBuilder b) {
		super.endTagWriteQuestion(b);
		for(int i = 0; i < this.fillInAnswers.length; i++) {
			this.fillInAnswers[i].writeXML(b);
		}
	}
	public static void testHTMLAndXML(Quiz quiz) {
		String[] answerList1 = {"120", "t", "120.0", "t", "12", "f"};
		FillIn f1 = new FillIn("arithmetic", "1", "What is 30 x 4?", answerList1, false);
		quiz.addQuestion(f1);
	}
//	public static void main(String[] args) {
//		StringBuilder b = new StringBuilder();
//		String []answerList = { "120", "t", "120.0", "t", "12", "f"};
//		FillIn f1 = new FillIn("8", "arithmetic", "1", "What is 30 x 4?", answerList); 
//		
//		f1.writeHTML(b);
//		
//		try {
//			PrintWriter pw = new PrintWriter("FillIn.html");
//			pw.println(b);
//			pw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(b);
//			
//		b.setLength(0);
//		f1.writeXML(b);
//		try {
//			PrintWriter pw = new PrintWriter("FillIn.xml");
//			pw.println(b);
//			pw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


}
