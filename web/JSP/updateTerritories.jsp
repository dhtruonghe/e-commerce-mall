<%-- 
    Document   : updateTerritories
    Created on : Nov 1, 2024, 8:45:39 AM
    Author     : haitr
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,entity.Territories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% ResultSet rsRegion=(ResultSet)request.getAttribute("rsRegion");
         Vector<Territories> vector=(Vector<Territories>)request.getAttribute("vector");
          Territories te=vector.get(0);
         %>
        <form action="TerritoriesURL" method="post">
            <input type="hidden" name="service" value="insertTerritories">
            <table>
                <caption>Insert Territories</caption>
                <tr>
                    <td><label for="TerritoryID">TerritoryID</label></td>
                    <td><input type="text" name="TerritoryID" id="TerritoryID" value="<%=te.getTerritoryID()%>"></td>
                </tr>
                <tr>
                    <td><label for="TerritoryDescription">TerritoryDescription</label></td>
                    <td><input type="text" name="TerritoryDescription" id="TerritoryDescription" required value="<%=te.getTerritoryDescription()%>"></td>
                </tr>
                <tr>
                    <td><label for="RegionID">RegionID</label></td>
                    <td>
                        <select name="RegionID" id="RegionID">
                            <%while(rsRegion.next()){%>
                                <option value="<%=rsRegion.getInt(1)%>"><%=rsRegion.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateTerritories" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>

