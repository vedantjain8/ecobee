package com.ecommerce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Product;

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DB_URL = "jdbc:mysql://localhost:3306/ecobee";
	private final String DB_USER = "ecobee";
	private final String DB_PASSWORD = "1111";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchedProduct = request.getParameter("SearchProduct").trim();
		List<Product> productList = new ArrayList<>();
		String fromPage = request.getParameter("from");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// String query = "SELECT * FROM product WHERE name = ?";
			String query = "SELECT * FROM product WHERE name LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(query);

			// stmt.setString(1, searchedProduct);
			stmt.setString(1, "%" + searchedProduct + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				p.setImage_url(rs.getString("image_url"));
				productList.add(p);
			}

			conn.close();
			request.setAttribute("productList", productList);
			if ("update".equals(fromPage)) {

				request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
			} else if ("delete".equals(fromPage)) {

				request.getRequestDispatcher("DeleteProduct.jsp").forward(request, response);
			}else if("homeSearch".equals(fromPage)) {
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);

			}

		} catch (Exception e) {
			System.out.println("u r here");
			e.printStackTrace();
		}

	}

}
