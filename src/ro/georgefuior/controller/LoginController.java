package ro.georgefuior.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.georgefuior.dao.LoginDAO;
import ro.georgefuior.dao.LoginDAOImpl;
import ro.georgefuior.entity.Login;

public class LoginController extends HttpServlet {

	LoginDAO loginDAO = null;

	public LoginController() {
		loginDAO = new LoginDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Login login = new Login();
		login.setEmail(req.getParameter("email"));
		login.setPassword(req.getParameter("password"));

		String status = loginDAO.authenticate(login);

		if(status =="true") {
			session.setAttribute("email", login.getEmail());
			resp.sendRedirect("EmployeeController?action=LIST");
		}else if (status =="false") {
			resp.sendRedirect("index.jsp?status=false");
		}else {
			resp.sendRedirect("index.jsp?status=error");
		}
	}



}
