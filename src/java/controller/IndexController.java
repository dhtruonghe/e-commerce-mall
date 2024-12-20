/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import entity.Customers;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOProduct;

/**
 *
 * @author haitr
 */
@WebServlet(name = "IndexController", urlPatterns = {"/IndexURL"})
public class IndexController extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        DAOProduct product = new DAOProduct();
        DAOCustomers customer = new DAOCustomers();

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            DAOCategories daoCate = new DAOCategories();

//            ResultSet rsCate = (ResultSet) dao.getCategorieses("select * from Categories");
//            request.setAttribute("rsCate", rsCate);
            Vector<Categories> vectorCate = (Vector<Categories>) 
                    daoCate.getCategorieses("select * from Categories");
            request.setAttribute("vectorCate", vectorCate);
//            request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);
//            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Index.jsp");
//            dispath.forward(request, response);
            if(service == null){
                request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);
            }
            if (service.equals("login")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
                } else {
                    Customers cus = customer.login(request.getParameter("username"),
                            request.getParameter("password"));
                    if (cus == null) {
                        request.setAttribute("error", "Login fail");
                        request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
                    } else {
                        session.setAttribute("customer", cus);
                        request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("logout")) {
                session.invalidate();
                request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);
            }
            if (service.equals("getProduct")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                Vector<Product> vectorProducts = (Vector<Product>) product.getProducts("select * from Products where CategoryID = "+cid);
                request.setAttribute("vectorProducts", vectorProducts);
                request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);
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
