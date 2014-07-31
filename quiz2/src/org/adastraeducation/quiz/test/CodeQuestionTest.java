package org.adastraeducation.quiz.test;

import org.adastraeducation.quiz.*;

public class CodeQuestionTest {
	public static void buildTestQuestions(Quiz quiz) {
		String initialCode = "_ class _ {\n" + 
					  "  private int x;\n" +
					  "  _ A() {\n" +
					  "    x = 0;\n" +
					  "  }\n" +
					  "}";

		String answer = "public class A {\n" + 
				  "  private int x;\n" +
				  "  public A() {\n" +
				  "    x = 0;\n" +
				  "  }\n" +
				  "}";
CodeQuestion code1 = new CodeQuestion(initialCode, answer);
		quiz.addQuestion(code1);
	}
}
