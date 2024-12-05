/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.OrderDetails;
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
public class DAOOrderDetails extends DBConnect{
    
    public int insertOD(OrderDetails od){
        int n=0;
        
        String sql="INSERT INTO [dbo].[Order Details]\n" +
"           ([OrderID]\n" +
"           ,[ProductID]\n" +
"           ,[UnitPrice]\n" +
"           ,[Quantity]\n" +
"           ,[Discount])\n" +
"     VALUES\n" +
"           ("+od.getOrderID()
                +","+od.getProductID()
                +","+od.getUnitPrice()
                +","+od.getQuantity()
                +","+od.getDiscount()+")";
        System.out.println(sql);
        try {
            
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addOD(OrderDetails od){
        int n = 0;
        
        String sql="INSERT INTO [dbo].[Order Details]\n" +
"           ([OrderID]\n" +
"           ,[ProductID]\n" +
"           ,[UnitPrice]\n" +
"           ,[Quantity]\n" +
"           ,[Discount])\n" +
"     VALUES\n" +
"           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateOD(OrderDetails od){
        int n = 0;
        
        String sql = "UPDATE [dbo].[Order Details]\n"
                + "   SET [OrderID] = ?\n"
                + "      ,[ProductID] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Discount] = ?\n"
                + " WHERE OrderID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, od.getOrderID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int deleteOD(int id){
        int n = 0;
        String sql="DELETE FROM [dbo].[Order Details] WHERE OrderID = "+id;
        try {
            
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs=state.executeQuery(sql);
            
            while(rs.next()){
                int orderID = rs.getInt(1), productID = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                
                double discount = rs.getDouble(5);
                OrderDetails od = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                System.out.println(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<OrderDetails> getDetails(String sql){
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int orderID = rs.getInt(1), productID = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getDouble(5);
                
                OrderDetails od = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {

    }
}
