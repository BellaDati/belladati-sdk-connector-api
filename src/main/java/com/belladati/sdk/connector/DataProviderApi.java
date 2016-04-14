package com.belladati.sdk.connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides import data (including preview data and data definition) based on configured properties.
 * @author Lubomir Elko
 */
public abstract class DataProviderApi<T extends RowsApi<? extends RowApi>> {

	protected final Map<String, PropertyValueApi<?>> properties;

	/**
	 * Creates data provider with default properties merged with given {@code configuration}.
	 * @param configuration Map of key-value entries that overwrites default properties
	 */
	protected DataProviderApi(Map<String, PropertyValueApi<?>> configuration) {
		this.properties = new HashMap<String, PropertyValueApi<?>>();
		setProperties(getDefaultProperties());
		setProperties(configuration);
	}

	private void setProperties(Map<String, PropertyValueApi<?>> properties) {
		if (properties != null) {
			for (String key : properties.keySet()) {
				this.properties.put(key, properties.get(key));
			}
		}
	}

	/**
	 * Returns properties that stores <b>default</b> configuration of this data provider. These properties
	 * will be available for editing on the user interface.
	 * @return Map of key-value entries
	 * @see #getProperties()
	 */
	public abstract Map<String, PropertyValueApi<?>> getDefaultProperties();

	/**
	 * Returns properties that stores <b>current</b> configuration of this data provider.
	 * @return Map of key-value entries
	 * @see #getDefaultProperties()
	 */
	public final Map<String, PropertyValueApi<?>> getProperties() {
		return properties;
	}

	/**
	 * Checks availability of this data provider.
	 * @return Returns true if data provider is available and ready to provide data. False otherwise.
	 * @throws Throwable
	 */
	public abstract boolean check() throws Throwable;

	/**
	 * Validates configuration of this data provider.
	 * @return Returns list of messages in case there is some configuration error, otherwise returns empty list.
	 */
	public abstract List<String> validate();

	/**
	 * Returns default data definition which is a {@link RowApi} that contains column names. 
	 * @return {@link RowApi} that contains column names or {@code null} if cannot obtain column names
	 */
	public abstract RowApi provideDefaultDataDefinition();

	/**
	 * Returns preview data based on the configuration and given {@code limit} of rows. Preview data do not contain column names.
	 * @param limit Maximal number of returned rows
	 * @return {@link RowsApi} that contains preview data without column names
	 */
	public abstract T providePreviewData(int limit);

	/**
	 * Returns import data based on the configuration. Preview data do not contain column names.
	 * @param progressBar {@link ProgressBarApi} to update progress of import
	 * @return {@link RowsApi} that contains import data without column names
	 */
	public abstract T provideImportData(ProgressBarApi progressBar);

}
