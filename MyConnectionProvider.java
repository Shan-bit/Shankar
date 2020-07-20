package login.shankar.registration;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnectionProvider implements MyProvider {

	static Connection connection = null;
	public static Connection getconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return connection;
	}
}
