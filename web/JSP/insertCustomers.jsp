<%-- 
    Document   : insertCustomers
    Created on : Oct 24, 2024, 8:51:30 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CustomersURL" method="post">
            <input type="hidden" name="service" value="insertCustomers">
            <table>
                <caption>Insert Customers</caption>
                <tr>
                    <td><label for="CustomerID">CustomerID</label></td>
                    <td><input type="text" name="CustomerID" id="CustomerID" required=""></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName"></td>
                </tr>
                <tr>
                    <td><label for="ContactName">ContactName</label></td>
                    <td><input type="text" name="ContactName" id="ContactName"></td>
                </tr>
                <tr>
                    <td><label for="ContactTitle">ContactTitle</label></td>
                    <td><input type="text" name="ContactTitle" id="ContactTitle"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone"></td>
                </tr>
                <tr>
                    <td><label for="Fax">Fax</label></td>
                    <td><input type="text" name="Fax" id="Fax"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertCustomers" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
