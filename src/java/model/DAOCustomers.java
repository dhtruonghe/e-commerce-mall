    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Customers;
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
public class DAOCustomers extends DBConnect{
    
    public Customers login(String userName, String password){
        Customers customer = null;
        String sql = "select * from Customers where CustomerID =? and Password=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                customer = new Customers(rs.getString(1), rs.getString(2)
                        ,rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), 
                        rs.getString(11), rs.getString(12));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    
    public int insertCustomer(Customers cus){
        int n=0;
        
        String sql="INSERT INTO [dbo].[Customers]\n" +
"           ([CustomerID]\n" +
"           ,[CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax])\n" +
"     VALUES\n" +
"           ('" + cus.getCustomerID() 
                +"','" + cus.getCompanyName()
                +"','" + cus.getContactName()
                +"','" + cus.getContactTitle()
                +"','" + cus.getAddress()
                +"','" + cus.getCity()
                +"','" + cus.getRegion()
                +"','" + cus.getPostalCode()
                +"','" + cus.getCountry()
                +"','" + cus.getPhone()
                +"','" + cus.getFax()+"')";
        System.out.println(sql);    
        
        try {
            Statement state= conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    } 
    
    public int addCustomers(Customers cus){
        int n=0;
        
         String sql="INSERT INTO [dbo].[Customers]\n" +
"           ([CustomerID]\n" +
"           ,[CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax])\n" +
"     VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateCustomer(Customers cus){
        int n=0;
        String sql="UPDATE [dbo].[Customers]\n" +
"   SET [CompanyName] = ?\n" +
"      ,[ContactName] = ?\n" +
"      ,[ContactTitle] = ?\n" +
"      ,[Address] = ?\n" +
"      ,[City] = ?\n" +
"      ,[Region] = ?\n" +
"      ,[PostalCode] = ?\n" +
"      ,[Country] = ?\n" +
"      ,[Phone] = ?\n" +
"      ,[Fax] = ?\n" +
" WHERE CustomerID=?";
        try {
            
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getCustomerID());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteCustomer(String cid){
        int n=0;
        
        String sql ="delete from Customers where CustomerID like"+cid;
        try {
            
            Statement state=conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            
            while (rs.next()) {
                String CustomerID = rs.getString(1), CompanyName = rs.getString(2),
                        ContactName = rs.getString(3),
                        ContactTitle = rs.getString(4),
                        Address = rs.getString(5),
                        City = rs.getString(6),
                        Region = rs.getString(7),
                        PostalCode = rs.getString(8),
                        Country = rs.getString(9),
                        Phone = rs.getString(10),
                        Fax = rs.getString(11),
                        Password = rs.getString(12);;
                Customers cus = new Customers(CustomerID, CompanyName,
                        ContactName,
                        ContactTitle,
                        Address,
                        City,
                        Region,
                        PostalCode,
                        Country,
                        Phone,
                        Fax,
                        Password);
                System.out.println(cus.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Vector<Customers> getCustomerses(String sql){
        Vector<Customers> vector = new Vector<Customers>();
        
        try {
            
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String CustomerID = rs.getString(1), CompanyName = rs.getString(2),
                        ContactName = rs.getString(3),
                        ContactTitle = rs.getString(4),
                        Address = rs.getString(5),
                        City = rs.getString(6),
                        Region = rs.getString(7),
                        PostalCode = rs.getString(8),
                        Country = rs.getString(9),
                        Phone = rs.getString(10),
                        Fax = rs.getString(11),
                        Password = rs.getString(12);
                Customers cus = new Customers(CustomerID, CompanyName,
                        ContactName,
                        ContactTitle,
                        Address,
                        City,
                        Region,
                        PostalCode,
                        Country,
                        Phone,
                        Fax, 
                        Password);
                
                vector.add(cus);
            }    
        } catch (SQLException ex) {
            
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        

        dao.displayAll("select * from Customers");
        Vector<Customers> vector = dao.getCustomerses("select * from Customers");
        for(Customers cus:vector){
            System.out.println(cus);
        }
    }
}
