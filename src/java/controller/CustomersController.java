/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomers;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author haitr
 */
@WebServlet(name = "CustomersController", urlPatterns = {"/CustomersURL"})
public class CustomersController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOCustomers dao = new DAOCustomers();

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            
            if (service.equals("deleteCustomers")) {
                dao.deleteCustomer(request.getParameter("cid"));
                response.sendRedirect("CustomersURL?service=listAll");
            }
            
            if (service.equals("logoutCustomer")) {
                session.invalidate();
                request.getRequestDispatcher("/JSP/listCustomers.jsp").forward(request, response);
            }
            
            
            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                } else {
                    Customers cus = dao.login(request.getParameter("username"),
                            request.getParameter("password"));
                    if (cus == null) {
                        request.setAttribute("error", "Login fail");
                        request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                    } else {
                        String sql = "select * from Customers";

                        session.setAttribute("customer", cus);
                        Vector<Customers> vector = dao.getCustomerses(sql);
                        request.setAttribute("dataCustomers", vector);
                        request.setAttribute("titleTable", "Customers Manage");
                        request.getRequestDispatcher("/JSP/listCustomers.jsp").forward(request, response);

                    }

                }
            }

            if (service.equals("listAll")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select * from Customers";

                } else {
                    String companyName = request.getParameter("companyName");
                    sql = "select * from Customers where CompanyName like '%" + companyName + "%'";

                }
                Vector<Customers> vector = dao.getCustomerses(sql);
                for (Customers cus : vector) {
                    System.out.println(cus.toString());
                }
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listCustomers.jsp");

                request.setAttribute("dataCustomers", vector);
                request.setAttribute("titleTable", "Customers Manage");

                dispath.forward(request, response);
            }

            if (service.equals("updateCustomers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    Vector<Customers> vector = dao.getCustomerses("SELECT * FROM Customers where CustomerID=" + cid);

                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateCustomers.jsp");
                } else {
                    //if (submit != null) {
                    String customerID = request.getParameter("CustomerID");
                    String companyName = request.getParameter("CompanyName");
                    String contactName = request.getParameter("ContactName");
                    String contactTitle = request.getParameter("ContactTitle");
                    String address = request.getParameter("Address");
                    String city = request.getParameter("City");
                    String region = request.getParameter("Region");
                    String postalCode = request.getParameter("PostalCode");
                    String country = request.getParameter("Country");
                    String phone = request.getParameter("Phone");
                    String fax = request.getParameter("Fax");
                    String pass = request.getParameter("Password");
                    //check data - validate
                    if (companyName.equals("")) {
                        out.print("CompanyName empty");
                    }

                    Customers customer = new Customers(customerID, companyName, contactName,
                            contactTitle, address, city, region, postalCode, country,
                            phone, fax, pass);

                    int n = dao.updateCustomer(customer);
                    response.sendRedirect("CustomersURL");
                }
            }
            if (service.equals("insertCustomers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    
                    dispath(request, response, "/JSP/insertCustomers.jsp");
                } else {
                    //if (submit != null) {
                    String CustomerID = request.getParameter("CustomerID");
                    String companyName = request.getParameter("CompanyName");
                    String contactName = request.getParameter("ContactName");
                    String contactTitle = request.getParameter("ContactTitle");
                    String address = request.getParameter("Address");
                    String city = request.getParameter("City");
                    String region = request.getParameter("Region");
                    String postalCode = request.getParameter("PostalCode");
                    String country = request.getParameter("Country");
                    String phone = request.getParameter("Phone");
                    String fax = request.getParameter("Fax");
                    String pass = request.getParameter("Password");
                    //check data - validate
                    if (companyName.equals("")) {
                        out.print("CompanyName empty");
                    }

                    Customers customer = new Customers(CustomerID, companyName, contactName,
                            contactTitle, address, city, region, postalCode, country,
                            phone, fax, pass);

                    int n = dao.addCustomers(customer);
                    response.sendRedirect("CustomersURL");
                }
            }
            if (service.equals("Register")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    
                    dispath(request, response, "/JSP/Register.jsp");
                } else {
                    //if (submit != null) {
                    String CustomerID = request.getParameter("CustomerID");
                    String companyName = request.getParameter("CompanyName");
//                    String contactName = request.getParameter("ContactName");
//                    String contactTitle = request.getParameter("ContactTitle");
//                    String address = request.getParameter("Address");
//                    String city = request.getParameter("City");
//                    String region = request.getParameter("Region");
//                    String postalCode = request.getParameter("PostalCode");
//                    String country = request.getParameter("Country");
//                    String phone = request.getParameter("Phone");
//                    String fax = request.getParameter("Fax");
                    //check data - validate
                    if (companyName.equals("")) {
                        out.print("CompanyName empty");
                    }

                    Customers customer = new Customers(CustomerID, companyName);

                    int n = dao.addCustomers(customer);
                    dispath(request, response, "/JSP/Index.jsp");
                }
            }

        }
    }
    protected void dispath(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws ServletException, IOException {
//         RequestDispatcher dis
//                        = request.getRequestDispatcher(page);
//         dis.forward(request, response);
        request.getRequestDispatcher(page).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

