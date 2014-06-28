package com.gregg.iteratorrefactor.loader;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gregg.iteratorrefactor.domain.Currency;

public class CurrencyLoaderTest {

	private static final String EMPTY_ROW = "";
	// Full Row Constants
	private static final String FULL_ROW = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLM1234567890123456789012345678901234567890123456789012345!@#$%^&*";
	private static final String FULL_COUNTRY_NAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLM";
	private static final String FULL_CURRENCY_NAME = "1234567890123456789012345678901234567890123456789012345";
	private static final String FULL_CURRENCY_CODE = "!@#$%^&*";
	// Full Row Constants
	private static final String NORMAL_ROW = "ARUBA                                                            Aruban Florin                                          AWG     ";
	private static final String NORMAL_COUNTRY_NAME = "ARUBA";
	private static final String NORMAL_CURRENCY_NAME = "Aruban Florin";
	private static final String NORMAL_CURRENCY_CODE = "AWG";

	CurrencyLoader currencyLoader;

	@Before
	public void setUp() throws Exception {
		currencyLoader = new CurrencyLoader();
	}

	/**
	 * Test the Country Name Parse routine with a Full Row. This test makes sure
	 * then entire column is parsed with no extras. Should get All values
	 * entered for the Country Name Column.
	 */
	@Test
	public void parseCountryNameFromFullRow() {
		assertEquals(FULL_COUNTRY_NAME,
				currencyLoader.parseCountryName(FULL_ROW));
	}

	/**
	 * Test the Currency Name Parse routine with a Full Row. This test makes
	 * sure then entire column is parsed with no extras. Should get All values
	 * entered for the Currency Name Column.
	 */
	@Test
	public void parseCurrencyNameFromFullRow() {
		assertEquals(FULL_CURRENCY_NAME,
				currencyLoader.parseCurrencyName(FULL_ROW));
	}

	/**
	 * Test the Currency Code Parse routine with a Full Row. This test makes
	 * sure then entire column is parsed with no extras. Should get All values
	 * entered for the Currency Code Column.
	 */
	@Test
	public void parseCurrencyCodeFromFullRow() {
		assertEquals(FULL_CURRENCY_CODE,
				currencyLoader.parseCurrencyCode(FULL_ROW));
	}

	/**
	 * Test when a Full Row is parsed the returned Currency Structure contains
	 * each field.
	 */
	@Test
	public void parseCurrencyFromFullRow() {
		Currency fullCurrency = new Currency();
		fullCurrency.setCountryName(FULL_COUNTRY_NAME);
		fullCurrency.setCurrencyName(FULL_CURRENCY_NAME);
		fullCurrency.setCurrencyCode(FULL_CURRENCY_CODE);
		assertEquals(fullCurrency, currencyLoader.parseRow(FULL_ROW));
	}

	/**
	 * Test when a Normal Row is parsed the returned Country Structure contains
	 * each field.
	 */
	@Test
	public void parseCurrencyFromNormalRow() {
		Currency normalCurrency = new Currency();
		normalCurrency.setCountryName(NORMAL_COUNTRY_NAME);
		normalCurrency.setCurrencyName(NORMAL_CURRENCY_NAME);
		normalCurrency.setCurrencyCode(NORMAL_CURRENCY_CODE);
		assertEquals(normalCurrency, currencyLoader.parseRow(NORMAL_ROW));
	}

}
