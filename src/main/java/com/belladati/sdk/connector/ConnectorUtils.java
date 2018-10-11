package com.belladati.sdk.connector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.belladati.sdk.connector.PropertyValueApi.BooleanValue;
import com.belladati.sdk.connector.PropertyValueApi.IntegerValue;
import com.belladati.sdk.connector.PropertyValueApi.StringValue;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;

/**
 * Utilities for custom connector.
 * @author Lubomir Elko
 */
public class ConnectorUtils {

	/**
	 * Returns name ({@link Class#getName()}) of each subtype found in classpath based on given {@code clazz}
	 * @param clazz Parent class
	 * @return List of found class names
	 */
	public static List<String> getSubTypesOf(Class<?> clazz) {
		List<String> list = new ArrayList<>();
		for (ClassInfo ci : new ClassGraph().whitelistPackages("com.belladati.sdk.connector").scan()
			.getSubclasses(clazz.getName())) {
			if (!ci.isAbstract() && !ci.isInterface()) {
				list.add(ci.getName());
			}
		}
		return list;
		//return new FastClasspathScanner("com.belladati.sdk.connector").scan().getNamesOfSubclassesOf(clazz);
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

	/**
	 * Returns {@link PropertyValueApi} by given {@code key}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Property value
	 */
	public static PropertyValueApi<?> getPropertyValue(Map<String, PropertyValueApi<?>> properties, String key) {
		if (properties == null || !properties.containsKey(key)) {
			throw new IllegalStateException("DataProvider configuration does not contain property with key: " + key);
		} else {
			return properties.get(key);
		}
	}

	/**
	 * Returns {@link StringValue} by given {@code key} as {@link String}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Value as {@link String}
	 */
	public static String getStringValue(Map<String, PropertyValueApi<?>> properties, String key) {
		return getPropertyValue(properties, key).getValueOrDefaultAsString();
	}

	/**
	 * Returns {@link IntegerValue} by given {@code key} as {@link Integer}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Value as {@link Integer}
	 */
	public static Integer getIntegerValue(Map<String, PropertyValueApi<?>> properties, String key) {
		return Integer.valueOf(getPropertyValue(properties, key).getValueOrDefaultAsString());
	}

	/**
	 * Returns {@link IntegerValue} by given {@code key} as {@code int}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Value as {@code int}
	 */
	public static int getIntValue(Map<String, PropertyValueApi<?>> properties, String key) {
		return getIntegerValue(properties, key).intValue();
	}

	/**
	 * Returns {@link IntegerValue} by given {@code key} as {@link Boolean}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Value as {@link Boolean}
	 */
	public static Boolean getBooleanValue(Map<String, PropertyValueApi<?>> properties, String key) {
		return Boolean.valueOf(getPropertyValue(properties, key).getValueOrDefaultAsString());
	}

	/**
	 * Returns {@link BooleanValue} by given {@code key} as {@code boolean}.
	 * @param properties Data provider configuration
	 * @param key Key of property value
	 * @return Value as {@code boolean}
	 */
	public static boolean getBoolValue(Map<String, PropertyValueApi<?>> properties, String key) {
		return getBooleanValue(properties, key).booleanValue();
	}

	/**
	 * Computes and sets percentage value into given progress bar.
	 * @param progressBar Reference to progress bar displayed on user interface during import or {@code null}
	 * @param rowIndex Current row index
	 * @param totalRows Total number of rows
	 */
	public static void updateProgressBar(ProgressBarApi progressBar, long rowIndex, long totalRows) {
		if (progressBar != null) {
			if (rowIndex < 1 || totalRows < 1) {
				progressBar.set(0);
			} else {
				progressBar.set((int) (((float) rowIndex / totalRows) * 100));
			}
		}
	}

}
