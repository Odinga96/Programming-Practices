<%-- 
    Document   : newjsp
    Created on : Oct 3, 2019, 6:20:57 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String name="Johnson";
            int age=60;
            double weight=70;
            
        %>
        <p>My name is <%= name %> and I am <%= age %> years old and weighing <%= weight %></p>
    </body>
</html>
