package com.trunk.core.web;

import com.trunk.core.json.CustomerJsonSerializer;
import com.trunk.core.json.JsonFilter;
import com.trunk.core.json.JsonFilters;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
public class JsonReturnHandler implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {

        JsonFilters filters = returnType.getMethodAnnotation(JsonFilters.class);
        JsonFilter filter = returnType.getMethodAnnotation(JsonFilter.class);

        if(filters != null && filters.value()!=null && filters.value().length!=0)
        	return true;

        if(filter!=null)
        	return true;

        return false;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
			throws Exception {
		// TODO Auto-generated method stub

		// 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
		mavContainer.setRequestHandled(true);

		// 获得注解并执行filter方法 最后返回
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);


		CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
		JsonFilters anno = returnType.getMethodAnnotation(JsonFilters.class);
		if(anno != null && anno.value()!=null && anno.value().length!=0){

			for (JsonFilter jsonFilter : anno.value()) {
				//判断是过滤还是包含，然后进行操作
				if(StringUtils.isNotBlank(jsonFilter.filter())){
					jsonSerializer.ignore(jsonFilter.type(), jsonFilter.filter());
				}
				else if(StringUtils.isNotBlank(jsonFilter.include())){
					jsonSerializer.include(jsonFilter.type(), jsonFilter.include());
				}
			}
		}

		JsonFilter filter = returnType.getMethodAnnotation(JsonFilter.class);
		if(filter!=null){
			if(StringUtils.isNotBlank(filter.filter())){
				jsonSerializer.ignore(filter.type(), filter.filter());
			}
			if(StringUtils.isNotBlank(filter.include())){
				jsonSerializer.include(filter.type(), filter.include());
			}
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "1800");

		String json = jsonSerializer.toJson(returnValue);
		response.getWriter().write(json);


	}

}
