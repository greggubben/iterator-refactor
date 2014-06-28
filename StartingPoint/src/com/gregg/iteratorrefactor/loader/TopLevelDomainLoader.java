/**
 * 
 */
package com.gregg.iteratorrefactor.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gregg.iteratorrefactor.domain.TopLevelDomain;
import com.gregg.iteratorrefactor.loader.util.FileLoader;

/**
 * @author gregg
 * 
 */
public class TopLevelDomainLoader extends FileLoader {

	private static final int TOP_LEVEL_DOMAIN_BEGIN_INDEX = 0;
	private static final int TOP_LEVEL_DOMAIN_END_INDEX = 80;

	/**
	 * Load the Top Level Domain File into a Memory Array
	 * 
	 * @param filename
	 * @return ArrayList of Top Level Domains
	 * @throws FileNotFoundException
	 */
	public ArrayList<TopLevelDomain> loadFile(String filename)
			throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filename));

		return parseFile(br);
	}

	/**
	 * Parse row by row through a file.
	 * 
	 * @param br
	 * @return
	 */
	protected ArrayList<TopLevelDomain> parseFile(BufferedReader br) {
		ArrayList<TopLevelDomain> topLevelDomains = new ArrayList<TopLevelDomain>();
		String row = null;
		try {
			while ((row = br.readLine()) != null) {
				topLevelDomains.add(parseRow(row));
			}
		} catch (IOException ioe) {
			// Print the stack trace and return what we have
			// TODO: This needs better error handling
			ioe.printStackTrace();
		}
		return topLevelDomains;
	}

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
