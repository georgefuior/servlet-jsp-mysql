package ro.georgefuior.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.georgefuior.dao.EmployeeDAO;
import ro.georgefuior.dao.EmployeeDAOImpl;
import ro.georgefuior.entity.Employee;
import ro.georgefuior.util.DBConnectionUtil;


public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Create a ref variable for employee DAO
	EmployeeDAO employeeDAO  = null;
	RequestDispatcher dispatcher = null;

	//Create a constructor to initialize the employee DAO

	public EmployeeController() {

		employeeDAO = new EmployeeDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action ==null) {
			action = "LIST";
		}
		switch(action) {
		case "LIST":
			listEmployees(request,response);
			break;
		case "EDIT":
			getSingleEmployee(request,response);
			break;
		case "DELETE":
			deleteEmployee(request,response);
			break;
		default:
			listEmployees(request,response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");	
		String name = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		String department = request.getParameter("department");

		//System.out.println("Name: "+name+"\n Dob: "+dob+"\n Department: "+department+"\n Id: "+id);

		Employee e = new Employee();
		e.setName(name);
		e.setDob(dob);
		e.setDepartment(department);

		if(id.isEmpty() || id == null ) {
			//save operation
			if(employeeDAO.save(e)) {
				request.setAttribute("message","Saved sucesfully");		
			}

		}else {
			//update operation 
			e.setId(Integer.parseInt(id));
			if(employeeDAO.update(e)) {
				request.setAttribute("message","Update sucesfully");
			}
		}

		listEmployees(request, response);
	}

	public void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Call dao method to get list of employees
		List<Employee> list = employeeDAO.get();


		//Add the employees to request object
		request.setAttribute("list", list);

		//Get the request dispatcher

		dispatcher = request.getRequestDispatcher("/Views/employee-list.jsp");

		//forward the request and response objects
		dispatcher.forward(request, response);
	}

	public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Employee employee = employeeDAO.get(Integer.parseInt(id));
		request.setAttribute("employee", employee);

		dispatcher = request.getRequestDispatcher("/Views/employee-add.jsp");

		//forward the request and response objects
		dispatcher.forward(request, response);
	}
	
	//Method to delete an Employee from database
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(employeeDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("message", "Record has been deleted");		
		}
		listEmployees(request,response);
	}

}
