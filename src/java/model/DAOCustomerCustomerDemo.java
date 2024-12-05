/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haitr
 */
public class DAOCustomerCustomerDemo extends DBConnect {

    public int insertDemo(CustomerCustomerDemo demo) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES\n"
                + "           ('" + demo.getCustomerID() 
                + "','" + demo.getCustomerTypeID() + "')";

        System.out.println(sql);
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addDemo(CustomerCustomerDemo demo) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES\n"
                + "           (?,?)";
        System.out.println(sql);
        try {
            
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, demo.getCustomerID());
            pre.setString(2, demo.getCustomerTypeID());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateDemo(CustomerCustomerDemo demo) {
        int n = 0;
        
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[CustomerTypeID] = ?\n"
                + " WHERE CustomerID=?";
        System.out.println(sql);
        try {
            
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, demo.getCustomerID());
            pre.setString(2, demo.getCustomerTypeID());
            pre.setString(3, demo.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    public int deleteDemo(int id){
        int n = 0;
        String sql ="Delete from CustomerCustomerDemo "
                + "Where CustomerID like '"+id+"'";
        try {
            
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        try {
            
            Statement state = conn.createStatement();
            rs= state.executeQuery(sql);
            while(rs.next()){
                String customerID=rs.getString(1),customerTypeID=rs.getString(2);
                CustomerCustomerDemo demo = new CustomerCustomerDemo(customerID, customerTypeID);
                System.out.println(demo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public Vector<CustomerCustomerDemo> getCustomerCustomerDemos(String sql){
       Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                 String customerID=rs.getString(1),customerTypeID=rs.getString(2);
                 CustomerCustomerDemo demo = new CustomerCustomerDemo();
                 vector.add(demo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
   }
   
   public static void main(String[] args) {
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();

        
        
        Vector<CustomerCustomerDemo> vector=dao.getCustomerCustomerDemos("select * from CustomerCustomerDemo");
        for(CustomerCustomerDemo cus : vector){
            System.out.println(cus);
        }
    }

}
