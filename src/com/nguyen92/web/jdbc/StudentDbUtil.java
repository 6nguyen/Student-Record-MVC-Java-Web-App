package com.nguyen92.web.jdbc;

import javax.sql.DataSource;

public class StudentDbUtil {

	// Step 1:  Set up reference to a DataSource
	private DataSource dataSource;
	
	// Step 2:  Set up constructor
	public StudentDbUtil(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
}
