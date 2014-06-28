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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gregg.iteratorrefactor.domain.Currency;
import com.gregg.iteratorrefactor.loader.util.FileLoader;

/**
 * @author gregg
 * 
 */
public class CurrencyLoader extends FileLoader {

	private static final int COUNTRY_BEGIN_INDEX = 0;
	private static final int COUNTRY_END_INDEX = 65;
	private static final int CURRENCY_BEGIN_INDEX = 65;
	private static final int CURRENCY_END_INDEX = 120;
	private static final int CURRENCY_CODE_BEGIN_INDEX = 120;
	private static final int CURRENCY_CODE_END_INDEX = 128;

	/**
	 * Load the Currency File into a Memory Array
	 * 
	 * @param filename
	 * @return ArrayList of Currencies
	 * @throws FileNotFoundException
	 */
	public ArrayList<Currency> loadFile(String filename)
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
	protected ArrayList<Currency> parseFile(BufferedReader br) {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		String row = null;
		try {
			while ((row = br.readLine()) != null) {
				currencies.add(parseRow(row));
			}
		} catch (IOException ioe) {
			// Print the stack trace and return what we have
			// TODO: This needs better error handling
			ioe.printStackTrace();
		}
		return currencies;
	}

	/**
	 * From a String of Fixed Position characters parse each field and populate
	 * a Country Structure.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Country Structure Object (null if @row is empty)
	 */
	protected Currency parseRow(String row) {
		Currency currency = null;
		if (row != null && row.length() != 0) {
			currency = new Currency();
			currency.setCountryName(parseCountryName(row));
			currency.setCurrencyName(parseCurrencyName(row));
			currency.setCurrencyCode(parseCurrencyCode(row));
		}
		return currency;
	}

	/**
	 * From a String containing the Row that was read parse the Country Name. It
	 * is expected to be fixed position row with trailing whitespace. The
	 * resulting Country Name will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Country Name as String
	 */
	protected String parseCountryName(String row) {
		return parseColumn(row, COUNTRY_BEGIN_INDEX, COUNTRY_END_INDEX);
	}

	/**
	 * From a String containing the Row that was read parse the Currency Name.
	 * It is expected to be fixed position row with trailing whitespace. The
	 * resulting Currency Name will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Currency Name as String
	 */
	protected String parseCurrencyName(String row) {
		return parseColumn(row, CURRENCY_BEGIN_INDEX, CURRENCY_END_INDEX);
	}

	/**
	 * From a String containing the Row that was read parse the Currency Code.
	 * It is expected to be fixed position row with trailing whitespace. The
	 * resulting Currency Code will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Currency Code as String
	 */
	protected String parseCurrencyCode(String row) {
		return parseColumn(row, CURRENCY_CODE_BEGIN_INDEX,
				CURRENCY_CODE_END_INDEX);
	}

}
