package com.ecommerce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DB_URL = "jdbc:mysql://localhost:3306/ecobee";
	private final String DB_USER = "ecobee";
	private final String DB_PASSWORD = "1111";

    public UpdateProduct() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String priceStr = request.getParameter("productPrice");
        String quantityStr = request.getParameter("productQuantity");
        String imageUrl = request.getParameter("productImageUrl");
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "UPDATE product SET description = ?, price = ?, quantity = ?, image_url = ? WHERE id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, description);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setString(4, imageUrl);
            stmt.setInt(5, productId);

            int rowsAffected = stmt.executeUpdate();
            conn.close();

            if (rowsAffected > 0) {
                request.setAttribute("message", "Product updated successfully!");
            } else {
                request.setAttribute("message", "Product not found or update failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
    }
}
