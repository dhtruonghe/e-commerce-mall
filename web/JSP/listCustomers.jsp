<%-- 
    Document   : listCustomers
    Created on : Oct 15, 2024, 9:13:49 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Customers> vector = (Vector<Customers>)request.getAttribute("dataCustomers");
        String title = (String)request.getAttribute("titleTable");
        %>
        <% Customers cus = (Customers)session.getAttribute("customer");%>
        <p align="right">
            <%if(cus==null){%>
            <a href="CustomersURL?service=loginCustomer">login</a>
            <%} else{%>
            <%= "Welcome"+cus%>
            <a href="CustomersURL?service=logoutCustomer">logout</a>
            <%}%>
        </p>
        <form action="CustomersURL" method="get">
            <p>Company Name <input type="text" name="companyName" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CustomersURL?service=insertCustomers">insert Customers</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>CustomerID</th>
                <th>CompanyName</th>
                <th>ContactName</th>
                <th>ContactTitle</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>Phone</th>
                <th>Fax</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <% for(Customers customer : vector){ %>
            <tr>
                <td><%=customer.getCustomerID()%></td>
                <td><%=customer.getCompanyName()%></td>
                <td><a href="OrdersURL?service=searchCustomerID&cid=<%=customer.getCustomerID()%>"><%=customer.getContactName()%></a></td>
                <td><%=customer.getContactTitle()%></td>
                <td><%=customer.getAddress()%></td>
                <td><%=customer.getCity()%></td>
                <td><%=customer.getRegion()%></td>
                <td><%=customer.getPostalCode()%></td>
                <td><%=customer.getCountry()%></td>
                <td><%=customer.getPhone()%></td>
                <td><%=customer.getFax()%></td>
                <td><a href="CustomersURL?service=updateCustomers&cid=<%=customer.getCustomerID()%>">update</a></td>
                <td><a href="CustomersURL?service=deleteCustomers&cid=<%=customer.getCustomerID()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
