package com.trunk.core.mybatis;

import com.trunk.core.base.BaseEntity;
import com.trunk.core.reflect.FastMethodCall;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import org.springframework.cglib.reflect.FastMethod;


import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

/***
 * @author fanhaoming
 * @DateTime 2019/08/14 10:25
 */

@Intercepts(value = { @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }), })
public class CommonIntercept implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		Object target = invocation.getTarget();
		Object result = null;

		if (target instanceof Executor) {
			/** 执行方法 */

			if (invocation.getArgs()[0] instanceof MappedStatement) {
				MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
				if (invocation.getArgs()[1] instanceof BaseEntity) {
					SqlCommandType sqlCommandType = statement.getSqlCommandType();

					if (SqlCommandType.INSERT.equals(sqlCommandType)) {
						Object domain = (Object) invocation.getArgs()[1];
						FastMethod getCreateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "getCreateTime");
						FastMethod 	setCreateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "setCreateTime", Date.class);

						if (getCreateTimeMethod.invoke(domain, null) == null) {
							setCreateTimeMethod.invoke(domain, new Object[] { new Date() });
						}
						//TODO :创建人设置

					} else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
						if (invocation.getArgs()[1] instanceof BaseEntity) {
							Object domain = (Object) invocation.getArgs()[1];

							FastMethod getUpdateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "getUpdateTime");
							FastMethod setUpdateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "setUpdateTime", Date.class);

							setUpdateTimeMethod.invoke(domain, new Object[] { new Date() });

							//TODO: 修改人设置

						}
					}
				}else if (invocation.getArgs()[1] instanceof StrictMap<?>) {//批量操作
					StrictMap<?> map = (StrictMap<?>) invocation.getArgs()[1];
					for (Entry<String, ?> entry : map.entrySet()) {
						if (entry.getValue() instanceof List<?>) {
							SqlCommandType sqlCommandType = statement.getSqlCommandType();
							if (SqlCommandType.INSERT.equals(sqlCommandType)) {

								List<?> lists = (List<?>) entry.getValue();
								for (Object object : lists) {

									Object domain = (Object) object;
									FastMethod getCreateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "getCreateTime");
									FastMethod setCreateTimeMethod = FastMethodCall.getInstance().getMethod(domain.getClass(), "setCreateTime", Date.class);

									if (getCreateTimeMethod.invoke(object, null) == null) {
										setCreateTimeMethod.invoke(object, new Object[] { new Date() });
									}
									//TODO :批量插入创建人设置


								}
							}
						}
					}
				}
			}
			result = invocation.proceed();
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
