package org.litnhjacuzzi.crosshairtargetfilter;

import java.lang.reflect.Constructor;

public class ReflectionUtil {
	public static <R> R newInstance(Class<R> cls, Class<?>[] paramTypes, Object... params) {
		try {
			Constructor<R> constructor = cls.getDeclaredConstructor(paramTypes);
			constructor.setAccessible(true);
			return constructor.newInstance(params);
		} catch (Exception e) {
			return null;
		}
	}
}
