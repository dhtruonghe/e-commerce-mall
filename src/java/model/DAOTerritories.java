/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Territories;
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
public class DAOTerritories extends DBConnect{
    
    public int insertTerritory(Territories t) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Territories]\n"
                + "           ([TerritoryID]\n"
                + "           ,[TerritoryDescription]\n"
                + "           ,[RegionID])\n"
                + "     VALUES\n"
                + "('" + t.getTerritoryID()
                + "'," + t.getTerritoryDescription()
                + "'," + t.getRegionID() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addTerritory(Territories t) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Territories]\n"
                + "           ([TerritoryID]\n"
                + "           ,[TerritoryDescription]\n"
                + "           ,[RegionID])\n"
                + "     VALUES(?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, t.getTerritoryID());
            pre.setString(2, t.getTerritoryDescription());
            pre.setInt(3, t.getRegionID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateTerritory(Territories t) {
        int n = 0;
        String sql = "UPDATE [dbo].[Territories]\n"
                + "SET  [TerritoryDescription] = ?"
                + ",[RegionID] = ?"
                + " WHERE TerritoryID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, t.getTerritoryDescription());
            pre.setInt(2, t.getRegionID());
            pre.setString(3, t.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteTerritory(String id){
        int n= 0;
        String sql="delete from Territories where TerritoryID like "+"'"+id+"'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs=null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                String territoryID=rs.getString(1);
                String territoryDescription=rs.getString(2);
                int regionID=rs.getInt(3);
                Territories t= new Territories(territoryID, territoryDescription, regionID);
                System.out.println(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Territories> getTerritories(String sql){
        Vector<Territories> vector = new Vector<Territories>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
             while(rs.next()){
                String territoryID=rs.getString(1);
                String territoryDescription=rs.getString(2);
                int regionID=rs.getInt(3);
                Territories t= new Territories(territoryID, territoryDescription, regionID);
                vector.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
//        DAOTerritories dao = new DAOTerritories();
//        int n = dao.insertTerritory(new Territory("99999","Racine",4));
//        int n = dao.addTerritory(new Territory("12345","Raccon",3));
//        int n = dao.updateTerritory(new Territory("12345","Racine",2) );
//        int n= dao.deleteTerritory("99999");
//        dao.displayAll("select * from Territories");
//         Vector<Territories> vector=dao.getTerritories("select * from Territories");
//        for(Territories ter:vector){
//            System.out.println(ter);
//        }
//        if(n>0){
//            System.out.println("Successfully");
//        }
    }
}
