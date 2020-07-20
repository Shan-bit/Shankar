package login.shankar.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/loginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginRegister() {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerDAO = new CustomerDAOImpl();
		String userName = request.getParameter("username");
		String password = request.getParameter("password1");
		String submitType = request.getParameter("submit");
		Customer customer = customerDAO.getCustomer(userName, password);
		//System.out.println(customer.getName()+customer.getPassword()+customer.getUserName());
		if (submitType.equals("login") && (customer == null)) {
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
		else if(submitType.equals("register")){
			customer.setName(request.getParameter("name"));
			customer.setPassword(password);
			customer.setUserName(userName);
			request.setAttribute("successMessage", "Registration done, please login to continue");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			request.setAttribute("message", "Data not found, click on Register");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}