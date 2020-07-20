package login.shankar.registration;

public interface CustomerDAO {

	public int insertCustomer(Customer customer);
	public Customer getCustomer(String userName, String password);
}
