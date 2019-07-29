package com.trunk.core.query;

public class OrderByColumn {
	private String column;
	private Boolean isDesc;
	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public OrderByColumn(String column, Boolean isDesc) {
		this.column = column;
		this.isDesc = isDesc;
		this.priority = 0;
	}

	public OrderByColumn(String column, Boolean isDesc, int priority) {
		this.column = column;
		this.isDesc = isDesc;
		this.priority = priority;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Boolean getIsDesc() {
		return isDesc;
	}

	public void setIsDesc(Boolean isDesc) {
		this.isDesc = isDesc;
	}

}
