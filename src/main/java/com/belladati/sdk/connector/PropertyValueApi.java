package com.belladati.sdk.connector;

/**
 * Abstract class representing value of property used in {@link DataProviderApi} configuration.
 * Contains value, default value and flag if this property is required.
 * @author Lubomir Elko
 */
public abstract class PropertyValueApi<T> {

	protected final T defaultValue;
	protected final boolean required;
	protected T value;

	/**
	 * Protected mandatory constructor.
	 * @param defaultValue Default value or {@code null}
	 * @param required Flag if this property will be required
	 */
	protected PropertyValueApi(T defaultValue, boolean required) {
		this.defaultValue = defaultValue;
		this.required = required;
	}

	/**
	 * Returns default value of this property.
	 * @return Default value of this property if it was specified, {@code null} otherwise.
	 */
	public final T getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Returns flag if this property is required or not.
	 * @return {@code true} if this property is required, {@code false} otherwise.
	 */
	public final boolean isRequired() {
		return required;
	}

	/**
	 * Returns value or default value of this property as {@link String}. Never returns {@code null}.
	 * @return Result of {@link #getValueOrDefault()} if it is not {@code null}, otherwise returns an empty
	 * {@link String} (in case of {@link StringValue} or {@link IntegerValue}) or {@link String} {@code "false"}
	 * (in case of {@link BooleanValue}).
	 */
	public abstract String getValueOrDefaultAsString();

	/**
	 * Returns value or default value of this property.
	 * @return Default value of this property if {@code value} is {@code null}, otherwise returns {@code value}.
	 */
	public final T getValueOrDefault() {
		return value == null ? defaultValue : value;
	}

	/**
	 * Returns property value that was entered by user on user interface during connector configuration.
	 * @return Value of this property if it was specified, {@code null} otherwise.
	 */
	public final T getValue() {
		return value;
	}

	/**
	 * Sets property value that was entered by user on user interface during connector configuration.
	 * @param value Value of this property or {@code null}.
	 */
	public final void setValue(T value) {
		this.value = value;
	}

	/**
	 * Validates value of this property based on the property type and {@code required} flag.
	 * @return {@code false} if this property is {@code required} and result of {@link #getValueOrDefaultAsString()}
	 * is empty, otherwise returns {@code true}.
	 */
	public final boolean isValid() {
		return !(required && getValueOrDefaultAsString().isEmpty());
	}

	/**
	 * Property value storing {@link Integer} value.
	 * @author Lubomir Elko
	 * @see PropertyValueApi
	 */
	public final static class IntegerValue extends PropertyValueApi<Integer> {

		/**
		 * Creates property value storing {@link Integer} value. Property value will be without default value
		 * and not required.
		 */
		public IntegerValue() {
			this(null, false);
		}

		/**
		 * Creates property value storing {@link Integer} value with given {@code defaultValue} and {@code required}.
		 * @param defaultValue Default value or {@code null}
		 * @param required Flag if this property will be required
		 */
		public IntegerValue(Integer defaultValue, boolean required) {
			super(defaultValue, required);
		}

		@Override
		public String getValueOrDefaultAsString() {
			return getValueOrDefault() == null ? "" : getValueOrDefault().toString();
		}

	}

	/**
	 * Property value storing {@link String} value.
	 * @author Lubomir Elko
	 * @see PropertyValueApi
	 */
	public final static class StringValue extends PropertyValueApi<String> {

		/**
		 * Creates property value storing {@link String} value. Property value will be without default value
		 * and not required.
		 */
		public StringValue() {
			this(null, false);
		}

		/**
		 * Creates property value storing {@link String} value with given {@code defaultValue} and {@code required}.
		 * @param defaultValue Default value or {@code null}
		 * @param required Flag if this property will be required
		 */
		public StringValue(String defaultValue, boolean required) {
			super(defaultValue, required);
		}

		@Override
		public String getValueOrDefaultAsString() {
			return getValueOrDefault() == null ? "" : getValueOrDefault();
		}

	}

	/**
	 * Property value storing {@link Boolean} value.
	 * @author Lubomir Elko
	 * @see PropertyValueApi
	 */
	public final static class BooleanValue extends PropertyValueApi<Boolean> {

		/**
		 * Creates property value storing {@link Boolean} value. Property value will be without default value
		 * and not required.
		 */
		public BooleanValue() {
			this(null, false);
		}

		/**
		 * Creates property value storing {@link Boolean} value with given {@code defaultValue} and {@code required}.
		 * @param defaultValue Default value or {@code null}
		 * @param required Flag if this property will be required
		 */
		public BooleanValue(Boolean defaultValue, boolean required) {
			super(defaultValue, required);
		}

		@Override
		public String getValueOrDefaultAsString() {
			return getValueOrDefault() == null ? "false" : getValueOrDefault().toString();
		}

	}

}
