<%-- 
    Document   : CheckOut
    Created on : Nov 8, 2024, 4:31:31 AM
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
    <form action="CartURL" method="post">
            <input type="hidden" name="service" value="checkOut">
            <table>
                <caption>Check Out</caption>
                <tr>
                    <td><label for="Name">Name</label></td>
                    <td><input type="text" name="Name" id="Name"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="checkOut" name="checkOut"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
</body>
</html>
