package org.adastraeducation.quiz.parser;

import org.xml.sax.Attributes;

public abstract class ObjectFactory {
	public abstract void create(Attributes attr);
	public abstract void addQuestion(String s);
	public abstract void addAnswer(String s);
	public void setQuestionAttributes(org.adastraeducation.quiz.Question q,
		Attributes attr) {
		String id = attr.getValue("id");
		String title = attr.getValue("title");
		String level = attr.getValue("level");
		String graphicQuestion = attr.getValue("imgQuestion");
		q.setId(Integer.parseInt(id));
		q.setTitle(title);
		q.setLevel(Integer.parseInt(level));
	}

}