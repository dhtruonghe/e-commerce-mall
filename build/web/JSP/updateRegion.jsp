<%-- 
    Document   : updateRegion
    Created on : Nov 1, 2024, 8:31:25 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,entity.Region" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          Vector<Region> vector=(Vector<Region>)request.getAttribute("vector");
          Region region=vector.get(0);
         %>
        <form action="RegionURL" method="post">
            <input type="hidden" name="service" value="updateRegion">
            <table>
                <caption>Update Region</caption>
                <tr>
                    <td><label for="RegionID">RegionID</label></td>
                    <td><input type="text" name="RegionID" id="RegionID" value="<%=region.getRegionID()%>"></td>
                </tr>
                <tr>
                    <td><label for="RegionDescription">RegionDescription</label></td>
                    <td><input type="text" name="RegionDescription" id="RegionDescription" required value="<%=region.getRegionDescription()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateRegion" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
