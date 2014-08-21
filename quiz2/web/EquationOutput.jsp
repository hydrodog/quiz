<%@ page import="org.adastraeducation.quiz.Equation" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.util.regex.Matcher" %>
<%@ page import = "java.util.regex.Pattern" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.HashMap" %>
<%@ page import = "org.adastraeducation.quiz.equation.Var" %>
<%@ page import = "org.adastraeducation.quiz.DatabaseMgr" %>
<html>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
        </script>
        <title>GetEquation</title>
    </head>
    <body>
        <%
            final HashMap<String,Boolean> functionsMap = new HashMap<String,Boolean>() {
                {
                put("abs",true);
                put("asin",true);
                put("atan",true);
                put("cos",true);
                put("sin",true);
                put("sqrt",true);
                put("tan",true);
                put("neg",true);
                }
	    };
        %>
        <h1 align="center">Equation Board Test!<br/></h1>
        <%
            String QID = request.getParameter("QID");
            String level = request.getParameter("level");
            String title = request.getParameter("title");
            String question = request.getParameter("question");
            String strEquation = request.getParameter("equation");
            
            HashMap<String,Var> variablesMap= new HashMap<String,Var>();
            String regex ="[\\w]+";
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(strEquation);
            
            String regex2 = "^[a-zA-z]+";
    	    Pattern p2 = Pattern.compile(regex2);
            
            while(m.find()){
    		Matcher m2 = p2.matcher(m.group());
    		if(m2.find()){
                    if(functionsMap.get(m.group())==null){
                        String name = m.group();
                        int min = Integer.valueOf(request.getParameter(name+"Min"));
                        int step = Integer.valueOf(request.getParameter(name+"Step"));
                        int max = Integer.valueOf(request.getParameter(name+"Max"));
                        variablesMap.put(name, new Var(name,min,step,max));
                    }
                }
            }
            
            Equation equation = new Equation(title,level,question,strEquation,variablesMap);
            
            StringBuilder infix = new StringBuilder();
            StringBuilder rpn = new StringBuilder();
            
            equation.getExpression().infix(infix);
            equation.getExpression().rpn(rpn);
            
            String sql="insert into questions values("+equation.getId()+",'"+equation.getTagName()+"',"+equation.getLevel()+",'"
                +equation.getTitle()+"',"+"false,"+"'"+equation.getQuestion()+"','"+infix.toString()+"','"+rpn.toString()+"',"+equation.getCorrectAnswer()+")";
                
            DatabaseMgr.update(sql);
            
            StringBuilder b = new StringBuilder();
            equation.getExpression().infixReplaceVar(b);
        %>
        <div>
            <p><%=QID %>: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <%=title %><br/></p>
            <p><%=question %>  &nbsp&nbsp
            <%=b.toString() %>
            </p>
            <p><%=equation.getCorrectAnswer()%></p>
            <p><%=sql%></p>
        </div>
    </body>
</html>