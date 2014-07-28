package org.adastraeducation.quiz;

/**
 * Question is the base class of all questions. It is abstract.\ The actual
 * questions are MultiChoice, MultiAnswer, Matching, etc. The purpose of
 * question is the contain the values shared by all questions, such as id, name
 * and level
 * 
 * @author: Dov Kruger
 */
public abstract class Question {
	/**
	 * unique id (for database, maybe XML?
	 */
	private int id;
	/**
	 * displayed above the question
	 */
	private String name;

	/**
	 * displayed above the question
	 */
	private String title;

	/**
	 * difficulty level, used for adaptive quiz
	 */
	private int level;

	/**
	 * text of the question
	 */
	private String question;
	private boolean imgQuestion; // if true, the question string is the name of
									// a picture
	private static int count; // for general unique id
	// TODO: replace with database

	static {
		count = 0;
	}

	public Question() {
	}

	public Question(String title, String level, String question,
			boolean imgQuestion) {
		this.id = count++;
		this.name = null; // name of the question within Quiz set when added to
							// a quiz
		this.title = title;
		this.level = Integer.parseInt(level);
		// TODO: trap errors in case level is not a valid integer
		this.question = question;
		this.imgQuestion = imgQuestion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String q) {
		question = q;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void writeHTMLHeader(StringBuilder b) {
		b.append("<h1>").append(title).append("</h1>");
		b.append("<div ");
		writeAttrs(b);
		if (imgQuestion) {
			b.append("<img src=\"img/").append(question).append("\">");
		} else {
			b.append("<h2>" + question + "</h2>");
		}
	}

	protected static void writeAttr(StringBuilder b, String tag, String val) {
		b.append(tag).append("=\"").append(val).append("\" ");
	}

	protected static void writeAttr(StringBuilder b, String tag, int val) {
		b.append(tag).append("=\"").append(val).append("\" ");
	}

	protected static void writeOptAttr(StringBuilder b, String tag, boolean val) {
		if (val) {
			b.append(tag).append("=\"").append(val).append("\" ");
		}
	}

	/**
	 * write the three tags id, name and level called by all children in both
	 * writeHTML and writeXML methods
	 * 
	 * @param b the buffer that accumulates the text for the HTML/XML.
	 */
	protected void writeAttrs(StringBuilder b) {
		writeAttr(b, "id", id);
//		writeAttr(b, "name", title); //TODO: name only makes sense inside a quiz?
		writeAttr(b, "title", title);
		writeAttr(b, "level", String.valueOf(level));
	}

	protected void endTagWriteQuestion(StringBuilder b) {
		b.append(">\n").append(question);
	}

	public void writeHTML(StringBuilder b) {
		b.append("<h1>").append(title).append("</h1>");
		b.append("<div ");
		writeAttrs(b);
		b.append(">\n");
		if (imgQuestion) {
			b.append("<img src=\"../img/").append(question).append("\">");
		} else {
			b.append("<h2>" + question + "</h2>");
		}
		writeHTMLContent(b);
		b.append("</div>");
	}

	public abstract String getTagName();
	
	/*
	 * check each question if it is correct
	 */
	public abstract boolean isCorrect(String ans);

	/*
	 * Write out the XML tag name and the basic attributes in common for all
	 * question types Example: <MultiChoice id="q1" name="dynosaur" level="1"
	 * then each class writes any additional attributes and ends the tag.
	 */
	public void writeXML(StringBuilder b) {
		b.append('<').append(getTagName()).append(' ');
		writeAttrs(b);
		writeXMLContent(b);
		b.append("</").append(getTagName()).append(">\n");
	}

	public abstract void writeHTMLContent(StringBuilder b);

	public abstract void writeXMLContent(StringBuilder b);

	// Not ready for this yet...
	// public abstract void writeDB();
	// public abstract void readDB();
	// public abstract void readXML()
}
