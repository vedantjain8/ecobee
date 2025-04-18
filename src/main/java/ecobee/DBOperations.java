package ecobee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOperations {
	public Connection connectDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/collegeGrpProject", "root", "root");
	}

}
