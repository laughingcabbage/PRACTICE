package com.hr.google.chart;

import com.hr.cmn.DTO;

public class LineVO extends DTO {

	private String year;
	private int sales;
	private int expenses;
	
	public LineVO(){}

	public LineVO(String year, int sales, int expenses) {
		super();
		this.year = year;
		this.sales = sales;
		this.expenses = expenses;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getExpenses() {
		return expenses;
	}

	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "LineVO [year=" + year + ", sales=" + sales + ", expenses=" + expenses + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
