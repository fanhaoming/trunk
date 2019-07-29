package com.trunk.core.query;


import com.trunk.core.base.AbstractCondition;
import com.trunk.core.utils.DateHelper;
import com.trunk.core.utils.PropertyNameConvert;
import com.trunk.core.utils.StringHelper;
import com.trunk.core.query.ReflectConditionField.FieldAnnotation;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class ConditionQuery {

	private AbstractCondition condition;

	private QueryBuild queryBuild;

	private List<OrderByColumn> orderColumns;

	private Integer start;

	private Integer pageSize;

	private Integer pageIndex;

	private String groupBy;

	public ConditionQuery() {
		queryBuild = new QueryBuild();
		this.start = -1;
	}

	public ConditionQuery addQueryItem(String column, Object value, String type) {
		queryBuild.addQueryItem(column, value, type);
		return this;
	}

	public ConditionQuery(AbstractCondition condition) {
		this.condition = condition;
		createBuild();
		createOrder();

		this.pageSize = PageHelp.getPageSize(condition);
		this.start = PageHelp.getStartIndex(condition);
		this.pageIndex = PageHelp.getPageIndex(condition);
	}



	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public QueryBuild getQueryBuild() {
		return queryBuild;
	}

	public void setQueryBuild(QueryBuild queryBuild) {
		this.queryBuild = queryBuild;
	}

	public List<OrderByColumn> getOrderColumns() {
		return orderColumns;
	}

	public void setOrderColumns(List<OrderByColumn> orderColumns) {
		this.orderColumns = orderColumns;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void addOrder(String column, boolean isDesc) {
		if (orderColumns == null) {
			orderColumns = new ArrayList<OrderByColumn>();
		}
		OrderByColumn orderByColumn = new OrderByColumn(column, isDesc);
		orderColumns.add(orderByColumn);
	}

	private void createOrder() {
		if (orderColumns == null) {
			orderColumns = new ArrayList<OrderByColumn>();
		}
		List<String> sortColumnStr = this.condition.getSortColumn();
		if(sortColumnStr != null && sortColumnStr.size()>0){
			sortColumnStr.stream().forEach(sortColumn ->{
				String column = "";
				boolean isDesc = true;
				if(sortColumnStr.contains(" ")){
					String[] sortColumnArr = sortColumn.split(" ");
					column = sortColumnArr[0];
					if(sortColumnArr[1].equals("desc")){
						isDesc = true;
					}
					if(sortColumnArr[1].equals("asc")){
						isDesc = false;
					}
				}
				else{
					column = sortColumn;
				}
				addOrder(PropertyNameConvert.propertyToColumn(column), isDesc);
			});

		}

	}

	public void createBuild(AbstractCondition condition){
		this.condition = condition;
		createBuild();
	}


	private void createBuild() {
		queryBuild = new QueryBuild();

		List<FieldAnnotation> fieldAnnotations = ReflectConditionField.getInstance().traverseFieldAnnoation(this.condition.getClass(), Query.class);
		for (FieldAnnotation fieldAnnotation : fieldAnnotations) {
			Query annotation = (Query) fieldAnnotation.getAnnotation();
			if (annotation != null) {
				String column = annotation.column();
				if (annotation.type().equals(QueryBuild.INCLUDE_QUERY_TYPE)) {
					column = this.condition.getClass().getSimpleName() + "." + fieldAnnotation.getField().getName();
				}

				if (StringHelper.IsNullOrEmpty(column)) {
					column = PropertyNameConvert.propertyToColumn(fieldAnnotation.getField().getName());
				}

				try {
					//根据Field获取condition中对应的字段的值
					Object value = fieldAnnotation.getField().get(this.condition);

					if (value != null) {
						//判断是否是日期
						if(fieldAnnotation.getField().getGenericType().toString().equals("class java.util.Date")){
							if (annotation.isMaxDate()) {//如果需要获取日期的当天的最大值即23::59:59
								value = DateHelper.convertTodayMax((Date) value);
							}else if (annotation.isMinDate()) {//如果需要获取日期的当天的最小值即00:00:00
								value = DateHelper.convertTodayMin((Date) value);
							}
						}

						if (value instanceof String) {
							if (StringUtils.isNotBlank(value.toString())) {
								queryBuild.addQueryItem(column, value, annotation.type());
							}
						} else {
							queryBuild.addQueryItem(column, value, annotation.type());
						}

					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
