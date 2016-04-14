package com.belladati.sdk.connector;

import java.io.Closeable;
import java.io.IOException;

/**
 * {@link Iterable} and {@link Closeable} definition of source {@link RowApi}s.
 * @author Lubomir Elko
 */
public interface RowsApi<T extends RowApi> extends Iterable<T>, Closeable {

	void close() throws IOException;

	/**
	 * Get header names.
	 * @return Header names as String array
	 */
	String[] getColumns();

}
