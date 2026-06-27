package org.litnhjacuzzi.crosshairtargetfilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionUtil {
	@SuppressWarnings("unchecked")
	public static <R> R invokeMethod(Class<?> cls, Object instance, Class<R> retType, String methodName,
			Class<?>[] paramTypes, Object... params) {
		try {
			Method method = cls.getDeclaredMethod(methodName, paramTypes);
			method.setAccessible(true);
			return (R) method.invoke(instance, params);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <R> R newInstance(Class<R> cls, Class<?>[] paramTypes, Object... params) {
		try {
			Constructor<R> constructor = cls.getDeclaredConstructor(paramTypes);
			constructor.setAccessible(true);
			return constructor.newInstance(params);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Object newInstance(String className, Class<?>[] paramTypes, Object... params) {
		try {
			return newInstance(Class.forName(className), paramTypes, params);
		} catch (Exception e) {
			return null;
		}
	}
}
