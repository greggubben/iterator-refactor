package com.gregg.iteratorrefactor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
	 * Test when a Null Row is parsed the returned Currency Structure is null.
	 */
	@Test
	public void parseCurrencyFromNull() {
		assertNull(currencyLoader.parseRow(null));
	}

	/**
	 * Test when an Empty Row is parsed the returned Currency Structure is null.
	 */
	@Test
	public void parseCurrencyFromEmptyRow() {
		assertNull(currencyLoader.parseRow(EMPTY_ROW));
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

	/**
	 * Test when a Single Row is read from a Single Row File.
	 */
	@Test
	public void parseCurrencyFromSingleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Currency normalCurrency = new Currency();
		normalCurrency.setCountryName(NORMAL_COUNTRY_NAME);
		normalCurrency.setCurrencyName(NORMAL_CURRENCY_NAME);
		normalCurrency.setCurrencyCode(NORMAL_CURRENCY_CODE);
		assertEquals(normalCurrency, currencyLoader.parseFile(br).get(0));
	}

	/**
	 * Test Count of Rows read from a Multi Row File.
	 */
	@Test
	public void parseCountCurrencyFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Currency normalCurrency = new Currency();
		normalCurrency.setCountryName(NORMAL_COUNTRY_NAME);
		normalCurrency.setCurrencyName(NORMAL_CURRENCY_NAME);
		normalCurrency.setCurrencyCode(NORMAL_CURRENCY_CODE);
		assertEquals(3, currencyLoader.parseFile(br).size());
	}

	/**
	 * Test when First Row is read from a Multi Row File.
	 */
	@Test
	public void parseFirstCurrencyFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Currency normalCurrency = new Currency();
		normalCurrency.setCountryName(NORMAL_COUNTRY_NAME);
		normalCurrency.setCurrencyName(NORMAL_CURRENCY_NAME);
		normalCurrency.setCurrencyCode(NORMAL_CURRENCY_CODE);
		assertEquals(normalCurrency, currencyLoader.parseFile(br).get(0));
	}

	/**
	 * Test when Last Row is read from a Multi Row File.
	 */
	@Test
	public void parseLastCurrencyFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(NORMAL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Currency normalCurrency = new Currency();
		normalCurrency.setCountryName(NORMAL_COUNTRY_NAME);
		normalCurrency.setCurrencyName(NORMAL_CURRENCY_NAME);
		normalCurrency.setCurrencyCode(NORMAL_CURRENCY_CODE);
		ArrayList<Currency> currencies = currencyLoader.parseFile(br);
		assertEquals(normalCurrency, currencies.get(currencies.size() - 1));
	}

	/**
	 * Test Count of Rows read from the Real Currency File.
	 * 
	 * @throws FileNotFoundException
	 */
	// @Ignore("Test has external dependencies")
	@Test
	public void parseCountCurrencyFromRealCurrencyFile()
			throws FileNotFoundException {
		assertEquals(256, currencyLoader.loadFile("../data/currency.txt")
				.size());
	}

}
