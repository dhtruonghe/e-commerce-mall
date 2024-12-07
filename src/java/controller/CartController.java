/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Customers;
import entity.OrderDetails;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import model.DAOCart;
import model.DAOOrder;
import model.DAOOrderDetails;

@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCart dao = new DAOCart();
        DAOOrder daoOrder = new DAOOrder();
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("add2cart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart newCart = dao.getCart(pid);
                //check pid
                if (session.getAttribute(pid + "") == null) { //firstTime
                    newCart.setQuantity(1);
                    session.setAttribute(pid + "", newCart);
                } else { //second...
                    Cart oldCart = (Cart) session.getAttribute(pid + "");
                    oldCart.setQuantity(oldCart.getQuantity() + 1);
                    session.setAttribute(pid + "", oldCart);
                }
                response.sendRedirect("ProductURL");
            }
            if (service.equals("showCart")) {
                Vector<Cart> vector = new Vector<>();
                //lay cot key
                Enumeration<String> enu = session.getAttributeNames();
                while (enu.hasMoreElements()) {
                    String pid = enu.nextElement(); // pid ~ key
                    Cart cart = (Cart) session.getAttribute(pid + "");
                    vector.add(cart);

                }
                session.setAttribute("vectorCart", vector);
                request.getRequestDispatcher("/JSP/showCart.jsp").forward(request, response);
            }
            if (service.equals("deleteCart")) {
                Vector<Cart> vector = (Vector<Cart>) request.getAttribute("vectorCart");
                int pid = (int) request.getAttribute("pid");
                for (int i = 0; i < vector.size(); i++) {
                    if (vector.get(i).getProductID() == pid) {
                        vector.remove(i);
                        break; // Thoát vòng lặp sau khi tìm thấy và xóa
                    }
                }
            }
            if (service.equals("removeCart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                request.removeAttribute(pid + "");
                response.sendRedirect("CartURL?service=showCart");
            }

            if (service.equals("cart")) {
//                Vector<Cart> vector = new Vector<>();
////                lay cot key
//                Enumeration<String> enu = session.getAttributeNames();
//                while (enu.hasMoreElements()) {
//                    String pid = enu.nextElement(); // pid ~ key
//                    Cart cart = (Cart) session.getAttribute(pid + "");
//                    vector.add(cart);
//
//                }
//                session.setAttribute("vectorCart", vector);
                request.getRequestDispatcher("/JSP/Cart.jsp").forward(request, response);

            }
            if (service.equals("addCart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart newCart = dao.getCart(pid);
                Vector<Cart> cartList = (Vector<Cart>) session.getAttribute("vectorCart");
                if (cartList == null) {
                    cartList = new Vector<>();
                }
                if (cartList.contains(newCart)) {
                    int index = cartList.indexOf(newCart);
                    newCart = cartList.get(index);
                    newCart.setQuantity(newCart.getQuantity() + 1);
                } else {
                    newCart.setQuantity(1);
                    cartList.add(newCart);
                }
                session.setAttribute("vectorCart", cartList);
                int cid = Integer.parseInt(request.getParameter("cid"));
                response.sendRedirect("ProductURL?service=searchCateID&cid=" + cid);
            }
            if (service.equals("removeAll")) {
                session.invalidate(); // clear entire cart
                response.sendRedirect("CartURL?service=showCart");
            }
            if (service.equals("remove")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Vector<Cart> cartList = (Vector<Cart>) session.getAttribute("vectorCart");

                if (cartList != null) {
                    for (int i = 0; i < cartList.size(); i++) {
                        Cart cart = cartList.get(i);
                        if (cart.getProductID() == pid) {
                            if (cart.getQuantity() > 1) {
                                cart.setQuantity(cart.getQuantity() - 1);
                            } else {
                                cartList.remove(i);
                            }
                            break;
                        }
                    }
                    session.setAttribute("vectorCart", cartList);
                }
                response.sendRedirect("CartURL?service=cart");
            }
            if (service.equals("bill")) {
                List<OrderDetails> ods = new ArrayList<>();
                DAOOrder order = new DAOOrder();
                DAOOrderDetails orderDetails = new DAOOrderDetails();
                Vector<Cart> cartList = (Vector<Cart>) session.getAttribute("vectorCart");
                OrderDetails od;

                Customers cus = (Customers) session.getAttribute("customer");
                Orders o = new Orders(cus.getCustomerID(), 1,
                        request.getParameter("orderDate"),
                        request.getParameter("requiredDate"),
                        request.getParameter("shippedDate"),
                        1, 0, cus.getCompanyName(),
                        cus.getAddress(),
                        cus.getCity(), cus.getRegion(),
                        cus.getPostalCode(), cus.getCountry());
                int orderID = daoOrder.addOrder(o);
                for (Cart cart : cartList) {
                    od = new OrderDetails(orderID, cart.getProductID(),
                            cart.getUnitPrice(), cart.getQuantity(),
                            cart.getDiscount());

                    orderDetails.insertOD(od);
                    ods.add(od);
                }
                order.insertOrder(o);
                request.setAttribute("o", o);
                request.setAttribute("ods", ods);
                request.getRequestDispatcher("JSP/bill.jsp").forward(request, response);
            }
            if (service.equals("checkOut")) {
                String submit = request.getParameter("checkOut");
                if (submit != null) {
                    response.sendRedirect("CartURL?service=bill");
                } else {
                    request.getRequestDispatcher("JSP/checkOut.jsp").forward(request, response);
                }
            }
            
        }
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
