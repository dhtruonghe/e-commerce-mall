<%-- 
    Document   : loginCustomer
    Created on : Oct 18, 2024, 11:01:26 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String error = (String) request.getAttribute("error");%>
            <%=(error != null?error:"")%>
        <form action="CustomersURL" method="post">
            <input type="hidden" name="service" value="loginCustomer">
                
        <table>
        <caption>Login</caption>
        <tr>
            <td><label for="username">username</label></td>
            <td><input type="text" name="username" id="username" required></td>
        </tr>
        <tr>
            <td><label for="password">password</label><p>
            <td><input type="password" name="password" id="password" required></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login" name="submit"></td>
            <td><input type="reset" value="clear"></td>
        </tr>
        

    </table>
        </form>
    </body>
</html>
