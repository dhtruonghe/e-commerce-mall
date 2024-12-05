/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employees;
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
public class DAOEmployee extends DBConnect{
    public int insertEmployee(Employees e){
        int n=0;
        
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([EmployeeID]\n"
                + "           ,[LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES\n"
                + "(" + e.getEmployeeID() 
                + ",'" + e.getLastName() 
                + ",'" + e.getFirstName() 
                + ",'" + e.getTitle() 
                + ",'" + e.getTitleOfCourtesy() 
                + ",'" + e.getBirthDate() 
                + ",'" + e.getHireDate() 
                + ",'" + e.getAddress() 
                + ",'" + e.getCity() 
                + ",'" + e.getRegion() 
                + ",'" + e.getPostalCode() 
                + ",'" + e.getCountry() 
                + ",'" + e.getHomePhone() 
                + ",'" + e.getExtension() 
                + ",'" + e.getNotes() 
                + "," + e.getReportsTo()
                + ",'" + e.getPhotoPath() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addEmployee(Employees e) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Photo]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setString(14, e.getPhoto());
            pre.setString(15, e.getNotes());
            pre.setInt(16, e.getReportsTo());
            pre.setString(17, e.getPhotoPath());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateEmp(Employees e) {
        int n = 0;
        
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [LastName] = ?\n"
                + "      ,[FirstName] = ?\n"
                + "      ,[Title] = ?\n"
                + "      ,[TitleOfCourtesy] = ?\n"
                + "      ,[BirthDate] = ?\n"
                + "      ,[HireDate] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[HomePhone] = ?\n"
                + "      ,[Extension] = ?\n"
                + "      ,[Photo] =?\n"
                + "      ,[Notes] = ?\n"
                + "      ,[ReportsTo] = ?\n"
                + "      ,[PhotoPath] = ?\n"
                + " WHERE EmployeeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setString(14, e.getPhoto());
            pre.setString(15, e.getNotes());
            pre.setInt(16, e.getReportsTo());
            pre.setString(17, e.getPhotoPath());
            pre.setInt(18, e.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteEmp(int eid){
        int n = 0;
        
        String sql = "delete from Employees where EmployeeID=" + eid;
        try {
            
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        
        try {
            
//            default:ResultSet.TYPE_FORWARD_ONLY;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
            
            while (rs.next()) {
                
                int EmployeeID = rs.getInt(1);
                String LastName = rs.getString(2), FirstName= rs.getString(3), Title= rs.getString(4)
                        , TitleOfCourtesy= rs.getString(5),BirthDate= rs.getString(6)
                        ,HireDate= rs.getString(7), Address= rs.getString(8), City= rs.getString(9)
                        , Region= rs.getString(10), PostalCode= rs.getString(11), Country= rs.getString(12)
                        , HomePhone= rs.getString(13), Extension= rs.getString(14), Photo= rs.getString(15)
                        , Notes= rs.getString(16);
                int ReportsTo = rs.getInt(17);
                String PhotoPath = rs.getString(18);
                Employees e = new Employees(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath);
                System.out.println(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Employees> getEmployees(String sql){
        
        Vector<Employees> vector = new Vector<Employees>();
        try {
//            default:ResultSet.TYPE_FORWARD_ONLY;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int EmployeeID = rs.getInt(1);
                String LastName = rs.getString(2), FirstName= rs.getString(3), Title= rs.getString(4)
                        , TitleOfCourtesy= rs.getString(5),BirthDate= rs.getString(6)
                        ,HireDate= rs.getString(7), Address= rs.getString(8), City= rs.getString(9)
                        , Region= rs.getString(10), PostalCode= rs.getString(11), Country= rs.getString(12)
                        , HomePhone= rs.getString(13), Extension= rs.getString(14), Photo= rs.getString(15)
                        , Notes= rs.getString(16);
                int ReportsTo = rs.getInt(17);
                String PhotoPath = rs.getString(18);
                Employees e = new Employees(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath);
                vector.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return vector;
    }
    
    public static void main(String[] args) {
        DAOEmployee dao = new DAOEmployee();

        int n = dao.insertEmployee(new Employees(10, "LastName",
                 "FirstName",
                 "Title",
                 "TitleOfCourtesy",
                 "BirthDate",
                 "HireDate",
                 "Address",
                 "City",
                 "Region",
                 "PostalCode",
                 "Country",
                 "HomePhone",
                 "Extension",
                 "",
                 "Notes",
                 1,
                 "PhotoPath"));

        if (n > 0) {
            System.out.println("Successfully");
        }

    }
}
