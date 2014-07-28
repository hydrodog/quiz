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
	<h1>QuizGrades</h1> 
	<%  String[] ans;
		ArrayList<Question> questions;
		Quiz quiz = (Quiz)request.getSession().getAttribute("QUIZ");
		questions = quiz.getQuestion();
		for(int i = 0; i < questions.size(); i++){
			ans = request.getParameterValues(questions.get(i).getName());%>
			<%if(questions.get(i).isCorrect(ans[0])){%>
				the <%= i%> question is correct! Congratulations!"<br>
			<%} else {%>
				the <%= i%> question is Wrong! Sorry!"<br>
			<%}
		}%>
</body>
</html>