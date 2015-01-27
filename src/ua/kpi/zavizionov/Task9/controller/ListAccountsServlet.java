package ua.kpi.zavizionov.Task9.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kpi.zavizionov.Task9.db.DBService;
import ua.kpi.zavizionov.Task9.db.entity.Account;
import ua.kpi.zavizionov.Task9.db.entity.Role;
import ua.kpi.zavizionov.Task9.db.entity.User;

/**
 * Servlet implementation class ListAccountsServlet
 */
@WebServlet("/listAccounts")
public class ListAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAccountsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = null;
		DBService service = DBService.getInstance();
		List<Account> accounts = service.getAllAccounts();
		request.setAttribute("accountList", accounts);
		forward = Path.ACCOUNT_LIST_PAGE;
		request.getRequestDispatcher(forward).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
