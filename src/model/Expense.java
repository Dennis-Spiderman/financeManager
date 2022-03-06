package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
	private double amount;
	private String description;
	private Date date;
	private String dateStr;
	
	public Expense(double amount, String description, Date date) {
		this.amount = amount;
		this.description = description;
		this.date = date;
		setDateStr();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr() {
		dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public void addValues(Expense expenseToAdd) {
		amount += expenseToAdd.getAmount();
		description += "\n" + expenseToAdd.getDescription();
	}
}
