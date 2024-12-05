/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Shippers;
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
public class DAOShippers extends DBConnect{
    
    public int insertShipper(Shippers s) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "('" + s.getCompanyName() 
                + "','" + s.getPhone() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addShipper(Shippers s) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int updateShipper(Shippers s) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shippers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[Phone] = ?\n"
                + " WHERE ShipperID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setInt(3, s.getShipperID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteShipper(int id) {
        int n = 0;
        String sql = "delete from Shippers where ShipperID=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int shipperID = rs.getInt(1);
                String companyName = rs.getString(2), phone = rs.getString(3);
                Shippers ship = new Shippers(shipperID, companyName, phone);
                System.out.println(ship);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Shippers> getShipperses(String sql) {
        Vector<Shippers> vector = new Vector<Shippers>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int shipperID = rs.getInt(1);
                String companyName = rs.getString(2), phone = rs.getString(3);
                Shippers ship = new Shippers(shipperID, companyName, phone);
                vector.add(ship);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        
    }
}
