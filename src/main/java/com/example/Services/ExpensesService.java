package com.example.Services;

import java.util.List;

import com.example.DAO.ExpensesDAO;
import com.example.Model.Expenses;

public class ExpensesService {

	ExpensesDAO expensesDAO;

	void addExpenses(Expenses expenses) {
		expensesDAO.addExpenses(expenses);
	}

	void updateExpense(Expenses expenses) {
		expensesDAO.updateExpense(expenses);
	}

	void deleteExpensesById(int id) {
		expensesDAO.deleteExpensesById(id);
	}

	Expenses getExpensesById(int id) {
		return expensesDAO.getExpensesById(id);
	}

	List<Expenses> getAllExpenses() {
		return expensesDAO.getAllExpenses();
	}

	List<Expenses> getAllExpensesByDate(String date) {
		return expensesDAO.getAllExpensesByDate(date);
	}

	List<Expenses> getAllExpensesByCategory(String category) {
		return expensesDAO.getAllExpensesByCategory(category);
	}

	double getTotalExpenses() {
		return expensesDAO.getTotalExpenses();
	}

	double getTotalExpensesByDate(String date) {
		return expensesDAO.getTotalExpensesByDate(date);
	}

}
