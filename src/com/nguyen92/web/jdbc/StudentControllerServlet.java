package com.nguyen92.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// Step 1: Set up reference to DB Util class
	private StudentDbUtil studentDbUtil;
		
	
	// Step 2:  Set up ref to Java EE Resource Injection
		// Define datasource/connection pool for Resource Injection
		// resource name found in META-INF>context.xml>Resource name
	@Resource(name="jdbc/webStudentTracker")
	private DataSource dataSource;
	
	
	// Step 3:  Override init() method (similar to a constructor in java class)
		// called by Java EE server (or Tomcat) when the Servlet is first initialized
	@Override
	public void init() throws ServletException {

		super.init();
		
		// create our Student DB Util and pass in the connection pool/datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}


	// Step 4:  Write method for doGet
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		// Step 4a: list the students in MVC fashion
		// exception caused by listStudents method below
		listStudents(request, response);
		} catch (Exception e){
			throw new ServletException(e);
		}

	
	}


	// Step 4a:  Create method to list students
	private void listStudents(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		// get students from DB util
		List<Student> students = studentDbUtil.getStudents();
		
		// add students to the request object as an attribute
		// setAttribute("name", object ref);  name used in listStudent jsp page
		request.setAttribute("studentList", students);
		
		// forward request to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listStudent.jsp");
		dispatcher.forward(request, response);
		
	}

}























