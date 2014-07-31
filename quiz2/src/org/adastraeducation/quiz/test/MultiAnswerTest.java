package org.adastraeducation.quiz.test;

import org.adastraeducation.quiz.MultiAnswer;
import org.adastraeducation.quiz.Quiz;

public class MultiAnswerTest {
	public static void buildTestQuestions(Quiz quiz) {
        String[] answers = {
        		"spider", "f", "fly", "t", "grasshopper", "t", "monkey", "f"
        };
	        
     
	    String question = "Which of the following is an insect?";
	        
        quiz.addQuestion(new MultiAnswer("Bug question", question, answers, "f"));

	    String[] bugimg = {
	        "1.jpg", "f", "2.jpg", "f", "3.jpg", "t", "4.jpg", "t"
	    };
        quiz.addQuestion(new MultiAnswer("Bug question", question, bugimg, "t"));
	    
	}
}
