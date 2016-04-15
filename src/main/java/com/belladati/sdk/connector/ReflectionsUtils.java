package com.belladati.sdk.connector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;

/**
 * Utilities for Java Reflection
 * @author Lubomir Elko
 */
public class ReflectionsUtils {

	/**
	 * Returns name ({@link Class#getName()}) of each subtype found in classpath based on given {@code clazz}
	 * @param clazz Parent class
	 * @return List of found class names
	 */
	public static List<String> getSubTypesOf(Class<?> clazz) {
		Reflections reflections = new Reflections("com.belladati");
		List<String> subTypes = new ArrayList<String>();
		for (Class<?> subTypeClass : reflections.getSubTypesOf(clazz)) {
			subTypes.add(subTypeClass.getName());
		}
		return subTypes;
	}

	/**
	 * Invokes static method {@code methodName} in class by given {@code className} without parameters.
	 * @param className Class name, e.g. {@code java.lang.Object}
	 * @param methodName Name of static method to invoke
	 * @return Result of invoked method 
	 * @throws Throwable If there is some unexpected problem during execution
	 */
	public static Object invokeStaticMethod(String className, String methodName) throws Throwable {
		return invokeStaticMethod(className, methodName, new Class<?>[] {}, new Object[] {});
	}

	/**
	 * Invokes static method {@code methodName} in class by given {@code className}, {@code paramTypes} and {@code paramValues}.
	 * @param className Class name, e.g. {@code java.lang.Object}
	 * @param methodName Name of static method to invoke
	 * @param paramTypes Array containing parameter types
	 * @param paramValues Array containing parameter values
	 * @return Result of invoked method 
	 * @throws Throwable If there is some unexpected problem during execution
	 */
	public static Object invokeStaticMethod(String className, String methodName, Class<?>[] paramTypes, Object[] paramValues)
		throws Throwable {
		Class<?> clazz = Class.forName(className);
		Method method = clazz.getMethod(methodName, paramTypes);
		return method.invoke(null, paramValues);
	}

	/**
	 * Invokes constructor in class by given {@code className}, {@code paramTypes} and {@code paramValues}.
	 * @param className Class name, e.g. {@code java.lang.Object}
	 * @param paramTypes Array containing parameter types
	 * @param paramValues Array containing parameter values
	 * @return New class instance
	 * @throws Throwable If there is some unexpected problem during execution
	 */
	public static Object invokeConstructor(String className, Class<?>[] paramTypes, Object[] paramValues) throws Throwable {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(paramTypes);
		return constructor.newInstance(paramValues);
	}

}
