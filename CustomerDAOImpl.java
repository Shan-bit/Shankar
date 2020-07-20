package login.shankar.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

	static Connection connection;
	static PreparedStatement preparedStatement;

	@Override
	public int insertCustomer(Customer customer) {
		int status=0;
		try {

			connection = MyConnectionProvider.getconnection();
			preparedStatement = connection.prepareStatement("insert into test.customer values(?, ?, ?)");
			preparedStatement.setString(1, customer.getUserName());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getPassword());
			status = preparedStatement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		}
		return status;
	}
	@Override
	public Customer getCustomer(String userid, String password) {
		Customer customer = new Customer();
		try {
			connection = MyConnectionProvider.getconnection();
			preparedStatement = connection.prepareStatement("select * from test.customer userid=? and password=?");
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				customer.setUserName(resultSet.getString(1));
				customer.setPassword(resultSet.getString(2));
				customer.setName(resultSet.getString(3));
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return customer;
	}
}