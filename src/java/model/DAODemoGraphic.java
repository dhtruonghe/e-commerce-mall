/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.CustomerDemoGraphics;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author daica
 */
public class DAODemoGraphic extends DBConnect{
    public int insertGraphic(CustomerDemoGraphics gra){
        int n = 0;
        String sql ="INSERT INTO [dbo].[CustomerDemographics]\n" +
"           ([CustomerTypeID]\n" +
"           ,[CustomerDesc])\n" +
"     VALUES\n" +
"           ('"+gra.getCustomerTypeID()+"','"+gra.getCustomerDesc()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n= state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAODemoGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addGraphic(CustomerDemoGraphics gra){
        int n=0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n" +
"           ([CustomerTypeID]\n" +
"           ,[CustomerDesc])\n" +
"     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, gra.getCustomerTypeID());
            pre.setString(2, gra.getCustomerDesc());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODemoGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateGraphic(CustomerDemoGraphics gra){
        int n = 0;
        String sql="UPDATE [dbo].[CustomerDemographics]\n" +
"   SET [CustomerTypeID] = ?\n" +
"      ,[CustomerDesc] = ?\n" +
" WHERE CustomerTypeID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, gra.getCustomerTypeID());
            pre.setString(2, gra.getCustomerDesc());
            pre.setString(3, gra.getCustomerDesc());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODemoGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteGraphic(int gid){
        int n = 0;
        String sql="delete from CustomerDemographics WHERE CustomerTypeID like '"+gid+"'";
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAODemoGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs=state.executeQuery(sql);
            while(rs.next()){
                String customerTypeID=rs.getString(1),customerDesc=rs.getString(2);
                CustomerDemoGraphics gra = new CustomerDemoGraphics(customerTypeID, customerDesc);
                System.out.println(gra);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODemoGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<CustomerDemoGraphics> getCustomerDemographicses(String sql){
         Vector<CustomerDemoGraphics> vector = new Vector<CustomerDemoGraphics>();
        try {
//            default:ResultSet.TYPE_FORWARD_ONLY;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String customerTypeID=rs.getString(1),customerDesc=rs.getString(2);
                CustomerDemoGraphics gra = new CustomerDemoGraphics(customerTypeID, customerDesc); 
                vector.add(gra);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }    
}
