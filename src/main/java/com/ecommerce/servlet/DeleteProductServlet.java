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

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	private final String DB_URL = "jdbc:mysql://localhost:3306/ecobee";
	private final String DB_USER = "ecobee";
	private final String DB_PASSWORD = "1111";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Delete the product based on product id
            String query = "DELETE FROM product WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, productId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Successfully deleted
                request.setAttribute("message", "Product Deleted successfully!");

            } else {
                request.setAttribute("message", "Product not found or Delete failed.");
               
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("DeleteProduct.jsp").forward(request, response);
        	        }
        request.getRequestDispatcher("DeleteProduct.jsp").forward(request, response);

    }
}
