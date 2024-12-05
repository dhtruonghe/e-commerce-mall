<%-- 
    Document   : insertOrderDetails
    Created on : Oct 24, 2024, 8:18:10 PM
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
         <% ResultSet rsPro=(ResultSet)request.getAttribute("rsPro");
         ResultSet rsOrder=(ResultSet)request.getAttribute("rsOrder");
         %>
        <form action="OrderDetailsURL" method="post">
            <input type="hidden" name="service" value="insertOrderDetails">
            <table>
                <caption>Insert Order Details</caption>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td>
                        <select name="OrderID" id="OrderID">
                            <%while(rsOrder.next()){%>
                                <option value="<%=rsOrder.getInt(1)%>"><%=rsOrder.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="ProductID">ProductID</label></td>
                    <td>
                        <select name="ProductID" id="ProductID">
                            <%while(rsPro.next()){%>
                                <option value="<%=rsPro.getInt(1)%>"><%=rsPro.getString(2)%></option>
                            <%}%>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="UnitPrice">UnitPrice</label></td>
                    <td><input type="text" name="UnitPrice" id="UnitPrice"></td>
                </tr>
                <tr>
                    <td><label for="Quantity">Quantity</label></td>
                    <td><input type="text" name="Quantity" id="Quantity"></td>
                </tr>
                <tr>
                    <td><label for="Discount">Discount</label></td>
                    <td><input type="text" name="Discount" id="Discount"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertOrderDetails" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
