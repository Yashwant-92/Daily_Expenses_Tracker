package com.example.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExpensesUtility {
	static Connection connection;

	public static String url = "jdbc:mysql://localhost:3306/ExpenseDB";
	public static String username = "root";
	public static String password = "root";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Someting wrong Connection is not established..");
			e.printStackTrace();
		}
		return connection;
	}

}
