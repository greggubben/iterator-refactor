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
public class Country {

	private String countryName;
	private String iso2Alpha;
	private String iso3Alpha;

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof Country) {
			Country compareCountry = (Country) anObject;
			if (sameCountryName(compareCountry)
					&& sameIso2Alpha(compareCountry)
					&& sameIso3Alpha(compareCountry)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Check to see if the Country Names are the same
	 * 
	 * @param compareCountry
	 * @return
	 */
	private boolean sameCountryName(Country compareCountry) {
		return (this.countryName == null && compareCountry.getCountryName() == null)
				|| (this.countryName != null && this.countryName
						.equals(compareCountry.getCountryName()));
	}

	/**
	 * Check to see if the Country ISO 2-Alpha Codes are the same
	 * 
	 * @param compareCountry
	 * @return
	 */
	private boolean sameIso2Alpha(Country compareCountry) {
		return (this.iso2Alpha == null && compareCountry.getIso2Alpha() == null)
				|| (this.iso2Alpha != null && this.iso2Alpha
						.equals(compareCountry.getIso2Alpha()));
	}

	/**
	 * Check to see if the Country ISO 3-Alpha Codes are the same
	 * 
	 * @param compareCountry
	 * @return
	 */
	private boolean sameIso3Alpha(Country compareCountry) {
		return (this.iso3Alpha == null && compareCountry.getIso3Alpha() == null)
				|| (this.iso3Alpha != null && this.iso3Alpha
						.equals(compareCountry.getIso3Alpha()));
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
	 * @return the iso2Alpha
	 */
	public String getIso2Alpha() {
		return iso2Alpha;
	}

	/**
	 * @return the iso3Alpha
	 */
	public String getIso3Alpha() {
		return iso3Alpha;
	}

	/**
	 * @param iso3Alpha
	 *            the iso3Alpha to set
	 */
	public void setIso3Alpha(String iso3Alpha) {
		this.iso3Alpha = iso3Alpha;
	}

	/**
	 * @param iso2Alpha
	 *            the iso2Alpha to set
	 */
	public void setIso2Alpha(String iso2Alpha) {
		this.iso2Alpha = iso2Alpha;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String toString() {
		return "Country Name='" + this.countryName + "'; ISO 2-Alpha='"
				+ this.iso2Alpha + "'; ISO 3-Alpha='" + this.iso3Alpha + "'";
	}
}
