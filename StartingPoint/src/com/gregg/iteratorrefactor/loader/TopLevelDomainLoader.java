/**
 * 
 */
package com.gregg.iteratorrefactor.loader;

import com.gregg.iteratorrefactor.domain.TopLevelDomain;
import com.gregg.iteratorrefactor.loader.util.FileLoader;

/**
 * @author gregg
 * 
 */
public class TopLevelDomainLoader extends FileLoader<TopLevelDomain> {

	private static final int TOP_LEVEL_DOMAIN_BEGIN_INDEX = 0;
	private static final int TOP_LEVEL_DOMAIN_END_INDEX = 80;

	/**
	 * From a String of Fixed Position characters parse each field and populate
	 * a Top Level Domain Structure.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Top Level Domain Structure Object (null if @row is empty)
	 */
	protected TopLevelDomain parseRow(String row) {
		TopLevelDomain topLevelDomain = null;
		if (row != null && row.length() != 0) {
			topLevelDomain = new TopLevelDomain();
			topLevelDomain.setTopLevelDomain(parseTopLevelDomain(row));
		}
		return topLevelDomain;
	}

	/**
	 * From a String containing the Row that was read parse the Top Level
	 * Domain. It is expected to be fixed position row with trailing whitespace.
	 * The resulting Top Level Domain will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Top Level Domain as String
	 */
	protected String parseTopLevelDomain(String row) {
		return parseColumn(row, TOP_LEVEL_DOMAIN_BEGIN_INDEX,
				TOP_LEVEL_DOMAIN_END_INDEX);
	}

}
