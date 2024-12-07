/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author haitr
 */
public class DAOOrder extends DBConnect {

    public int insertOrder(Orders o) {
        int n = 0;

        // Correctly format the SQL statement with all values properly enclosed
        String sql = "INSERT INTO [dbo].[Orders] "
                + "([OrderID],[CustomerID], [EmployeeID], [OrderDate], [RequiredDate], [ShippedDate], "
                + "[ShipVia], [Freight], [ShipName], [ShipAddress], [ShipCity], "
                + "[ShipRegion], [ShipPostalCode], [ShipCountry]) "
                + "VALUES (" + 10251
                + ",'" + o.getCustomerID()
                + "', " + o.getEmployeeID()
                + ", '" + o.getOrderDate()
                + "', '" + o.getRequiredDate()
                + "', '"
                + o.getShippedDate()
                + "', " + o.getShipVia()
                + ", "
                + o.getFreight()
                + ", '" + o.getShipName()
                + "', '" + o.getShipAddres()
                + "', '" + o.getShipCity()
                + "', '" + o.getShipRegion()
                + "', '" + o.getShipPostalCode()
                + "', '" + o.getShipCountry() + "')";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    

    public int addOrder(Orders o) {
        int n = 0;
        int orderID = -1;
        String sql = "INSERT INTO [dbo].[Orders] "
                + "([OrderID],[CustomerID], [EmployeeID], [OrderDate], [RequiredDate], [ShippedDate], "
                + "[ShipVia], [Freight], [ShipName], [ShipAddress], [ShipCity], "
                + "[ShipRegion], [ShipPostalCode], [ShipCountry]) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getOrderID());
            pre.setString(2, o.getCustomerID());
            pre.setInt(3, o.getEmployeeID());
            pre.setString(4, o.getOrderDate());
            pre.setString(5, o.getRequiredDate());
            pre.setString(6, o.getShippedDate());
            pre.setInt(7, o.getShipVia());

            pre.setDouble(8, o.getFreight());
            pre.setString(9, o.getShipName());
            pre.setString(10, o.getShipAddres());
            pre.setString(11, o.getShipCity());
            pre.setString(12, o.getShipRegion());
            pre.setString(13, o.getShipPostalCode());
            pre.setString(14, o.getShipCountry());
            n = pre.executeUpdate();
            if (n > 0) {
                try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1);  // Lấy ID của đơn hàng vừa chèn
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderID;
    }

    public int updateOrder(Orders o) {
        int n = 0;

        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[EmployeeID] = ?\n"
                + "      ,[OrderDate] = ?\n"
                + "      ,[RequiredDate] = ?\n"
                + "      ,[ShippedDate] = ?\n"
                + "      ,[ShipVia] = ?\n"
                + "      ,[Freight] = ?\n"
                + "      ,[ShipName] = ?\n"
                + "      ,[ShipAddress] = ?\n"
                + "      ,[ShipCity] = ?\n"
                + "      ,[ShipRegion] = ?\n"
                + "      ,[ShipPostalCode] = ?\n"
                + "      ,[ShipCountry] = ?\n"
                + " WHERE OrderID = ?";

        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getOrderID());
            pre.setString(2, o.getCustomerID());
            pre.setInt(3, o.getEmployeeID());
            pre.setString(4, o.getOrderDate());
            pre.setString(5, o.getRequiredDate());
            pre.setString(6, o.getShippedDate());
            pre.setInt(7, o.getShipVia());
            pre.setDouble(8, o.getFreight());
            pre.setString(9, o.getShipName());
            pre.setString(10, o.getShipAddres());
            pre.setString(11, o.getShipCity());
            pre.setString(12, o.getShipRegion());
            pre.setString(13, o.getShipPostalCode());

            pre.setString(14, o.getShipCountry());
            n = pre.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteOrder(int id) {
        int n = 0;

        String sql = "delete from Orders where OrderID=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String customerID = rs.getString(2);
                int employeeID = rs.getInt(3);

                String orderDate = rs.getString(4), requiredDate = rs.getString(5), shippedDate = rs.getString(6);
                int shipVia = rs.getInt(7);
                double freight = rs.getDouble(8);

                String shipName = rs.getString(9), shipAddres = rs.getString(10), shipCity = rs.getString(11), shipRegion = rs.getString(12), shipPostalCode = rs.getString(13), shipCountry = rs.getString(14);
                Orders o = new Orders(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddres, shipCity, shipRegion, shipPostalCode, shipCountry);
                System.out.println(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
//            default:ResultSet.TYPE_FORWARD_ONLY;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int orderID = rs.getInt(1);
                String customerID = rs.getString(2);
                int employeeID = rs.getInt(3);

                String orderDate = rs.getString(4), requiredDate = rs.getString(5), shippedDate = rs.getString(6);
                int shipVia = rs.getInt(7);
                double freight = rs.getDouble(8);
                String shipName = rs.getString(9), shipAddres = rs.getString(10), shipCity = rs.getString(11), shipRegion = rs.getString(12), shipPostalCode = rs.getString(13), shipCountry = rs.getString(14);
                Orders o = new Orders(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddres, shipCity, shipRegion, shipPostalCode, shipCountry);
                vector.add(o);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public static void main(String[] args) {

    }
}
