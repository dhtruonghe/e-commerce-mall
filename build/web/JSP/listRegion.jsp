<%-- 
    Document   : listRegion
    Created on : Oct 24, 2024, 6:31:18 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Region" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Region> vector = (Vector<Region>)request.getAttribute("dataRegion");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="RegionURL" method="get">
            <p>Region Description  <input type="text" name="rDescrip" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="RegionURL?service=insertRegion">insert Region</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>RegionID</th>
                <th>RegionDescription</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(Region region : vector){%>
            <tr>
                <td><%=region.getRegionID()%></td>
                <td><%=region.getRegionDescription()%></td>
                <td><a href="RegionURL?service=updateRegion&rid=<%=region.getRegionID()%>">update</a></td>
                <td><a href="RegionURL?service=deleteRegion&rid=<%=region.getRegionID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
