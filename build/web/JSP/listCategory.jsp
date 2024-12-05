<%-- 
    Document   : listCategory
    Created on : Oct 15, 2024, 8:34:39 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Categories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Categories> vector = (Vector<Categories>)request.getAttribute("dataCategories");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="CategoriesURL" method="get">
            <p>Category Name <input type="text" name="cname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="HTML/insertCategories.html">Insert Categories</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Description</th>
                <th>Picture</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(Categories categories : vector){%>
            <tr>
                <td><%=categories.getCategoryID()%></td>
                <td><a href="ProductURL?service=searchCateID&cid=<%=categories.getCategoryID()%>"><%=categories.getCategoryName()%></a></td>
                <td><%=categories.getDescription()%></td>
                <td><%=categories.getPicture()%></td>
                <td><a href="CategoriesURL?service=updateCategories&cid=<%= categories.getCategoryID()%>">update</a></td>
                <td><a href="CategoriesURL?service=deleteCategories&cid=<%= categories.getCategoryID()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
