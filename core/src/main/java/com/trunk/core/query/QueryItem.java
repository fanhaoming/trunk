package com.trunk.core.query;

public class QueryItem {

	private String column;
	private Object value;
	private String type;
	
	public QueryItem(String column, Object value, String type){
		this.column = column;
		this.value = value;
		this.type = type;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
