<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.adastraeducation.quiz.*"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Quiz</h1>
    <%  StringBuilder b = new StringBuilder();
		ArrayList<Question> questions = new ArrayList<Question>();
		questions = DBMultichoiceTest.exampleOfQuestionQueryPseudocode();
		questions.get(0).writeHTMLContent(b);
		questions.get(1).writeHTMLContent(b);%>
    <%= b %>
</body>
</html>