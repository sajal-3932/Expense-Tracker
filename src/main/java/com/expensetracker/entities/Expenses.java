package com.expensetracker.entities;

import java.sql.Date;

public class Expenses {

	private String category ;
	private double amount ;
	private String comments ;
	private Date createdAt ;
	private Date UpdatedAt ;
	
	public Expenses() {	}

	public Expenses(String category, double amount, String comments, Date createdAt, Date updatedAt) {
		super();
		this.category = category;
		this.amount = amount;
		this.comments = comments;
		this.createdAt = createdAt;
		UpdatedAt = updatedAt;
	}

	public Expenses(String category, double amount, String comments) {
		super();
		this.category = category;
		this.amount = amount;
		this.comments = comments;
	}

	public Expenses(String category, double amount) {
		super();
		this.category = category;
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	
}
