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

import com.gregg.iteratorrefactor.domain.Currency;

/**
 * @author gregg
 * 
 */
public class CurrencyTest {

	Currency currency;
	Currency currencyCompare;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currency = new Currency();
		currencyCompare = new Currency();
	}

	/**
	 * Null comparison should not be equal.
	 */
	@Test
	public void equalsNullReturnsFalse() {
		assertFalse(currency.equals(null));
	}

	/**
	 * A Different Object should not be equal.
	 */
	@Test
	public void equalsDiffObjectReturnsFalse() {
		assertFalse(currency.equals(new String()));
	}

	/**
	 * 2 Empty Currencies should be Equal.
	 */
	@Test
	public void equalsSameEmptyObjectsReturnsTrue() {
		assertTrue(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Same Country Names only should be Equal.
	 */
	@Test
	public void equalsSameCountryNameOnlyReturnsTrue() {
		currency.setCountryName("ABC");
		currencyCompare.setCountryName("ABC");
		assertTrue(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Country Names should not be Equal.
	 */
	@Test
	public void equalsDiffCountryNameOnlyReturnsFalse() {
		currency.setCountryName("ABC");
		currencyCompare.setCountryName("DEF");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Same Currency Name only should be Equal.
	 */
	@Test
	public void equalsSameCurrencyNameOnlyReturnsTrue() {
		currency.setCurrencyName("GH");
		currencyCompare.setCurrencyName("GH");
		assertTrue(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Currency Name only should not be Equal.
	 */
	@Test
	public void equalsDiffCurrencyNameOnlyReturnsFalse() {
		currency.setCurrencyName("GH");
		currencyCompare.setCurrencyName("IJ");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Same Currency Code only should be Equal.
	 */
	@Test
	public void equalsSameCurrencyCodeOnlyReturnsTrue() {
		currency.setCurrencyCode("KLM");
		currencyCompare.setCurrencyCode("KLM");
		assertTrue(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Currency Code only should not be Equal.
	 */
	@Test
	public void equalsDiffCurrencyCodeOnlyReturnsFalse() {
		currency.setCurrencyCode("KLM");
		currencyCompare.setCurrencyCode("NOP");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Same Currency with All Info should be Equal.
	 */
	@Test
	public void equalsSameCurrencyAllReturnsTrue() {
		currency.setCountryName("ABCDEF");
		currency.setCurrencyName("HI");
		currency.setCurrencyCode("JKL");
		currencyCompare.setCountryName("ABCDEF");
		currencyCompare.setCurrencyName("HI");
		currencyCompare.setCurrencyCode("JKL");
		assertTrue(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Currency with All Info should not be Equal.
	 */
	@Test
	public void equalsDiffCurrencyAllReturnsFalse() {
		currency.setCountryName("ABCDEF");
		currency.setCurrencyName("HI");
		currency.setCurrencyCode("JKL");
		currencyCompare.setCountryName("MNOPQR");
		currencyCompare.setCurrencyName("ST");
		currencyCompare.setCurrencyCode("UVW");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Country Name with All Info should not be Equal.
	 */
	@Test
	public void equalsDiffCurrencyNameAllReturnsFalse() {
		currency.setCountryName("ABCDEF");
		currency.setCurrencyName("HI");
		currency.setCurrencyCode("JKL");
		currencyCompare.setCountryName("MNOPQR");
		currencyCompare.setCurrencyName("HI");
		currencyCompare.setCurrencyCode("JKL");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Country ISO 2-Alpha with All Info should not be
	 * Equal.
	 */
	@Test
	public void equalsDiffCurrencyIso2AlphaAllReturnsFalse() {
		currency.setCountryName("ABCDEF");
		currency.setCurrencyName("HI");
		currency.setCurrencyCode("JKL");
		currencyCompare.setCountryName("ABCDEF");
		currencyCompare.setCurrencyName("MN");
		currencyCompare.setCurrencyCode("JKL");
		assertFalse(currency.equals(currencyCompare));
	}

	/**
	 * Currencies with Different Country ISO 3-Alpha with All Info should not be
	 * Equal.
	 */
	@Test
	public void equalsDiffCurrencyIso3AlphaAllReturnsFalse() {
		currency.setCountryName("ABCDEF");
		currency.setCurrencyName("HI");
		currency.setCurrencyCode("JKL");
		currencyCompare.setCountryName("ABCDEF");
		currencyCompare.setCurrencyName("HI");
		currencyCompare.setCurrencyCode("MNO");
		assertFalse(currency.equals(currencyCompare));
	}

}
