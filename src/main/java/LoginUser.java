
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecobee.DBOperations;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Create an instance of DBOperations and call connectDB()
			DBOperations dbOps = new DBOperations();
			conn = dbOps.connectDB();

			// SQL query to insert user data
			String sql = "select * from users where username = ? and password = ? limit 1";
			pstmt = conn.prepareStatement(sql);

			// Set parameters
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Cookie c = new Cookie("username", username);
				c.setMaxAge(86400);
				response.addCookie(c);
				response.sendRedirect("home.html");
			}else {
				response.getWriter().print("login failed");
			}

			// Execute the query
//			
//
//			if (rowsAffected > 0) {
//				// Registration successful - forward to success JSP
//				request.setAttribute("username", username);
//				request.setAttribute("email", email);
//				request.getRequestDispatcher("registrationSuccess.jsp").forward(request, response);
//			} else {
//				// Registration failed - forward to failure JSP
//				request.setAttribute("error", "Database error: Failed to insert record");
//				request.getRequestDispatcher("registrationFailed.jsp").forward(request, response);
//			}
		} catch (SQLException e) {
			// Registration failed with exception - forward to failure JSP
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("registrationFailed.jsp").forward(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
