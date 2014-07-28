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
	<%  
		String[] c1 = request.getParameterValues("q1");
		String[] c2 = request.getParameterValues("q2");
		//String[] c3 = request.getParameterValues("q3");
		//String[] c4 = request.getParameterValues("q4");
		//String[] c5 = request.getParameterValues("q5");
		ArrayList<Question> questions;
		String[] ans = {c1[0], c2[0]};
		Quiz quiz = (Quiz)request.getSession().getAttribute("QUIZ");
		questions = quiz.getQuestion();
		for(int i = 0; i < questions.size(); i++){%>
			<%if(questions.get(i).isCorrect(ans[i])){%>
				the <%= i%> question is correct! Congratulations!"<br>
			<%} else {%>
				the <%= i%> question is Wrong! Sorry!"
			<%}
		}%>
</body>
</html>