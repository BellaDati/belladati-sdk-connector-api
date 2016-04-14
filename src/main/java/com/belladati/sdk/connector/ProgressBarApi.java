package com.belladati.sdk.connector;

/**
 * Progress bar displayed on user interface during import.
 * @author Lubomir Elko
 */
public interface ProgressBarApi {

	/**
	 * Updates progress to given percent value.
	 * @param progress Percent value (from 0 to 100).
	 */
	public void set(int progress);

}