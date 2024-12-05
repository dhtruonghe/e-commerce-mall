/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Suppliers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOSuppliers;

/**
 *
 * @author haitr
 */
@WebServlet(name = "SuppliersController", urlPatterns = {"/SuppliersURL"})
public class SuppliersController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSuppliers dao = new DAOSuppliers();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteSuppliers")) {
                dao.deleteSupplier(Integer.parseInt(request.getParameter("sid")));
                response.sendRedirect("SuppliersURL?service=listAll");
            }
            if (service.equals("insertSuppliers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    dispath(request, response, "/JSP/insertSuppliers.jsp");
                } else {
                    //if (submit != null) {
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String fax = request.getParameter("fax");
                    String phone = request.getParameter("phone");
                    String homePage = request.getParameter("homePage");
                    //check data - validate
                    if (companyName.equals("")) {
                        out.print("CompanyName empty");
                    }

                    Suppliers supplier = new Suppliers(0, companyName, contactName,
                            contactTitle, address, city, region, postalCode, country,
                            fax, phone, homePage);

                    int n = dao.addSupplier(supplier);
                    
                    response.sendRedirect("SuppliersURL");
                }

            }
            
            if (service.equals("listAll")) {//request, service
                //call model
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {  //khong phai search, hien toan bo
                    sql = "select * from Suppliers";
                } else { //search
                    String companyName = request.getParameter("cname");
                    sql = "select * from Suppliers where CompanyName like '%" + companyName + "%'";
                }
                Vector<Suppliers> vector = dao.getSupplierses(sql);
                //select view: jsp
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listSuppliers.jsp");
                //set data for view
                request.setAttribute("dataSuppliers", vector);
                request.setAttribute("titleTable", "Suppliers Manage");
                //run
                dispath.forward(request, response);

            }
            if (service.equals("updateSuppliers")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    int sid = Integer.parseInt(request.getParameter("sid"));
                    Vector<Suppliers> vector = dao.getSupplierses("SELECT * FROM Suppliers where SupplierID=" + sid);

                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateSuppliers.jsp");
                } else {
                    //if (submit != null) {
                    int SupplierID = Integer.parseInt(request.getParameter("SupplierID"));
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String fax = request.getParameter("fax");
                    String phone = request.getParameter("phone");
                    String homePage = request.getParameter("homePage");
                    //check data - validate
                    if (companyName.equals("")) {
                        out.print("CompanyName empty");
                    }
                    

                    Suppliers supplier = new Suppliers(SupplierID, companyName, contactName,
                            contactTitle, address, city, region, postalCode, country,
                            fax, phone, homePage);

                    int n = dao.updateSupplier(supplier);
                    response.sendRedirect("SuppliersURL");
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
