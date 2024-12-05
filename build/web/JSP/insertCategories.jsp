<%-- 
    Document   : insertCategories
    Created on : Oct 24, 2024, 8:35:17 PM
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
        <form action="CategoriesURL" method="post">
            <input type="hidden" name="service" value="insertCategories">
            <table>
                <caption>Insert Categories</caption>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="text" name="CategoryID" id="CategoryID" readonly></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName" required></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                    <td><input type="text" name="Description" id="Description"></td>
                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                    <td><input type="text" name="Picture" id="Picture"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertCategories" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
