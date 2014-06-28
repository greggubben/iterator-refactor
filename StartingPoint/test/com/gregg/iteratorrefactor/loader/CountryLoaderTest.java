/*
 * Copyright 2014 (C) Gregg Ubben and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *    
 */

package com.gregg.iteratorrefactor.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.gregg.iteratorrefactor.domain.Country;
import com.gregg.iteratorrefactor.loader.CountryLoader;

public class CountryLoaderTest {

	private static final String EMPTY_ROW = "";
	// Full Row Constants
	private static final String FULL_ROW = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCD123456789012!@#$%^&*()+";
	private static final String FULL_COUNTRY_NAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
	private static final String FULL_ISO2ALPHA = "123456789012";
	private static final String FULL_ISO3ALPHA = "!@#$%^&*()+";
	// Normal Row Constants
	private static final String NORMAL_ROW = "ARUBA                                                   AW          ABW        ";
	private static final String NORMAL_COUNTRY_NAME = "ARUBA";
	private static final String NORMAL_ISO2ALPHA = "AW";
	private static final String NORMAL_ISO3ALPHA = "ABW";

	CountryLoader countryLoader;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		countryLoader = new CountryLoader();
	}

	/**
	 * Test the Country Name Parse routine with a Full Row. This test makes sure
	 * then entire column is parsed with no extras. Should get All values
	 * entered for the Country Name Column.
	 */
	@Test
	public void parseCountryNameFromFullRow() {
		assertEquals(FULL_COUNTRY_NAME,
				countryLoader.parseCountryName(FULL_ROW));
	}

	/**
	 * Test the Country ISO 2-alpha Code Parse routine with a Full Row. This
	 * test makes sure then entire column is parsed with no extras. Should get
	 * All values entered for the Country ISO 2-alpha Code Column.
	 */
	@Test
	public void parseIso2AlphaFromFullRow() {
		assertEquals(FULL_ISO2ALPHA, countryLoader.parseIso2Alpha(FULL_ROW));
	}

	/**
	 * Test the Country ISO 3-alpha Code Parse routine with a Full Row. This
	 * test makes sure then entire column is parsed with no extras. Should get
	 * All values entered for the Country ISO 3-alpha Code Column.
	 */
	@Test
	public void parseIso3AlphaFromFullRow() {
		assertEquals(FULL_ISO3ALPHA, countryLoader.parseIso3Alpha(FULL_ROW));
	}

	/**
	 * Test when a Null Row is parsed the returned Country Structure is null.
	 */
	@Test
	public void parseCountryFromNull() {
		assertNull(countryLoader.parseRow(null));
	}

	/**
	 * Test when an Empty Row is parsed the returned Country Structure is null.
	 */
	@Test
	public void parseCountryFromEmptyRow() {
		assertNull(countryLoader.parseRow(EMPTY_ROW));
	}

	/**
	 * Test when a Full Row is parsed the returned Country Structure contains
	 * each field.
	 */
	@Test
	public void parseCountryFromFullRow() {
		Country fullCountry = new Country();
		fullCountry.setCountryName(FULL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(FULL_ISO2ALPHA);
		fullCountry.setIso3Alpha(FULL_ISO3ALPHA);
		assertEquals(fullCountry, countryLoader.parseRow(FULL_ROW));
	}

	/**
	 * Test when a Normal Row is parsed the returned Country Structure contains
	 * each field.
	 */
	@Test
	public void parseCountryFromNormalRow() {
		Country fullCountry = new Country();
		fullCountry.setCountryName(NORMAL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(NORMAL_ISO2ALPHA);
		fullCountry.setIso3Alpha(NORMAL_ISO3ALPHA);
		assertEquals(fullCountry, countryLoader.parseRow(NORMAL_ROW));
	}

	/**
	 * Test when a Single Row is read from a Single Row File.
	 */
	@Test
	public void parseCountryFromSingleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Country fullCountry = new Country();
		fullCountry.setCountryName(NORMAL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(NORMAL_ISO2ALPHA);
		fullCountry.setIso3Alpha(NORMAL_ISO3ALPHA);
		assertEquals(fullCountry, countryLoader.parseFile(br).get(0));
	}

	/**
	 * Test Count of Rows read from a Multi Row File.
	 */
	@Test
	public void parseCountCountryFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Country fullCountry = new Country();
		fullCountry.setCountryName(NORMAL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(NORMAL_ISO2ALPHA);
		fullCountry.setIso3Alpha(NORMAL_ISO3ALPHA);
		assertEquals(3, countryLoader.parseFile(br).size());
	}

	/**
	 * Test when First Row is read from a Multi Row File.
	 */
	@Test
	public void parseFirstCountryFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		Country fullCountry = new Country();
		fullCountry.setCountryName(NORMAL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(NORMAL_ISO2ALPHA);
		fullCountry.setIso3Alpha(NORMAL_ISO3ALPHA);
		assertEquals(fullCountry, countryLoader.parseFile(br).get(0));
	}

	/**
	 * Test when Last Row is read from a Multi Row File.
	 */
	@Test
	public void parseLastCountryFromMultipleRowFile() {
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
		Country fullCountry = new Country();
		fullCountry.setCountryName(NORMAL_COUNTRY_NAME);
		fullCountry.setIso2Alpha(NORMAL_ISO2ALPHA);
		fullCountry.setIso3Alpha(NORMAL_ISO3ALPHA);
		ArrayList<Country> counties = countryLoader.parseFile(br);
		assertEquals(fullCountry, counties.get(counties.size() - 1));
	}

	/**
	 * Test Count of Rows read from the Real Country File.
	 * 
	 * @throws FileNotFoundException
	 */
	// @Ignore("Test has external dependencies")
	@Test
	public void parseCountCountryFromRealCountryFile()
			throws FileNotFoundException {
		assertEquals(260,
				countryLoader.loadFile("../data/iso_country_codes.txt").size());
	}

}
