/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Region;
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
public class DAORegion extends DBConnect{
    
    public int insertRegion(Region r){
        int n=0;
        
        String sql="INSERT INTO [dbo].[Region]\n" +
                "           ([RegionID]\n" +
                "           ,[RegionDescription])\n" +
                "     VALUES\n" +
                "           ("+r.getRegionID()
                +",'"+r.getRegionDescription()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addRegion(Region r){
        int n = 0;
        
        String sql="INSERT INTO [dbo].[Region]\n" +
                "           ([RegionID]\n" +
                "           ,[RegionDescription])\n" +
                "     VALUES\n" +
                "           (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, r.getRegionID());
            pre.setString(2, r.getRegionDescription());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateRegion(Region r){
        
        int n=0;
        String sql="UPDATE [dbo].[Region]\n" +
            "   SET [RegionDescription] = ?\n" +
            " WHERE RegionID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, r.getRegionDescription());
            pre.setInt(2, r.getRegionID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
        
    }
    
    public int deleteRegion(int id){
        int n = 0;
        String sql = "delete from Region where regionid = "+id;
        try {
            Statement state =conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs=state.executeQuery(sql);
            while(rs.next()){
                int regionID = rs.getInt(1);
     String regionDescription= rs.getString(2);
     Region reg = new Region(regionID, regionDescription);
                System.out.println(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Vector<Region> getRegions(String sql){
        Vector<Region> vector = new Vector<Region>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int regionID = rs.getInt(1);
     String regionDescription= rs.getString(2);
     Region reg = new Region(regionID, regionDescription);
     vector.add(reg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        
    }
}
