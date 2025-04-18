import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ecobee.DBOperations;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Create an instance of DBOperations and call connectDB()
            DBOperations dbOps = new DBOperations();
            conn = dbOps.connectDB();
            
            // SQL query to insert user data
            String sql = "INSERT INTO users (username, password, email, address) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);  // Note: In production, password should be hashed
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            
            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Registration successful - forward to success JSP
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("registrationSuccess.jsp").forward(request, response);
            } else {
                // Registration failed - forward to failure JSP
                request.setAttribute("error", "Database error: Failed to insert record");
                request.getRequestDispatcher("registrationFailed.jsp").forward(request, response);
            }
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
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
