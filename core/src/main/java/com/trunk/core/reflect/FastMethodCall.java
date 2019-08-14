package com.trunk.core.reflect;


import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FastMethodCall {
	private static FastMethodCall instance;

	/**
	 * Singleton access point to the manager.
	 */
	public static FastMethodCall getInstance() {
		synchronized (FastMethodCall.class) {
			if (instance == null) {
				instance = new FastMethodCall();
				instance.init();
			}
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	HashMap<Class, FastClass> classMap;
	HashMap<String, FastMethod> methodMap;

	@SuppressWarnings("rawtypes")
	private void init() {

		classMap = new HashMap<Class, FastClass>();
		methodMap = new HashMap<String, FastMethod>();
	}

	@SuppressWarnings({ "rawtypes" })
	public FastMethod getMethod(Class clazz, String method, Class dest) {

		FastClass cglibBeanClass = null;
		FastMethod cglibMethod = null;
		if (!classMap.containsKey(clazz)) {
			cglibBeanClass = FastClass.create(clazz);
			classMap.put(clazz, cglibBeanClass);
		} else {
			cglibBeanClass = classMap.get(clazz);
		}
		String fullMethod = clazz.getPackage() + "." + clazz.getSimpleName() + "." + method;

		if (!methodMap.containsKey(fullMethod)) {

			Method getMethod = null;

			while (true) {
				getMethod = getDeclaredMethod(clazz, method, dest);
				if (getMethod != null)
					break;

				clazz = clazz.getSuperclass();
				if (clazz.equals(Object.class)) {
					break;
				}
			}

			if (getMethod == null) {
				return null;
			}

			cglibMethod = cglibBeanClass.getMethod(getMethod);
			methodMap.put(fullMethod, cglibMethod);

		} else {
			cglibMethod = methodMap.get(fullMethod);
		}

		return cglibMethod;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Method getDeclaredMethod(Class clazz, String methodName, Class dest) {

		Method method = null;
		try {

			if (dest == null) {
				method = clazz.getDeclaredMethod(methodName);
			} else {
				method = clazz.getDeclaredMethod(methodName, dest);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return method;
	}

	@SuppressWarnings({ "rawtypes" })
	public FastMethod getMethod(Class clazz, String method) {

		return this.getMethod(clazz, method, null);
	}

	public Object getMethodValue(FastMethod method, Object obj) {
		Object result = null;
		try {
			result = method.invoke(obj, null);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
