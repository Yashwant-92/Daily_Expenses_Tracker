package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Expenses;
import com.example.Utility.ExpensesUtility;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class ExpensesDAOimpl implements ExpensesDAO {

	public void addExpenses(Expenses expenses) {
		String query = "INSERT into expenses (id, amount,category,date,description) values(?,?,?,?,?)";
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setInt(1, expenses.getId());
			stmt.setDouble(2, expenses.getAmount());
			stmt.setString(3, expenses.getCategory());
			stmt.setString(4, expenses.getDate());
			stmt.setString(5, expenses.getDescription());
			stmt.executeUpdate();
			System.out.println("\nExpenses Added Succesfully....");
			stmt.close();
		} catch (SQLException e) {
			System.out.println("\nError -> Expenses Not Added......");
			e.printStackTrace();
		}

	}

	public void updateExpense(Expenses expenses) {
		String query = "UPDATE expenses set category = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setString(1, expenses.getCategory());
			stmt.setInt(2, expenses.getId());
			stmt.executeUpdate();
			System.out.println("\nExpenses Updated Succesfully.....");
		} catch (SQLException e) {
			System.out.println("\nSorry expenses cann't updated...");
			e.printStackTrace();
		}

	}

	public void deleteExpensesById(int id) {
		String query = "DELETE from expenses where id = ?";
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("\nExpenses deleted Succesfully...");
		} catch (SQLException e) {
			System.out.println("\nSorry expenses can't delete....");
			e.printStackTrace();
		}

	}

	public Expenses getExpensesById(int id) {
		String query = "SELECT * from expenses where id = ?";
		Expenses expenses = null; // new method object is created.
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("\nExpenses Found:-> ");
				int id1 = rs.getInt("Id");
				double amount = rs.getDouble("amount");
				String category = rs.getString("category");
				String date = rs.getString("date");
				String description = rs.getString("description");
				expenses = new Expenses(id1, amount, category, date, description);
			}

		} catch (SQLException e) {
			System.out.println("Error occured Cannot show the expenses please try again....");
			e.printStackTrace();
		}

		return expenses;
	}

	public List<Expenses> getAllExpenses() {
		List<Expenses> expenses = new ArrayList<Expenses>();
		String query = "SELECT * from expenses";
		try {
			Statement stmt = ExpensesUtility.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				String category = rs.getString("category");
				String date = rs.getString("date");
				String description = rs.getString("description");

				expenses.add(new Expenses(id, amount, category, date, description));

			}
		} catch (SQLException e) {
			System.out.println("Error occured Cannot show the expenses please try again....");
			e.printStackTrace();
		}

		return expenses;
	}

	public List<Expenses> getAllExpensesByDate(String date) {

		List<Expenses> expenses = new ArrayList<Expenses>();
		String query = "SELECT * from expenses where date = ?";
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				String category = rs.getString("category");
				String date1 = rs.getString("date");
				String description = rs.getString("description");

				expenses.add(new Expenses(id, amount, category, date1, description));

			}
		} catch (SQLException e) {
			System.out.println("Error occured Cannot show the expenses please try again....");
			e.printStackTrace();
		}

		return expenses;
	}

	public List<Expenses> getAllExpensesByCategory(String category) {
		List<Expenses> expenses = new ArrayList<Expenses>();
		String query = "SELECT * from expenses where category = ?";
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				String category1 = rs.getString("category");
				String date1 = rs.getString("date");
				String description = rs.getString("description");
				expenses.add(new Expenses(id, amount, category1, date1, description));
			}

		} catch (SQLException e) {
			System.out.println("Error occured Cannot show the expenses please try again....");
			e.printStackTrace();
		}
		return expenses;
	}

	public double getTotalExpenses() {
		String query = "SELECT SUM(amount) AS total FROM expenses";
		double total = 0.0;
		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				total = rs.getDouble("total"); // already above declared

			}
		} catch (SQLException e) {
			System.out.println("Error Expenses Total can't calculate..");
			e.printStackTrace();
		}

		return total;
	}

	public double getTotalExpensesByDate(String date) {
		String query = "SELECT SUM(amount) AS total FROM expenses WHERE date = ?";
		double total = 0.0;

		try {
			PreparedStatement stmt = ExpensesUtility.getConnection().prepareStatement(query);
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				total = rs.getDouble("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

}
