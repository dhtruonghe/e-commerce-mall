/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author daica
 */
public class DAOBill extends DBConnect {

    Bill bill = new Bill();
    List<Bill> bills = new ArrayList<>();

    public List<Bill> getBill(int orderID) {
        String sql = "SELECT o.orderId, o.orderDate, o.RequiredDate, o.ShippedDate, c.ContactName, "
            + "c.Address, c.Phone, c.Fax, p.ProductID, p.ProductName, od.UnitPrice, "
            + "od.Quantity, od.Discount "
            + "FROM Orders o "
            + "JOIN Customers c ON o.customerId = c.customerId "
            + "JOIN [Order Details] od ON o.orderId = od.orderId "
            + "JOIN Products p ON od.productId = p.productId "
            + "WHERE o.OrderID = ?";

        List<Bill> bills = new ArrayList<>();

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, orderID);

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Bill bill = new Bill();

                    // Assuming appropriate setters exist in Bill class
                    bill.setOrderID(rs.getInt("orderId"));
                    bill.setOrderDate(rs.getDate("orderDate"));
                    bill.setRequiredDate(rs.getDate("RequiredDate"));
                    bill.setShippedDate(rs.getDate("ShippedDate"));
                    bill.setContactName(rs.getString("ContactName"));
                    bill.setAddress(rs.getString("Address"));
                    bill.setPhone(rs.getString("Phone"));
                    bill.setFax(rs.getString("Fax"));
                    bill.setProductID(rs.getInt("ProductID"));
                    bill.setProductName(rs.getString("ProductName"));
                    bill.setUnitPrice(rs.getDouble("UnitPrice"));
                    bill.setQuantity(rs.getInt("Quantity"));
                    bill.setDiscount(rs.getDouble("Discount"));

                    bills.add(bill);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bills;
    }
    
}