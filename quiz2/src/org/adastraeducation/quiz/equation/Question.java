package org.adastraeducation.quiz.equation;
/*
  Question is the base class of all questions.  It is abstract.
  The actual questions are MultiChoice, MultiAnswer, Matching, etc.
  
  The purpose of question is the contain the values shared by all questions, such as id, name and level.

  @author: Dov Kruger
 */
public abstract class Question {
	private String id;      // unique id (for database, maybe XML?)
	private String name;    // displayed above the question
	private int level;      // difficulty level, used for adaptive quiz
	private String question; // text of the question
	private boolean ImgQuestion; // if true, the question string is the name of a picture
	public Question() {}

	public Question(String id, String name, int level) {
		this.id = id; this.name = name; this.level = level;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	protected static void writeAttr(StringBuilder b, String tag, String val) {
		b.append(tag).append("=\"").append(val).append("\" ");
	}

    /*
      write the three tags id, name and level called by all children
      in both writeHTML and writeXML methods
    */
	protected void writeAttrs(StringBuilder b) {
		writeAttr(b, "id", id);
		writeAttr(b, "name", name);
		writeAttr(b, "level", String.valueOf(level));
	}
	protected void endTagWriteQuestion(StringBuilder b) {
		b.append(">\n");
		b.append(question);
	}

	public abstract void writeHTML(StringBuilder b);
	public abstract void writeXML(StringBuilder b);

	// Not ready for this yet...
	//	public abstract void writeDB();
	//  public abstract void readDB();
	// public abstract void readXML()
}


