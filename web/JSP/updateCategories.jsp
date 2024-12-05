<%-- 
    Document   : updateCategories
    Created on : Nov 1, 2024, 8:55:03 AM
    Author     : haitr
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,entity.Categories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
          Vector<Categories> vector=(Vector<Categories>)request.getAttribute("vector");
          Categories cate=vector.get(0);
         %>
        <form action="CategoriesURL" method="post">
            <input type="hidden" name="service" value="updateCategories">
            <table>
                <caption>update Categories</caption>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="text" name="CategoryID" id="CategoryID" value="<%=cate.getCategoryID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName" value="<%=cate.getCategoryName()%>"></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                    <td><input type="text" name="Description" id="Description" value="<%=cate.getDescription()%>"></td>
                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                    <td><input type="text" name="Picture" id="Picture" value="<%=cate.getPicture()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateCategories" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>

