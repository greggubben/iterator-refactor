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

package com.gregg.iteratorrefactor;

/**
 * @author gregg
 * 
 */
public class Currency {

	private String countryName;
	private String currencyName;
	private String currencyCode;

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof Currency) {
			Currency compareCurrency = (Currency) anObject;
			if (sameCountryName(compareCurrency)
					&& sameCurrencyName(compareCurrency)
					&& sameCurrencyCode(compareCurrency)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Check to see if the Country Names are the same
	 * 
	 * @param compareCurrency
	 * @return
	 */
	private boolean sameCountryName(Currency compareCurrency) {
		return (this.countryName == null && compareCurrency.getCountryName() == null)
				|| (this.countryName != null && this.countryName
						.equals(compareCurrency.getCountryName()));
	}

	/**
	 * Check to see if the Currency Names are the same
	 * 
	 * @param compareCurrency
	 * @return
	 */
	private boolean sameCurrencyName(Currency compareCurrency) {
		return (this.currencyName == null && compareCurrency.getCurrencyName() == null)
				|| (this.currencyName != null && this.currencyName
						.equals(compareCurrency.getCurrencyName()));
	}

	/**
	 * Check to see if the Currency Codes are the same
	 * 
	 * @param compareCurrency
	 * @return
	 */
	private boolean sameCurrencyCode(Currency compareCurrency) {
		return (this.currencyCode == null && compareCurrency.getCurrencyCode() == null)
				|| (this.currencyCode != null && this.currencyCode
						.equals(compareCurrency.getCurrencyCode()));
	}

	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the currencyName
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * @param currencyName
	 *            the currencyName to set
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode
	 *            the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String toString() {
		return "Country Name='" + this.countryName + "'; Currency Name='"
				+ this.currencyName + "'; Currency Code='" + this.currencyCode
				+ "'";
	}

}
