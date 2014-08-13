<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.util.regex.Matcher" %>
<%@ page import = "java.util.regex.Pattern" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.HashMap" %>
<html>
    <head>
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
           String formula = request.getParameter("equation");
           String regex ="[\\w]+";
           ArrayList<String> variable = new ArrayList<String>();
	   Pattern p = Pattern.compile(regex);
	   Matcher m = p.matcher(formula);
           while(m.find()){
            variable.add(m.group());
           }
        %>
        <p><%=formula %><br></p>

        <table border="2" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
            <tr>
                <th>Variable</th>
                <th>Min</th>
                <th>Step</th>
                <th>Max</th>
            </tr>
            <%
            for(int i=0;i<variable.size();i++){
                if(functionsMap.get(variable.get(i))==null){
            %>
            <tr>
                <td><%=variable.get(i)%></td>
                <td><%=request.getParameter(variable.get(i)+"Min")%></td>
                <td><%=request.getParameter(variable.get(i)+"Step")%></td>
                <td><%=request.getParameter(variable.get(i)+"Max")%></td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>