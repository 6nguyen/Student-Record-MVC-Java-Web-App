package com.nguyen92.web.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	// Step 1:  Set up reference to a DataSource
	private DataSource dataSource;
	
	// Step 2:  Set up constructor, used by Servlet to pass db as parameter	
	public StudentDbUtil(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
	// Step 3:  Write method to list the Student Objects
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
		return students;
	}
	
	
	
}
