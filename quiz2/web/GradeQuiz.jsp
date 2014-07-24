<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>QuizGrades</h1>
	<%  String[] c1 = request.getParameterValues("q1");
		String[] c2 = request.getParameterValues("q2");
		String[] c3 = request.getParameterValues("q3");
		String[] c4 = request.getParameterValues("q4");
		String[] c5 = request.getParameterValues("q5");
		int n1 = c1.length, n2 = c2.length, n3 = c3.length, n4 = c4.length, n5 = c5.length;
		for(int i = 0; i < n1; i++) {%>
		the answer is <%=c1[i] %>
		<%} %><br>
		<%for(int i = 0; i < n2; i++) {%>
		the answer is <%=c2[i] %>
		<%} %><br>
		<%for(int i = 0; i < n3; i++) {%>
		the answer is <%=c3[i] %>
		<%} %><br>
		<%for(int i = 0; i < n4; i++) {%>
		the answer is <%=c4[i] %>
		<%} %><br>
		<%for(int i = 0; i < n5; i++) {%>
		the answer is <%=c5[i] %>
		<%} %><br>
</body>
</html>