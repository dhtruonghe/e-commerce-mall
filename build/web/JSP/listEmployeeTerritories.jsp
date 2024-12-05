<%-- 
    Document   : listEmployeeTerritories
    Created on : Oct 24, 2024, 6:49:04 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.EmployeeTerritories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <body>
        <% Vector<EmployeeTerritories> vector = (Vector<EmployeeTerritories>)request.getAttribute("dataEmployeeTerritories");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="EmployeeTerritoriesURL" method="get">
            <p>Employee ID <input type="text" name="eid" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="EmployeeTerritoriesURL?service=insertEmployeeTerritories">insert Employee Territories</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>EmployeeID</th>
                <th>TerritoryID</th>
                <th>delete</th>
            </tr>
            <%for(EmployeeTerritories employee : vector){%>
            <tr>
                <td><%=employee.getEmployeeID()%></td>
                <td><%=employee.getTerritoryID()%></td>
                <td><a href="EmployeeTerritoriesURL?service=updateEmployeeTerritories&eid=<%=employee.getEmployeeID()%>">update</a></td>
                <td><a href="EmployeeTerritoriesURL?service=deleteEmployeeTerritories&eid=<%=employee.getEmployeeID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
