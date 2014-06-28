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

package com.gregg.iteratorrefactor.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gregg.iteratorrefactor.domain.Country;

/**
 * @author gregg
 * 
 */
public class CountryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Null comparison should not be equal.
	 */
	@Test
	public void equalsNullReturnsFalse() {
		Country country = new Country();
		assertFalse(country.equals(null));
	}

	/**
	 * A Different Object should not be equal.
	 */
	@Test
	public void equalsDiffObjectReturnsFalse() {
		Country country = new Country();
		assertFalse(country.equals(new String()));
	}

	/**
	 * 2 Empty Counties should be Equal.
	 */
	@Test
	public void equalsSameEmptyObjectsReturnsTrue() {
		Country country = new Country();
		Country countryCompare = new Country();
		assertTrue(country.equals(countryCompare));
	}

	/**
	 * Counties with Same Country Names only should be Equal.
	 */
	@Test
	public void equalsSameCountryNameOnlyReturnsTrue() {
		Country country = new Country();
		country.setCountryName("ABC");
		Country countryCompare = new Country();
		countryCompare.setCountryName("ABC");
		assertTrue(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country Names should not be Equal.
	 */
	@Test
	public void equalsDiffCountryNameOnlyReturnsFalse() {
		Country country = new Country();
		country.setCountryName("ABC");
		Country countryCompare = new Country();
		countryCompare.setCountryName("DEF");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Same Country ISO 2-alpha only should be Equal.
	 */
	@Test
	public void equalsSameIso2AlphaOnlyReturnsTrue() {
		Country country = new Country();
		country.setIso2Alpha("GH");
		Country countryCompare = new Country();
		countryCompare.setIso2Alpha("GH");
		assertTrue(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country ISO 2-alpha only should not be Equal.
	 */
	@Test
	public void equalsDiffIso2AlphaOnlyReturnsFalse() {
		Country country = new Country();
		country.setIso2Alpha("GH");
		Country countryCompare = new Country();
		countryCompare.setIso2Alpha("IJ");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Same Country ISO 3-alpha only should be Equal.
	 */
	@Test
	public void equalsSameIso3AlphaOnlyReturnsTrue() {
		Country country = new Country();
		country.setIso3Alpha("KLM");
		Country countryCompare = new Country();
		countryCompare.setIso3Alpha("KLM");
		assertTrue(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country ISO 3-alpha only should not be Equal.
	 */
	@Test
	public void equalsDiffIso3AlphaOnlyReturnsFalse() {
		Country country = new Country();
		country.setIso3Alpha("KLM");
		Country countryCompare = new Country();
		countryCompare.setIso3Alpha("NOP");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Same Country with All Info should be Equal.
	 */
	@Test
	public void equalsSameCountryAllReturnsTrue() {
		Country country = new Country();
		country.setCountryName("ABCDEF");
		country.setIso2Alpha("HI");
		country.setIso3Alpha("JKL");
		Country countryCompare = new Country();
		countryCompare.setCountryName("ABCDEF");
		countryCompare.setIso2Alpha("HI");
		countryCompare.setIso3Alpha("JKL");
		assertTrue(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country with All Info should not be Equal.
	 */
	@Test
	public void equalsDiffCountryAllReturnsFalse() {
		Country country = new Country();
		country.setCountryName("ABCDEF");
		country.setIso2Alpha("HI");
		country.setIso3Alpha("JKL");
		Country countryCompare = new Country();
		countryCompare.setCountryName("MNOPQR");
		countryCompare.setIso2Alpha("ST");
		countryCompare.setIso3Alpha("UVW");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country Name with All Info should not be Equal.
	 */
	@Test
	public void equalsDiffCountryNameAllReturnsFalse() {
		Country country = new Country();
		country.setCountryName("ABCDEF");
		country.setIso2Alpha("HI");
		country.setIso3Alpha("JKL");
		Country countryCompare = new Country();
		countryCompare.setCountryName("MNOPQR");
		countryCompare.setIso2Alpha("HI");
		countryCompare.setIso3Alpha("JKL");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country ISO 2-Alpha with All Info should not be
	 * Equal.
	 */
	@Test
	public void equalsDiffCountryIso2AlphaAllReturnsFalse() {
		Country country = new Country();
		country.setCountryName("ABCDEF");
		country.setIso2Alpha("HI");
		country.setIso3Alpha("JKL");
		Country countryCompare = new Country();
		countryCompare.setCountryName("ABCDEF");
		countryCompare.setIso2Alpha("MN");
		countryCompare.setIso3Alpha("JKL");
		assertFalse(country.equals(countryCompare));
	}

	/**
	 * Counties with Different Country ISO 3-Alpha with All Info should not be
	 * Equal.
	 */
	@Test
	public void equalsDiffCountryIso3AlphaAllReturnsFalse() {
		Country country = new Country();
		country.setCountryName("ABCDEF");
		country.setIso2Alpha("HI");
		country.setIso3Alpha("JKL");
		Country countryCompare = new Country();
		countryCompare.setCountryName("ABCDEF");
		countryCompare.setIso2Alpha("HI");
		countryCompare.setIso3Alpha("MNO");
		assertFalse(country.equals(countryCompare));
	}

}
