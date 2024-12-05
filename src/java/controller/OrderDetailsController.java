/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOOrderDetails;

/**
 *
 * @author haitr
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/OrderDetailsURL"})
public class OrderDetailsController extends HttpServlet {

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
        DAOOrderDetails dao = new DAOOrderDetails();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteOrderDetails")) {
                dao.deleteOD(Integer.parseInt(request.getParameter("cid")));
                response.sendRedirect("OrderDetailsURL?service=listAll");
            }
            if (service.equals("insertOrderDetails")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsOrder = dao.getData("SELECT OrderID,CustomerID  FROM Orders");
                    ResultSet rsPro = dao.getData("SELECT ProductID,ProductName FROM Products");
                    request.setAttribute("rsOrder", rsOrder);
                    request.setAttribute("rsPro", rsPro);
                    dispath(request, response, "/JSP/insertOrderDetails.jsp");
                } else {
                    int ProductID = Integer.parseInt(request.getParameter("ProductID"));
                    Double UnitPrice = Double.parseDouble(request.getParameter("UnitPrice"));
                    int Quantity = Integer.parseInt(request.getParameter("Quantity"));
                    Double Discount = Double.parseDouble(request.getParameter("Discount"));

                    OrderDetails od = new OrderDetails(0, ProductID,
                            UnitPrice, Quantity, Discount);
                    int n = dao.insertOD(od);
                    response.sendRedirect("OrderDetailsURL");
                }
            }
            if (service.equals("listAll")) {

                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select * from [Order Details]";
                } else {
                    String oid = request.getParameter("oid");
                    sql = "SELECT * from [Order Details] where OrderID = " + oid;
                }
                Vector<OrderDetails> vector = dao.getDetails(sql);
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listOrderDetails.jsp");

                request.setAttribute("dataOrderDetails", vector);
                request.setAttribute("titleTable", "OrderDetails manage");

                dispath.forward(request, response);
            }
            if (service.equals("updateOrderDetails")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int oid = Integer.parseInt(request.getParameter("oid"));
                    Vector<OrderDetails> vector = dao.getDetails("SELECT * FROM [Order Details] where OrderID=" + oid);

                    ResultSet rsPro = dao.getData("SELECT ProductID,ProductName FROM Products");
                    ResultSet rsOrder = dao.getData("SELECT CategoryID,CategoryName FROM Categories");
                    request.setAttribute("rsPro", rsPro);
                    request.setAttribute("rsOrder", rsOrder);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateOrderDetails.jsp");
                } else {
                    int OrderID = Integer.parseInt(request.getParameter("OrderID"));
                    int ProductID = Integer.parseInt(request.getParameter("ProductID"));
                    Double UnitPrice = Double.parseDouble(request.getParameter("UnitPrice"));
                    int Quantity = Integer.parseInt(request.getParameter("Quantity"));
                    Double Discount = Double.parseDouble(request.getParameter("Discount"));

                    OrderDetails od = new OrderDetails(OrderID, ProductID,
                            UnitPrice, Quantity, Discount);
                    int n = dao.updateOD(od);
                    response.sendRedirect("OrderDetailsURL");

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
