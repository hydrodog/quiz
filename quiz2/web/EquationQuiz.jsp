<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
      </script>
      <script>
        $(document).ready(function(){
        $("#eq").keyup(function(){
            $("#variable").empty();
            var testArray = [];
            var formula = $("#eq").val();
            var variable=formula.split(/\W|sin|cos|tan|atan|asin|abs|neg|sqrt/);
            for (var i=0;i<variable.length;i++) {
               if (isNaN(variable[i])) {
                     if ($.inArray(variable[i], testArray) > -1) {
                        ;
                     }
                     else{
                        $("#variable").append(variable[i]);
                        $("#variable").append(":&nbsp;&nbsp;&nbsp;min:");
                        $("#variable").append('<input type="text" name="eq">');
                        $("#variable").append("step:");
                        $("#variable").append('<input type="text" name="eq">');
                        $("#variable").append("max:");
                        $("#variable").append('<input type="text" name="eq"><br>');
                     }
                     testArray.push(variable[i]);
                }
        }
           });
        });
      </script>      
      <title>Equation</title>
    </head>
    
    <body>
      <h3>Equation Test</h3><br>
      
      <form action="" methid="post">
        <table border="2" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
            <tr>
                <td height="30">Name</td>
                <td >Q</td>
            </tr>
            <tr>
                <td height="30">Title</td>
                <td>
                    <input type="text" name="title">
                </td>
            </tr>
            <tr>
                <td height="30">Level</td>
                <td>
                    <select name="level" size="1">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td height="30">Question</td>
                <td>
                    <input type="text" name="question" style="width:200">
                </td>
            </tr>
            <tr>
                <td height = "30">Equation</td>
                <td>
                    <select name="equation_type" size="1">
                        <option value="infix" selected>infix</option>
                        <option value="rpn">RPN</option>
                    </select>
                    <input type="text" id="eq" name="equation" value="">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit" size="12">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="Cancel" size="12">
                </td>
            </tr>
        </table>
        <div align="center" id="variable">
        </div>
      </form>
      
    </body>
</html>
