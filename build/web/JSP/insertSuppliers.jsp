<%-- 
    Document   : insertSuppliers.jsp
    Created on : Oct 15, 2024, 5:53:36 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SuppliersURL" method="post">
            <input type="hidden" name="service" value="insertSuppliers">
            <table>
                <caption>Insert Suppliers</caption>
                <tr>
                    <td><label for="SuppliersID">SuppliersID</label></td>
                    <td><input type="text" name="SuppliersID" id="SuppliersID" readonly></td>
                </tr>
                <tr>
                    <td><label for="companyName">companyName</label></td>
                    <td><input type="text" name="companyName" id="companyName" required></td>
                </tr>
                <tr>
                    <td><label for="contactName">contactName</label></td>
                    <td><input type="text" name="contactName" id="contactName" required></td>
                
                </tr>
                <tr>
                    <td><label for="contactTitle">contactTitle</label></td>
                    <td><input type="text" name="contactTitle" id="contactTitle" required></td>
                
                </tr>
                
                <tr>
                    <td><label for="address">address</label></td>
                    <td><input type="text" name="address" id="address"></td>
                </tr>
                <tr>
                    <td><label for="city">city</label></td>
                    <td><input type="text" name="city" id="city"></td>
                </tr>
                <tr>
                    <td><label for="region">region</label></td>
                    <td><input type="text" name="region" id="region"></td>
                </tr>
                <tr>
                    <td><label for="postalCode">postalCode</label></td>
                    <td><input type="text" name="postalCode" id="postalCode"></td>
                </tr>
                <tr>
                    <td><label for="country">country</label></td>
                    <td><input type="text" name="country" id="country"></td>
                </tr>
                <tr>
                    <td><label for="fax">fax</label></td>
                    <td><input type="text" name="fax" id="fax"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="text" name="phone" id="phone"></td>
                </tr>
                <tr>
                    <td><label for="homePage">homePage</label></td>
                    <td><input type="text" name="homePage" id="homePage"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="insertSuppliers" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>


        </form>  
    </body>
</html>
