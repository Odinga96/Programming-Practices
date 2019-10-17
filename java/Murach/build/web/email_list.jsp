<%--
    Document   : email_list
    Created on : Oct 3, 2019, 7:04:51 AM
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
        <%
           String firstName =request.getParameter("firstName");
           String lastName  =request.getParameter("lastName");
           String email     =request.getParameter("email");
        %>

        <h1>Hello : Thanks for joining our email list</h1>
        <p>Here is the information you entered</p>

        <table>
          <tr>
            <td>first Name</td>
            <td><%= firstName %></td>
          </tr>
          <tr>
            <td>last Name</td>
            <td><%= lastName %></td>
          </tr>
          <tr>
            <td>email</td>
            <td><%= email %></td>
          </tr>
        </table>
    </body>
</html>
