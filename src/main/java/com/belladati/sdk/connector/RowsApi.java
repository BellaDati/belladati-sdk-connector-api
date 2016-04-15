package com.belladati.sdk.connector;

import java.io.Closeable;

/**
 * {@link Iterable} and {@link Closeable} definition of source rows.
 * @author Lubomir Elko
 * @see RowApi
 */
public interface RowsApi<T extends RowApi> extends Iterable<T>, Closeable {

	/**
	 * Get header names.
	 * @return Header names as String array
	 */
	String[] getColumns();

}
