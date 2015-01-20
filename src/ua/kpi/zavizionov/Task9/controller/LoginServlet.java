package ua.kpi.zavizionov.Task9.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kpi.zavizionov.Task9.db.DBService;
import ua.kpi.zavizionov.Task9.db.entity.Role;
import ua.kpi.zavizionov.Task9.db.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = null;
		DBService service = DBService.getInstance();
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			forward = Path.LOGIN_ERROR;
		}else{
		User user = service.findUserByLogin(login);
		if (user == null || !password.equals(user.getPassword())){
			forward = Path.LOGIN_ERROR;
		}else{
			Role userRole = user.getRole();
			if(userRole.getName().equals("user")){
				forward = Path.USER_WELCOME_PAGE;
			}
			
			if(userRole.getName().equals("admin")){
				forward = Path.ADMIN_WELCOME_PAGE;
			}
			session.setAttribute("user", user);
			session.setAttribute("userRole", userRole);
			session.setAttribute("ip", request.getRemoteAddr());
			System.out.println(request.getRemoteAddr());
		}
		}
		if (forward.isEmpty() || forward == null){
			forward = Path.LOGIN_ERROR;
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}

}
