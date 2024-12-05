<%-- 
    Document   : listTerritories
    Created on : Oct 24, 2024, 6:40:45 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Territories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Territories> vector = (Vector<Territories>)request.getAttribute("dataTerritories");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="TerritoriesURL" method="get">
            <p>Territories ID <input type="text" name="tid" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="TerritoriesURL?service=insertTerritories">insert Territories</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>TerritoryID</th>
                <th>TerritoryDescription</th>
                <th>RegionID</th>
            </tr>
            <%for(Territories territory : vector){%>
            <tr>
                <td><%=territory.getTerritoryID()%></td>
                <td><%=territory.getTerritoryDescription()%></td>
                <td><%=territory.getRegionID()%></td>
                <td><a href="TerritoriesURL?service=updateTerritories&tid=<%=territory.getTerritoryID()%>">update</a></td>
                <td><a href="TerritoriesURL?service=deleteTerritories&tid=<%=territory.getTerritoryID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
