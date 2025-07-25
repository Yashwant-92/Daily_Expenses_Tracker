package com.example.Main;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.ExpensesDAOimpl;
import com.example.Model.Expenses;
import com.example.Utility.ExpensesUtility;

public class ExpensesMain {

	public static void main(String[] args) throws SQLException {

		ExpensesDAOimpl expenseDAO = new ExpensesDAOimpl();

		// Create DB and table if not exists
		String query1 = "CREATE DATABASE IF NOT EXISTS ExpenseDB";
		String query2 = "USE ExpenseDB";
		String query3 = "CREATE TABLE IF NOT EXISTS expenses ("
				+ "id INT PRIMARY KEY, "
				+ "amount DOUBLE, "
				+ "category VARCHAR(100), "
				+ "date VARCHAR(50), "
				+ "description VARCHAR(255))";

		Statement stmt = ExpensesUtility.getConnection().createStatement();
		stmt.execute(query1);
		System.out.println("✅ Database created (or already exists)");

		stmt.execute(query2);
		System.out.println("📁 Using database: ExpenseDB");

		stmt.execute(query3);
		System.out.println("✅ Table created (or already exists)");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n========= DAILY EXPENSE MANAGER =========");
			System.out.println("1. Add Expense");
			System.out.println("2. View All Expenses");
			System.out.println("3. Search Expense by ID");
			System.out.println("4. View Expenses by Date");
			System.out.println("5. View Expenses by Category");
			System.out.println("6. Update Expense Category");
			System.out.println("7. Delete Expense");
			System.out.println("8. Total Expense");
			System.out.println("9. Total Expense by Date");
			System.out.println("10. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();
			sc.nextLine(); // clear buffer

			switch (choice) {
			case 1:
				System.out.print("Enter ID: ");
				int id = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter Amount: ");
				double amount = sc.nextDouble();
				sc.nextLine();

				System.out.print("Enter Category: ");
				String category = sc.nextLine();

				System.out.print("Enter Date (yyyy-mm-dd): ");
				String date = sc.nextLine();

				System.out.print("Enter Description: ");
				String description = sc.nextLine();

				Expenses newExp = new Expenses(id, amount, category, date, description);
				expenseDAO.addExpenses(newExp);
				break;

			case 2:
				List<Expenses> allExpenses = expenseDAO.getAllExpenses();
				if (allExpenses.isEmpty()) {
					System.out.println("⚠️ No expenses found.");
				} else {
					for (Expenses e : allExpenses) {
						System.out.println(e);
					}
				}
				break;

			case 3:
				System.out.print("Enter Expense ID: ");
				int eid = sc.nextInt();
				Expenses e = expenseDAO.getExpensesById(eid);
				if (e != null) {
					System.out.println(e);
				} else {
					System.out.println("❌ Expense not found.");
				}
				break;

			case 4:
				System.out.print("Enter Date: ");
				String searchDate = sc.nextLine();
				List<Expenses> expensesByDate = expenseDAO.getAllExpensesByDate(searchDate);
				for (Expenses ed : expensesByDate) {
					System.out.println(ed);
				}
				break;

			case 5:
				System.out.println("Enter Expense Category from the list below:");
				System.out.println("\n========= EXPENSE CATEGORIES =========");
				System.out.println("🥗 Food           : Groceries, restaurants, snacks, coffee");
				System.out.println("🚗 Transportation : Bus fare, petrol, Uber, train ticket");
				System.out.println("🏠 Rent           : Apartment rent, PG, hostel");
				System.out.println("💡 Utilities      : Electricity, water, internet, gas");
				System.out.println("🛍️ Shopping       : Clothes, gadgets, gifts");
				System.out.println("🏥 Health         : Medicines, doctor visits, health insurance");
				System.out.println("📚 Education      : Tuition, books, online courses");
				System.out.println("🎮 Entertainment  : Netflix, cinema, games, subscriptions");
				System.out.println("🏦 Miscellaneous  : Anything that doesn’t fit other categories");
				System.out.println("=======================================");
				System.out.print("\nEnter Category: ");
				String cat = sc.nextLine();
				List<Expenses> expByCat = expenseDAO.getAllExpensesByCategory(cat);
				for (Expenses ec : expByCat) {
					System.out.println(ec);
				}
				break;

			case 6:
				System.out.print("Enter Expense ID to update: ");
				int uid = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter New Category: ");
				String newCat = sc.nextLine();
				Expenses updatedExp = new Expenses();
				updatedExp.setId(uid);
				updatedExp.setCategory(newCat);
				expenseDAO.updateExpense(updatedExp);
				break;

			case 7:
				System.out.print("Enter Expense ID to delete: ");
				int did = sc.nextInt();
				expenseDAO.deleteExpensesById(did);
				break;

			case 8:
				double total = expenseDAO.getTotalExpenses();
				System.out.println("💰 Total Expenses: ₹" + total);
				break;

			case 9:
				System.out.print("Enter Date to get total: ");
				String d = sc.nextLine();
				double dayTotal = expenseDAO.getTotalExpensesByDate(d);
				System.out.println("📅 Total Expenses on " + d + ": ₹" + dayTotal);
				break;

			case 10:
				System.out.println("👋 Exiting Expense Manager. Goodbye!");
				System.exit(0);
				break;

			default:
				System.out.println("❌ Invalid choice. Try again.");
			}
		}
	}
}
