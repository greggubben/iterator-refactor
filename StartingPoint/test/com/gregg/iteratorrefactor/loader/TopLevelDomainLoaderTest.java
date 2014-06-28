/**
 * 
 */
package com.gregg.iteratorrefactor.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import com.gregg.iteratorrefactor.domain.TopLevelDomain;

/**
 * @author gregg
 * 
 */
public class TopLevelDomainLoaderTest {

	private static final String EMPTY_ROW = "";
	// Full Row Constants
	private static final String FULL_ROW = "ABCDEFG          ";
	private static final String FULL_TOP_LEVEL_DOMAIN = "ABCDEFG";

	// Normal Row Constants
	private static final String NORMAL_ROW = "COM";
	private static final String NORMAL_TOP_LEVEL_DOMAIN = "COM";

	TopLevelDomainLoader topLevelDomainLoader;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		topLevelDomainLoader = new TopLevelDomainLoader();
	}

	/**
	 * Test the Top Level Domain Parse routine with a Full Row. This test makes
	 * sure then entire column is parsed with no extras. Should get All values
	 * entered for the Top Level Domain Column.
	 */
	@Test
	public void parseTopLevelDomainFromFullRow() {
		assertEquals(FULL_TOP_LEVEL_DOMAIN,
				topLevelDomainLoader.parseTopLevelDomain(FULL_ROW));
	}

	/**
	 * Test when a Null Row is parsed the returned Top Level Domain Structure is
	 * null.
	 */
	@Test
	public void parseTopLevelDomainFromNull() {
		assertNull(topLevelDomainLoader.parseRow(null));
	}

	/**
	 * Test when an Empty Row is parsed the returned Top Level Domain Structure
	 * is null.
	 */
	@Test
	public void parseTopLevelDomainFromEmptyRow() {
		assertNull(topLevelDomainLoader.parseRow(EMPTY_ROW));
	}

	/**
	 * Test when a Single Row is read from a Single Row File.
	 */
	@Test
	public void parseTopLevelDomainFromSingleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		TopLevelDomain fullCountry = new TopLevelDomain();
		fullCountry.setTopLevelDomain(NORMAL_TOP_LEVEL_DOMAIN);
		assertEquals(fullCountry, topLevelDomainLoader.parseFile(br).get(0));
	}

}
