package com.hr.google.chart;

import com.hr.cmn.DTO;

public class PieVO extends DTO {

	private String label;
	private int data;
	
	public PieVO(){}
	
	public PieVO(String label, int data) {
		super();
		this.label = label;
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PieVO [label=" + label + ", data=" + data + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
