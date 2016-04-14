package com.belladati.sdk.connector;

/**
 * One source row represented as array of {@link String} values.
 * @author Lubomir Elko
 */
public interface RowApi {

	/**
	 * Returns array of source values in default column order.
	 * @return Array of {@link String} values
	 */
	public String[] getValues();

	/**
	 * Returns one source value by given index of a column.
	 * @param columnIndex Index of a column
	 */
	public String getValue(int columnIndex);

	/**
	 * Returns number of source values available on this row.
	 * @return Number of source values
	 */
	public int getLength();

	/**
	 * Returns index of this row.
	 * @return Index of this row
	 */
	public int getIndex();

}
