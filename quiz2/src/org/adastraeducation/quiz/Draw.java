package org.adastraeducation.quiz;

public class Draw extends Question {
	public Draw(String title, String question){
		super(title, "1", question, false);
	}
	
	public StringBuilder connectJavaScript(StringBuilder b){
		b.append("<head>");
		b.append("<script src=\"breadboard.js\" type=\"text/javascript\"></script>");
		b.append("</head>");
		return b;
	}
	
	private StringBuilder setCanvas(StringBuilder b){
		b.append("<canvas id = \"breadBoard\" height = \"430\" width = \"813\" style = \"border: 1px solid #d3d3d3; background: #aaaaaa\"></canvas>");
		return b;
	}
	
	private StringBuilder setButtons(StringBuilder b){
		String[] buttons = {"Resistance (Horizontal)",
							"Resistance (Vertical)",
							"Wire"};
		String[] buttonsFunction = {"drawRectH()",
									"drawRectV()",
									"drawWire()"};
		
		b.append("<div>");
		for(int i = 0; i < 3; i++){
			b.append("<button onclick=\"").append(buttonsFunction[i]).append("\">").append(buttons[i]).append("</button>");
		}
		b.append("</div>");
		return b;
	}

	public void writeHTMLContent(StringBuilder b){
		b.append("<html>\n");
		
		b.append("<body>\n");
		//set questions
		b.append("<h2>Question: ").append(getQuestion()).append("</h2><br/>");
		
		//set canvas tag
		b = setCanvas(b);
		
		//add bottons
		b = setButtons(b);
		
		b.append("</body>\n</html>");
	}
	
	public void writeXMLContent(StringBuilder b){
		b.append("<").append(getTagName()).append(">").append(getQuestion()).append("</").append(getTagName()).append(">");
	}
	
	public String getTagName(){
		return "Canvas";
	}
	
	public boolean isCorrect(String[] ans){
		return false;
	}
	
	public double gradeIt(String[] answers){
		return 0;
	}
}
