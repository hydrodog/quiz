package org.adastraeducation.quiz;

/**
 * A question that involves coding and a coding answer.
 * @author Andres Contreras
 *
 */

public class CodeQuestion extends Question {
	
	/** Possible code question types */
	public static enum CODE_QUESTION_TYPE{
		/** Compared to an answer literally */
		COMPLETE_CODE,
		/** Compiled in order to obtain output and compare */
		COMPILE_PROGRAM,
		/** Not compared automatically; checked for logic and/or structure */
		PSEUDOCODE
	}
	
	/** The programming language in which the question must be answered */
	private String language;
	
	/** The initial code needed to answer the question */
	private String initialCode;
	
	/** The expected length of the answer */
	private int expectedAnswerLength;
	
	/** The answer to the code question. It can be an output, matching code or expected pseudocode */
	private Answer answer;
	
	public CodeQuestion() {}
	
	public CodeQuestion(String initialCode, String answer) {
		this.initialCode = initialCode;
		this.answer = new Answer(answer, true);
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getInitialCode() {
		return initialCode;
	}

	public void setInitialCode(String initialCode) {
		this.initialCode = initialCode;
	}

	public int getExpectedAnswerLength() {
		return expectedAnswerLength;
	}

	public void setExpectedAnswerLength(int expectedAnswerLength) {
		this.expectedAnswerLength = expectedAnswerLength;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String getTagName() {		
		return "Code";
	}
	

	@Override
	public void writeHTMLContent(StringBuilder b) {
		b.append("<textarea class=\"code\" rows=\""+ expectedAnswerLength/80 +"\" cols=\""+ 80 +"\" ");
		super.writeAttr(b, "name", getName());
		b.append(">"+ initialCode +"</textarea>\n");
	}

	@Override
	public void writeXMLContent(StringBuilder b) {
		super.endTagWriteQuestion(b);		
		answer.writeXML();
	}


	@Override
	public boolean isCorrect(String[] ans) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double gradeIt(String[] answers) {
		// TODO Auto-generated method stub
		return 0;
	}

}
