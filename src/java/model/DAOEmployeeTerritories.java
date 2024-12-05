/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.EmployeeTerritories;
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
public class DAOEmployeeTerritories extends DBConnect{
    
     public int insertEmpTer(EmployeeTerritories et) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES\n"
                + "           (" + et.getEmployeeID()
                + ",'" + et.getTerritoryID() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addEmpTer(EmployeeTerritories et) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES(?,?)";
        try {
            
            PreparedStatement pre =  conn.prepareStatement(sql);
            pre.setInt(1, et.getEmployeeID());
            pre.setString(2, et.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateEmpTer(EmployeeTerritories et) {
        int n = 0;
        String sql = "UPDATE [dbo].[EmployeeTerritories]\n"
                + "   SET [EmployeeID] = ?\n"
                + "      ,[TerritoryID] = ?\n"
                + " WHERE EmployeeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, et.getEmployeeID());
            pre.setString(2, et.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return n;
    }
    
    public int deleteEmpTer(int id){
        int n = 0;
        String sql = "delete from EmployeeTerritories where EmployeeID=" + id;
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
                
                int employeeID = rs.getInt(1);
                String territoryID = rs.getString(2);
                EmployeeTerritories et = new EmployeeTerritories(employeeID, territoryID);
                System.out.println(et);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public Vector<EmployeeTerritories> getEmpTer(String sql){
        Vector<EmployeeTerritories> vector = new Vector<EmployeeTerritories>();
        try {
            
//            default:ResultSet.TYPE_FORWARD_ONLY;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                
                int employeeID = rs.getInt(1);
                String territoryID = rs.getString(2);
                EmployeeTerritories et = new EmployeeTerritories(employeeID, territoryID);
                vector.add(et);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }

    public static void main(String[] args) {
        
        
    }
}
