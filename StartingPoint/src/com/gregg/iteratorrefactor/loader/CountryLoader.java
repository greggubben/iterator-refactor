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

import com.gregg.iteratorrefactor.domain.Country;
import com.gregg.iteratorrefactor.loader.util.FileLoader;

/**
 * @author Gregg Ubben
 * 
 */
public class CountryLoader extends FileLoader<Country> {

	private static final int COUNTRY_BEGIN_INDEX = 0;
	private static final int COUNTRY_END_INDEX = 56;
	private static final int ISO2ALPHA_BEGIN_INDEX = 56;
	private static final int ISO2ALPHA_END_INDEX = 68;
	private static final int ISO3ALPHA_BEGIN_INDEX = 68;
	private static final int ISO3ALPHA_END_INDEX = 79;

	/**
	 * From a String of Fixed Position characters parse each field and populate
	 * a Country Structure.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Country Structure Object (null if @row is empty)
	 */
	protected Country parseRow(String row) {
		Country country = null;
		if (row != null && row.length() != 0) {
			country = new Country();
			country.setCountryName(parseCountryName(row));
			country.setIso2Alpha(parseIso2Alpha(row));
			country.setIso3Alpha(parseIso3Alpha(row));
		}
		return country;
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
	 * From a String containing the Row that was read parse the Country ISO
	 * 2-alpha Code. It is expected to be fixed position row with trailing
	 * whitespace. The resulting Country ISO 2-alpha Code will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Country ISO 2-alpha Code as String
	 */
	protected String parseIso2Alpha(String row) {
		return parseColumn(row, ISO2ALPHA_BEGIN_INDEX, ISO2ALPHA_END_INDEX);
	}

	/**
	 * From a String containing the Row that was read parse the Country ISO
	 * 3-alpha Code. It is expected to be fixed position row with trailing
	 * whitespace. The resulting Country ISO 3-alpha Code will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Trimmed Country ISO 3-alpha Code as String
	 */
	protected String parseIso3Alpha(String row) {
		return parseColumn(row, ISO3ALPHA_BEGIN_INDEX, ISO3ALPHA_END_INDEX);
	}

}
