package com.trunk.core.utils;

import org.apache.commons.lang3.StringUtils;
/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class PropertyNameConvert {

	/**
	 * 数据库字段转java类字段
	 * @param column 数据库字段名如create_time   ->  createTime
	 * @return
	 */
	public static String columnToProperty(String column) {
		StringBuilder sb = new StringBuilder();
		boolean nextNeedUpper = false;
		for (int i = 0; i < column.length(); i++) {
			char c = column.charAt(i);
			if (c == '_') {
				nextNeedUpper = true;
				continue;
			}

			if (nextNeedUpper == false)
				sb.append(String.valueOf(c));
			else {
				sb.append(String.valueOf(c).toUpperCase());
				nextNeedUpper = false;
			}
		}
		return sb.toString();
	}

	public static String classToProperty(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	public static String propertyToClass(String property) {
		return property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	/**
	 * 类字段的命名形式转换成数据库字段命名形式
	 * @param property
	 * @return
	 */
	public static String propertyToColumn(String property) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < property.length(); i++) {
			char c = property.charAt(i);
			if (Character.isUpperCase(c)) {
				if(i!=0)
					sb.append("_");
				sb.append(String.valueOf(c).toLowerCase());
			}else
				sb.append(String.valueOf(c));
		}
		return sb.toString();
	}
	
	
	public static String getForeignId(String tableName){
		if(StringUtils.isBlank(tableName)){
			return "";
		}
		int index = tableName.indexOf("_");
		if(index>0){
			String prev = tableName.substring(index+1);
			return prev + "_id";
		}
		else{
			return tableName + "_id";
		}
	}
}
