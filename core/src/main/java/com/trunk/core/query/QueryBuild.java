package com.trunk.core.query;

import java.util.ArrayList;
import java.util.List;
/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class QueryBuild {

	public static final String NOT_EQUAL_QUERY = "notequal";
	public static final String EQUAL_QUERY_TYPE = "equal";
	public static final String LIKE_QUERY_TYPE = "like";
	public static final String LEFT_LIKE_QUERY_TYPE = "leftLike";
	public static final String RIGHT_LIKE_QUERY_TYPE = "rightLike";
	
	public static final String GREATER_QUERY_TYPE = "greater";
	public static final String LESS_QUERY_TYPE = "less";

	public static final String GREATER_EQUAL_QUERY_TYPE = "greaterEqual";
	public static final String LESS_EQUAL_QUERY_TYPE = "lessEqual";

	public static final String INCLUDE_QUERY_TYPE = "include";

	public static final String IN_QUERY_TYPE = "in";

	public static final String DEFAULT_ID_COLUMN = "id";

	private List<QueryItem> queryItems;

	public List<QueryItem> getQueryItems() {
		return queryItems;
	}

	public QueryBuild() {
		queryItems = new ArrayList<QueryItem>();
	}

	public void addQueryItem(String column, Object value, String type) {
		if (value != null) {
			queryItems.add(new QueryItem(column, value, type));
		}
	}

	public QueryBuild addEqualQuery(String column, Object value) {
		addQueryItem(column, value, EQUAL_QUERY_TYPE);
		return this;
	}

	public QueryBuild addLikeQuery(String column, Object value) {
		addQueryItem(column, value, LIKE_QUERY_TYPE);
		return this;
	}
	
	public QueryBuild addLeftLikeQuery(String column, Object value) {
		addQueryItem(column, value, LEFT_LIKE_QUERY_TYPE);
		return this;
	}

	public QueryBuild addGreaterQuery(String column, Object value) {
		addQueryItem(column, value, GREATER_QUERY_TYPE);

		return this;
	}

	public QueryBuild addLessQuery(String column, Object value) {
		addQueryItem(column, value, LESS_QUERY_TYPE);
		return this;
	}

	public QueryBuild addGreaterEqualQuery(String column, Object value) {
		addQueryItem(column, value, GREATER_EQUAL_QUERY_TYPE);
		return this;
	}

	public QueryBuild addLessEqualQuery(String column, Object value) {
		addQueryItem(column, value, LESS_EQUAL_QUERY_TYPE);
		return this;
	}

	public QueryBuild addIncludeQuery(String column, Object value) {
		addQueryItem(column, value, INCLUDE_QUERY_TYPE);
		return this;
	}

	public QueryBuild addInQuery(String column, Object value) {
		addQueryItem(column, value, IN_QUERY_TYPE);
		return this;
	}

}
