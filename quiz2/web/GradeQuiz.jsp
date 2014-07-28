<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.adastraeducation.quiz.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>QuizGrades</h1>
	<%  
		//String[] c1 = request.getParameterValues("q1");
		//String[] c2 = request.getParameterValues("q2");
		//String[] c3 = request.getParameterValues("q3");
		//String[] c4 = request.getParameterValues("q4");
		//String[] c5 = request.getParameterValues("q5");
		//ArrayList<Question> questions;
		//String[] ans = {c1[0], c2[0]};
		Quiz quiz = (Quiz)request.getSession().getAttribute("QUIZ");
		questions = quiz.getQuestion();
		double finalScore = 0;
		/*
		String[] questionNameList = new String[questions.size()];
		Enumeration paramNames = request.getParameterNames();
		for(int i = 0; paramNames.hasMoreElements(); i++) {
			questionNameList[i] = (String)paraNames.nextElement();
		}
		*/
	%>
    <h2>Detailed Results</h2>
    <table>
    	<tr>
        	<th>Question</th>
            <th>Right/Wrong</th>
            <th>Score</th>
        </tr>
    <%	for(int i = 0; i < questions.size(); i++) { 
			Question thisQuestion = questions.get(i);
			String thisName = thisQuestion.getName();
			String[] thisAnswer = request.getParameterValues(thisName);
			double thisScore = thisQuestion.gradeIt(thisAnswer);
			finalScore += thisScore;
	%>
        <tr>
        	<td>
				<%= thisName %>
            </td>
            <td>
				<%= thisQuestion.isCorrect(thiAnswer)) %>
            </td>
            <td>
            	<%= thisScore %>
            </td>
        </tr>
	<%	}
	%>
    <br>
    <h2>Final Score: <%= finalScore %> </h2>
    
			<!--<%if(questions.get(i).isCorrect(ans[i])){%>
				the <%= i%> question is correct! Congratulations!"<br>
			<%} else {%>
				the <%= i%> question is Wrong! Sorry!"
			<%}
		}%> -->
</body>
</html>