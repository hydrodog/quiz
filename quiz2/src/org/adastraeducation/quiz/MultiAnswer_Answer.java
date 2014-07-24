package org.adastraeducation.quiz;
/**
 *
 * @author zhangchenyi
 */
public class MultiAnswer_Answer {

	private String Description;
	private boolean Result;

	public MultiAnswer_Answer(String Description, boolean Result) {
		this.Description = Description;
		this.Result = Result;
	}

	public MultiAnswer_Answer() {
		throw new UnsupportedOperationException("Lost Arguments!!");
	}

	public String get_answer_XML() {
		String result = "";

		result = "<A correct = \"" + this.Result + "\">" + this.Description + "<\\A>\n";

		return result;
	}

	public String get_answer_HTML() {
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
	public boolean get_result() {
		return Result;
	}

	public String get_desc() {
		return Description;
	} 
}
