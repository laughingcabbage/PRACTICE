package com.hr.google.chart;

import com.hr.cmn.DTO;

public class YearMemberRatioVO extends DTO {
	
	private String year;
	private int totalCnt;
	private int female;
	private int male;
	
	public YearMemberRatioVO(){}

	public YearMemberRatioVO(String year, int totalCnt, int female, int male) {
		super();
		this.year = year;
		this.totalCnt = totalCnt;
		this.female = female;
		this.male = male;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	@Override
	public String toString() {
		return "YearMemberRatioVO [year=" + year + ", totalCnt=" + totalCnt + ", female=" + female + ", male=" + male
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
