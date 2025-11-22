package com.sunbeam;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	    public static final String DB_URL = "jdbc:mysql://localhost:3306/Test";
	    public static final String DB_USER = "root";
	    public static final String DB_PASSWORD = "root";

	    static {   
	    try {
	            Class.forName(DB_DRIVER);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	    }

	    public static Connection getConnection() throws Exception {
	        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    }
	}

