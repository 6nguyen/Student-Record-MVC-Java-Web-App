package com.nguyen92.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
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
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	
	}

}
