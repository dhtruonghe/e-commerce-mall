<%-- 
    Document   : listSuppliers
    Created on : Oct 15, 2024, 6:16:27 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Suppliers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Suppliers> vector = (Vector<Suppliers>)request.getAttribute("dataSuppliers");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="SuppliersURL" method="get">
            <p>Company Name <input type="text" name="cname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="SuppliersURL?service=insertSuppliers">insert Suppliers</a></p>
        <table border=1>
            <caption><%=title%></caption>
                              <tr>
                                  <th>SupplierID</th>
                                  <th>companyName</th>
                                  <th>contactName</th>
                                  <th>contactTitle</th>
                                  <th>address</th>
                                  <th>city</th>
                                  <th>region</th>
                                  <th>postalCode</th>
                                  <th>country</th>
                                  <th>fax </th>
                                  <th>phone</th>
                                  <th>homePage</th>
                                  <th>update</th>
                                  <th>delete</th>
                              </tr>
                              <%for(Suppliers supplier : vector){%>
                              <tr>
                                  <td><%=supplier.getSupplierID()%></td>
                                  <td><a href="ProductURL?service=searchSupplierID&sid=<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></a></td>
                                  <td><%=supplier.getContactName()%></td>
                                  <td><%=supplier.getContactTitle()%></td>
                                  <td><%=supplier.getAddress()%></td>
                                  <td><%=supplier.getCity()%></td>
                                  <td><%=supplier.getRegion()%></td>
                                  <td><%=supplier.getPostalCode()%></td>
                                  <td><%=supplier.getCountry()%></td>
                                  <td><%=supplier.getPhone()%></td>
                                  <td><%=supplier.getFax()%></td>
                                  <td><%=supplier.getHomePage()%></td>
                                  <td><a href="SuppliersURL?service=updateSuppliers&sid=<%=supplier.getSupplierID()%>">update</a></td>
                                  <td><a href="SuppliersURL?service=deleteSuppliers&sid=<%=supplier.getSupplierID()%>">delete</a></td>
                              </tr>
                              <%}%>
    </body>
</html>
