package com.gregg.iteratorrefactor.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TopLevelDomainTest {
	TopLevelDomain topLevelDomain;
	TopLevelDomain topLevelDomainCompare;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		topLevelDomain = new TopLevelDomain();
		topLevelDomainCompare = new TopLevelDomain();
	}

	/**
	 * Null comparison should not be equal.
	 */
	@Test
	public void equalsNullReturnsFalse() {
		assertFalse(topLevelDomain.equals(null));
	}

	/**
	 * A Different Object should not be equal.
	 */
	@Test
	public void equalsDiffObjectReturnsFalse() {
		assertFalse(topLevelDomain.equals(new String()));
	}

	/**
	 * 2 Empty TLD should be Equal.
	 */
	@Test
	public void equalsSameEmptyObjectsReturnsTrue() {
		assertTrue(topLevelDomain.equals(topLevelDomainCompare));
	}

	/**
	 * TLD with Same Top Level Domain only should be Equal.
	 */
	@Test
	public void equalsSameTopLevelDomainOnlyReturnsTrue() {
		topLevelDomain.setTopLevelDomain("ABC");
		topLevelDomainCompare.setTopLevelDomain("ABC");
		assertTrue(topLevelDomain.equals(topLevelDomainCompare));
	}

	/**
	 * TLD with Different Top Level Domains should not be Equal.
	 */
	@Test
	public void equalsDiffTopLevelDomainReturnsFalse() {
		topLevelDomain.setTopLevelDomain("ABC");
		topLevelDomainCompare.setTopLevelDomain("DEF");
		assertFalse(topLevelDomain.equals(topLevelDomainCompare));
	}

}
