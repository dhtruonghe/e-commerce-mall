<%-- 
    Document   : listEmployees
    Created on : Oct 24, 2024, 7:03:22 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Employees" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Employees> vector = (Vector<Employees>)request.getAttribute("dataEmployees");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="EmployeesURL" method="get">
            <p>First Name <input type="text" name="fname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="EmployeesURL?service=insertEmployees">insert Employees</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>EmployeeID</th>
                <th>LastName</th>
                <th>FirstName</th>
                <th>Title</th>
                <th>TitleOfCourtesy</th>
                <th>BirthDate</th>
                <th>HireDate</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>HomePhone</th>
                <th>Extension</th>
                <th>Photo</th>
                <th>Notes</th>
                <th>ReportsTo</th>
                <th>PhotoPath</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(Employees employee : vector){%>
            <tr>
                <td><%=employee.getEmployeeID()%></td>
                <td><%=employee.getLastName()%></td>
                <td><%=employee.getFirstName()%></td>
                <td><%=employee.getTitle()%></td>
                <td><%=employee.getTitleOfCourtesy()%></td>
                <td><%=employee.getBirthDate()%></td>
                <td><%=employee.getHireDate()%></td>
                <td><%=employee.getAddress()%></td>
                <td><%=employee.getCity()%></td>
                <td><%=employee.getRegion()%></td>
                <td><%=employee.getPostalCode()%></td>
                <td><%=employee.getCountry()%></td>
                <td><%=employee.getHomePhone()%></td>
                <td><%=employee.getExtension()%></td>
                <td><%=employee.getPhoto()%></td>
                <td><%=employee.getNotes()%></td>
                <td><%=employee.getReportsTo()%></td>
                <td><%=employee.getPhotoPath()%></td>
                <td><a href="EmployeesURL?service=updateEmployees&eid=<%=employee.getEmployeeID()%>">update</a></td>
                <td><a href="EmployeesURL?service=deleteEmployees&eid=<%=employee.getEmployeeID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
