package com.ecommerce.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String DB_URL = "jdbc:mysql://localhost:3306/ecobee";
	private final String DB_USER = "ecobee";
	private final String DB_PASSWORD = "1111";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String priceStr = request.getParameter("productPrice");
        String quantityStr = request.getParameter("productQuantity");
        String imageUrl = request.getParameter("productImage");

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        boolean success = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO product (name, description, price, quantity, image_url) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, imageUrl);

            int rows = stmt.executeUpdate();
            success = (rows > 0);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("status", success ? "Product Added!" : "Failed to Add Product.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddProduct.jsp");
        dispatcher.forward(request, response);
    }
}
