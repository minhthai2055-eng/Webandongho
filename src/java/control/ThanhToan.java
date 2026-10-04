/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.dao1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sanpham.User;
import sanpham.gioHang;

/**
 *
 * @author ADMIN
 */
public class ThanhToan extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            response.sendRedirect("TrangChu");
            return;
        }
         dao1 dao = new dao1();
        String pid = request.getParameter("id");
        if (pid != null && !pid.trim().isEmpty()) {
            try {
                int productId = Integer.parseInt(pid);
                dao.addToCart(user.getId(), productId);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        gioHang cart = dao.getCartByUserId(user.getId());
        request.setAttribute("cart", cart);
        request.setAttribute("user", user);
        request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");

        if (user == null) {
            response.sendRedirect("TrangChu");
            return;
        }

        dao1 dao = new dao1();
        gioHang cart = dao.getCartByUserId(user.getId());

        // LẤY DỮ LIỆU FORM
        String receiverName = request.getParameter("receiverName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String note = request.getParameter("note");

        // VALIDATE
        if (receiverName == null || receiverName.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || address == null || address.trim().isEmpty()
                || city == null || city.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.setAttribute("cart", cart);
            request.setAttribute("user", user);

            request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
            return;
        }

        // CHECKOUT
        dao.checkout(user.getId(), receiverName, phone, address, city, note);
        request.setAttribute("success", "Đặt hàng thành công");
        request.setAttribute("cart", cart);
        request.setAttribute("user", user);

        request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
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
