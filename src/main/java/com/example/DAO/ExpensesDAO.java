package com.example.DAO;

import java.util.List;

import com.example.Model.Expenses;

public interface ExpensesDAO {

	void addExpenses(Expenses expenses);

	void updateExpense(Expenses expenses);

	void deleteExpensesById(int id);

	Expenses getExpensesById(int id);

	List<Expenses> getAllExpenses();

	List<Expenses> getAllExpensesByDate(String date);

	List<Expenses> getAllExpensesByCategory(String category);

	double getTotalExpenses();

	double getTotalExpensesByDate(String date);

}
