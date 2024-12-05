/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Suppliers;
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
public class DAOSuppliers extends DBConnect{
    
    public int insertSupplier(Suppliers sup) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES\n"
                + "('" + sup.getCompanyName() 
                + "','" + sup.getContactName() 
                + "','" + sup.getContactTitle()
                + "','" + sup.getAddress() 
                + "','" + sup.getCity() 
                + "','" + sup.getRegion() 
                + "','" + sup.getPostalCode()
                + "','" + sup.getCountry() 
                + "','" + sup.getPhone() 
                + "','" + sup.getFax() 
                + "','" + sup.getHomePage() + ")";
        System.out.println(sql);
        try {
            
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addSupplier(Suppliers sup) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateSupplier(Suppliers sup) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + "      ,[HomePage] = ?\n"
                + " WHERE SupplierID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());
            pre.setInt(12, sup.getSupplierID());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteSupplier(int id) {
        int n = 0;
        String sql = "delete from Suppliers where SupplierID=" + id;
        try {
            
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return n;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int supplierID = rs.getInt(1);
                
                String companyName = rs.getString(2), contactName = rs.getString(3),
                        contactTitle = rs.getString(4), address = rs.getString(5), city = rs.getString(6),
                        region = rs.getString(7), postalCode = rs.getString(8), country = rs.getString(9),
                        phone = rs.getString(10), fax = rs.getString(11), homePage = rs.getString(12);
                
                Suppliers sup = new Suppliers(supplierID, companyName, contactName, contactTitle,
                         address, city, region, postalCode, country, phone, fax, homePage);
                System.out.println(sup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Vector<Suppliers> getSupplierses(String sql) {
        Vector<Suppliers> vector = new Vector<Suppliers>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                
                int supplierID = rs.getInt(1);
                String companyName = rs.getString("CompanyName"), contactName = rs.getString(3),
                        contactTitle = rs.getString(4), address = rs.getString(5), city = rs.getString(6),
                        region = rs.getString(7), postalCode = rs.getString(8), country = rs.getString(9),
                        phone = rs.getString(10), fax = rs.getString(11), homePage = rs.getString(12);
                Suppliers sup = new Suppliers(supplierID, companyName, contactName, contactTitle,
                         address, city, region, postalCode, country, phone, fax, homePage);
                vector.add(sup);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
//        DAOSuppliers dao = new DAOSuppliers();
//        dao.displayAll("select * from Suppliers");
//        Vector<Suppliers> vector=dao.getSupplierses("select * from Suppliers");
//        for(Suppliers sup:vector){
//            System.out.println(sup);
//        }
    }
}
