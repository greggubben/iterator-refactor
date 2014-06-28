/**
 * 
 */
package com.gregg.iteratorrefactor.loader;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author gregg
 * 
 */
public class TopLevelDomainLoaderTest {

	private static final String EMPTY_ROW = "";
	// Full Row Constants
	private static final String FULL_ROW = "ABCDEFG          ";
	private static final String FULL_TOP_LEVEL_DOMAIN = "ABCDEFG";

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

}
