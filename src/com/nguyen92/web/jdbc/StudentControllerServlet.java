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
		// BUG: When hitting refresh with student add data in URL,
		// student will be continuously added 
		try {
		// Step 5: read the "action" parameter
			String theAction = request.getParameter("action");
			
		// if the action is missing, default to listing the students
			if (theAction == null) {
				theAction = "LIST";
			}
			
		// Based on action, route to the appropriate method
		// Step 6: make case for addStudent, method below
			switch (theAction) {
			case "LIST":
				listStudents(request, response);
				break;
			}
		} catch (Exception e){
			throw new ServletException(e);
		}
	}


	// Step 6:  Write method for doPost
	// Implement the Post/Redirect/Get design pattern. 
	// This pattern is designed to prevent duplicate form submissions from BUG above
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		// read the "action" parameter
			String theAction = request.getParameter("action");
			
		// route to appropriate method
			switch(theAction){
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "EDIT":
				editStudent(request,response);
				break;
			default:
				listStudents(request,response);
			}
		}
		catch(Exception e) {
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

	
	// Step 6a:  Create method to add students
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new student object
		Student newStudent = new Student(firstName, lastName, email);
		
		// add the student to the database
		studentDbUtil.addStudent(newStudent);
		
		// send back to student list
		// send as Redirect to avoid multiple-browser reload issue
		 response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?action=LIST");
		 //response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
    }
	
	
	// Step 7:  Create method to load student data
	// Load student to pre-populate form when editing student
	private void loadStudent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// read student ID from form data
		String theStudentId = request.getParameter("studentId");
		
		// get student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		// place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		// forward to jsp page: updateStudentForm.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editStudentForm.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// Step 8:  Create method to editStudent
	// retrieve student data from list and create a new student object to add into db
	private void editStudent(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);
		
		// perform update on database
		studentDbUtil.editStudent(theStudent);
		
		// send them back to the listStudents page
		listStudents(request,response);
		
	}
	
}
 






















