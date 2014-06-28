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
package com.gregg.iteratorrefactor.loader.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gregg.iteratorrefactor.loader.util.FileLoader;

public class FileLoaderTest {

	private static final String EMPTY_ROW = "";
	// Column Positions
	private static final int FIRST_COLUMN_BEGIN = 0;
	private static final int FIRST_COLUMN_END = 56;
	private static final int MIDDLE_COLUMN_BEGIN = 56;
	private static final int MIDDLE_COLUMN_END = 68;
	private static final int LAST_COLUMN_BEGIN = 68;
	private static final int LAST_COLUMN_END = 79;

	// Full Row Constants
	private static final String FULL_ROW = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCD123456789012!@#$%^&*()+";
	private static final String FULL_FIRST_COLUMN = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
	private static final String FULL_MIDDLE_COLUMN = "123456789012";
	private static final String FULL_LAST_COLUMN = "!@#$%^&*()+";

	// Normal Row Constants
	private static final String NORMAL_ROW = "ARUBA                                                   AW          ABW        ";
	private static final String NORMAL_FIRST_COLUMN = "ARUBA";

	FileLoader fileLoader;

	@Before
	public void setUp() throws Exception {
		fileLoader = new FileLoader();
	}

	/**
	 * Test the Column Parse routine with an Empty Row. Should get an Empty
	 * String back.
	 */
	@Test
	public void parseColumnFromEmptyRow() {
		assertEquals("", fileLoader.parseColumn(EMPTY_ROW, FIRST_COLUMN_BEGIN,
				FIRST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine for a column that starts at the first
	 * character (i.e. the First Column) with a Row longer than the first
	 * column. This test makes sure then entire column is parsed with no extras.
	 * Should get All values entered for the First Column.
	 */
	@Test
	public void parseFirstColumnFromFullRow() {
		assertEquals(FULL_FIRST_COLUMN, fileLoader.parseColumn(FULL_ROW,
				FIRST_COLUMN_BEGIN, FIRST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine for a column that starts after the first
	 * character (i.e. the Middle Column) with a Row longer than the middle
	 * column. This test makes sure then entire column is parsed with no extras.
	 * Should get All values entered for the Middle Column.
	 */
	@Test
	public void parseMiddleColumnFromFullRow() {
		assertEquals(FULL_MIDDLE_COLUMN, fileLoader.parseColumn(FULL_ROW,
				MIDDLE_COLUMN_BEGIN, MIDDLE_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine for a column that ends on the last
	 * characted of the row (i.e. Last Column) and starts after the first
	 * character. This test makes sure then entire column is parsed with no
	 * extras. Should get All values entered for the Last Column.
	 */
	@Test
	public void parseLastColumnFromFullRow() {
		assertEquals(FULL_LAST_COLUMN, fileLoader.parseColumn(FULL_ROW,
				LAST_COLUMN_BEGIN, LAST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine for a column that begins on the first
	 * character of the row and end on the last column of the Row (i.e. Full
	 * Row). This test makes sure then entire column is parsed with nothing
	 * missing. Should get All values entered for the Row.
	 */
	@Test
	public void parseColumnIsFullRow() {
		assertEquals(FULL_ROW, fileLoader.parseColumn(FULL_ROW,
				FIRST_COLUMN_BEGIN, LAST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine to clean up trailing spaces in a parsed
	 * column.
	 */
	@Test
	public void parseColumnHasTrailingSpacesCleanedUp() {
		assertEquals(NORMAL_FIRST_COLUMN, fileLoader.parseColumn(NORMAL_ROW,
				FIRST_COLUMN_BEGIN, FIRST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine to handle parsing a row that ends before
	 * the end of the column is supposed to end.
	 */
	@Test
	public void parseColumnEndsAfterRowEnds() {
		assertEquals(NORMAL_FIRST_COLUMN, fileLoader.parseColumn(
				NORMAL_FIRST_COLUMN, FIRST_COLUMN_BEGIN, FIRST_COLUMN_END));
	}

	/**
	 * Test the Column Parse routine to handle parsing a row that ends before
	 * the start of the column is supposed to begin.
	 */
	@Test
	public void parseColumnStartsAfterRowEnds() {
		assertEquals("", fileLoader.parseColumn(NORMAL_FIRST_COLUMN,
				LAST_COLUMN_BEGIN, LAST_COLUMN_END));
	}

}
