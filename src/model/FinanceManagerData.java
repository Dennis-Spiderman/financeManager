package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.InformationNotFilledException;
import exceptions.TypeNotSelectedException;

public class FinanceManagerData {
	private ArrayList<Expense> expenses;
	private ArrayList<Expense> filteredExpenses;
	private ArrayList<Income> incomes;
	private ArrayList<Income> filteredIncomes;
	private double balance;
	
	public FinanceManagerData() {
		expenses = new ArrayList<>();
		incomes = new ArrayList<>();
	}
	
	public void addExpenseOrIncome(double amount, String description, String dateStr, int type) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		
		if(description.isEmpty() || dateStr.isEmpty() || amount == 0) {
			throw new InformationNotFilledException();
		}else if(type == 0) {
			throw new TypeNotSelectedException();
		}else if(type == 1){
			addExpenseByDate(new Expense(amount, description, date));
		}else {
			addIncomeByDate(new Income(amount, description, date));
		}
	}
	
	public void addIncomeByDate(Income newIncome) {
		boolean found = false;
		
		for (int i = 0; i < incomes.size() && !found; i++) {
			if(incomes.get(i).getDate().equals(newIncome.getDate())) {
				incomes.get(i).addValues(newIncome);
				found = true;
			}
		}
		
		if(!found) {
			incomes.add(newIncome);
		}
	}
	
	public void addExpenseByDate(Expense newExpense) {
		boolean found = false;
		
		for (int i = 0; i < expenses.size() && !found; i++) {
			if(expenses.get(i).getDate().equals(newExpense.getDate())) {
				expenses.get(i).addValues(newExpense);
				found = true;
			}
		}
		
		if(!found) {
			expenses.add(newExpense);
		}
	}
	
	public void filterExpenses(Date initDate, Date finalDate) {
		filteredExpenses = new ArrayList<Expense>();
		
		for (int i = 0; i < expenses.size(); i++) {
			if(expenses.get(i).getDate().getTime() >= initDate.getTime() && expenses.get(i).getDate().getTime() <= finalDate.getTime()) {
				filteredExpenses.add(expenses.get(i));
			}else if(expenses.get(i).getDate().getTime() <= initDate.getTime() && expenses.get(i).getDate().getTime() >= finalDate.getTime()) {
				filteredExpenses.add(expenses.get(i));
			}
		}
	}
	
	public void filterIncomes(Date initDate, Date finalDate) {
		filteredIncomes = new ArrayList<Income>();
		
		for (int i = 0; i < incomes.size(); i++) {
			if(incomes.get(i).getDate().getTime() >= initDate.getTime() && incomes.get(i).getDate().getTime() <= finalDate.getTime()) {
				filteredIncomes.add(incomes.get(i));
			}else if(incomes.get(i).getDate().getTime() <= initDate.getTime() && incomes.get(i).getDate().getTime() >= finalDate.getTime()) {
				filteredIncomes.add(incomes.get(i));
			}
		}
	}
	
	public void calculateBalance() {
		double incomesSummation = 0;
		double expensesSummation = 0;
		
		for (int i = 0; i < incomes.size(); i++) {
			incomesSummation += incomes.get(i).getAmount();
		}
		
		for (int i = 0; i < expenses.size(); i++) {
			expensesSummation += expenses.get(i).getAmount();
		}
		
		balance = incomesSummation - expensesSummation;
	}
	
	public void calculateFilteredBalance(Date initDate, Date finalDate) {
		filterExpenses(initDate, finalDate);
		filterIncomes(initDate, finalDate);
		
		double incomesSummation = 0;
		double expensesSummation = 0;
		
		for (int i = 0; i < filteredIncomes.size(); i++) {
			incomesSummation += filteredIncomes.get(i).getAmount();
		}
		
		for (int i = 0; i < filteredExpenses.size(); i++) {
			expensesSummation += filteredExpenses.get(i).getAmount();
		}
		
		balance = incomesSummation - expensesSummation;
	}
	
	public ArrayList<Expense> getExpenses() {
		return expenses;
	}
	
	public ArrayList<Expense> getFilteredExpenses() {
		return filteredExpenses;
	}

	public ArrayList<Income> getIncomes() {
		return incomes;
	}
	
	public ArrayList<Income> getFilteredIncomes() {
		return filteredIncomes;
	}

	public double getBalance() {
		return balance;
	}
}
