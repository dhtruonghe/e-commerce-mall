<%-- 
    Document   : insertEmployeeTerritories
    Created on : Oct 24, 2024, 8:47:21 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% ResultSet rsTer=(ResultSet)request.getAttribute("rsTer");
          ResultSet rsEmp=(ResultSet)request.getAttribute("rsEmp");
         %>
        <form action="EmployeeTerritoriesURL" method="post">
            <input type="hidden" name="service" value="insertEmployeeTerritories">
            <table>
                <caption>Insert Employee Territories</caption>
                <tr>
                    <td><label for="EmployeeID">EmployeeID</label></td>
                    <td>
                        <select name="EmployeeID" id="EmployeeID">
                            <%while(rsEmp.next()){%>
                                <option value="<%=rsEmp.getInt(1)%>"><%=rsEmp.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="TerritoryID">TerritoryID</label></td>
                    <td>
                        <select name="TerritoryID" id="TerritoryID">
                            <%while(rsTer.next()){%>
                                <option value="<%=rsTer.getInt(1)%>"><%=rsTer.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertEmployeeTerritories" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
