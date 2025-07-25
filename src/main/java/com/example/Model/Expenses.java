package com.example.Model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "expenses")
@Entity
public class Expenses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Cascade(CascadeType.ALL)
	private int id;

	@Column(name = "amount")
	private double amount;
	@Column(name = "category")
	private String category;
	@Column(name = "date")
	private String date;
	@Column(name = "description")
	private String description;

	public Expenses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expenses(int id, double amount, String category, String date, String description) {
		super();
		this.id = id;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Expenses [id=" + id + ", amount=" + amount + ", category=" + category + ", date=" + date
				+ ", description=" + description + "]";
	}

}
