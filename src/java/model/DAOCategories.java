/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Categories;
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
public class DAOCategories extends DBConnect{
    
    public int insertCategories(Categories cat) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES\n"
                + "           ('" + cat.getCategoryName() 
                + "','" + cat.getDescription() 
                + "','" + cat.getPicture() + "')";
        System.out.println(sql);
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addCategories(Categories cat) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description])\n"
               
                + "     VALUES(?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateCategories(Categories cat) {
        int n = 0;
        
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [CategoryName] = ?\n"
                + "      ,[Description] = ?\n"
                
                + " WHERE CategoryID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            
            pre.setInt(3, cat.getCategoryID());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }
    
    public int deleteCategories(int cid){
        int n=0;
        String sql ="delete from Categories where CategoryID="+cid;
        try {
            
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int CategoryID = rs.getInt(1);
                String CategoryName = rs.getString("CategoryName"),
                        Description = rs.getString(3),
                        Picture = rs.getString(4);
                Categories cat = new Categories(CategoryID, CategoryName, Description, Picture);
                System.out.println(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Categories> getCategorieses(String sql){
        Vector<Categories> vector = new Vector<Categories>();
        
        try {
//            default:ResultSet.TYPE_FORWARD_ONLY;

            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                
                int CategoryID = rs.getInt(1);
                String CategoryName = rs.getString(2),
                        Description = rs.getString(3),
                        Picture = rs.getString(4);
                Categories cat = new Categories(CategoryID, CategoryName, Description, Picture);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();

        
        
        
        Vector<Categories> vector=dao.getCategorieses("select * from Categories");
        for(Categories cat : vector){
            System.out.println(cat);
        }
    }
}
