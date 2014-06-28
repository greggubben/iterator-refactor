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
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
	private static final String NORMAL_MIDDLE_COLUMN = "AW";
	private static final String NORMAL_LAST_COLUMN = "ABW";

	TestLoader fileLoader;

	@Before
	public void setUp() throws Exception {
		fileLoader = new TestLoader();
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
	 * character of the row (i.e. Last Column) and starts after the first
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

	/**
	 * Test when a Null Row is parsed the returned Test Structure is null.
	 */
	@Test
	public void parseTestStructureFromNull() {
		assertNull(fileLoader.parseRow(null));
	}

	/**
	 * Test when an Empty Row is parsed the returned Test Structure is null.
	 */
	@Test
	public void parseTestStructureFromEmptyRow() {
		assertNull(fileLoader.parseRow(EMPTY_ROW));
	}

	/**
	 * Test when a Single Row is read from a Single Row File.
	 */
	@Test
	public void parseTestStructureFromSingleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		TestStructure fullTestStructure = new TestStructure();
		fullTestStructure.setFirstColumn(NORMAL_FIRST_COLUMN);
		fullTestStructure.setMiddleColumn(NORMAL_MIDDLE_COLUMN);
		fullTestStructure.setLastColumn(NORMAL_LAST_COLUMN);
		assertEquals(fullTestStructure, fileLoader.parseFile(br).get(0));
	}

	/**
	 * Test Count of Rows read from a Multi Row File.
	 */
	@Test
	public void parseCountTestStructureFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		TestStructure fullTestStructure = new TestStructure();
		fullTestStructure.setFirstColumn(NORMAL_FIRST_COLUMN);
		fullTestStructure.setMiddleColumn(NORMAL_MIDDLE_COLUMN);
		fullTestStructure.setLastColumn(NORMAL_LAST_COLUMN);
		assertEquals(3, fileLoader.parseFile(br).size());
	}

	/**
	 * Test when First Row is read from a Multi Row File.
	 */
	@Test
	public void parseFirstTestStructureFromMultipleRowFile() {
		StringBuffer sb = new StringBuffer();
		sb.append(NORMAL_ROW);
		sb.append("\n");
		sb.append(FULL_ROW);
		sb.append("\n");
		BufferedReader br = new BufferedReader(new StringReader(sb.toString()));
		TestStructure fullTestStructure = new TestStructure();
		fullTestStructure.setFirstColumn(NORMAL_FIRST_COLUMN);
		fullTestStructure.setMiddleColumn(NORMAL_MIDDLE_COLUMN);
		fullTestStructure.setLastColumn(NORMAL_LAST_COLUMN);
		assertEquals(fullTestStructure, fileLoader.parseFile(br).get(0));
	}

	/**
	 * Test when Last Row is read from a Multi Row File.
	 */
	@Test
	public void parseLastTestStructureFromMultipleRowFile() {
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
		TestStructure fullTestStructure = new TestStructure();
		fullTestStructure.setFirstColumn(NORMAL_FIRST_COLUMN);
		fullTestStructure.setMiddleColumn(NORMAL_MIDDLE_COLUMN);
		fullTestStructure.setLastColumn(NORMAL_LAST_COLUMN);
		ArrayList<TestStructure> testStructures = fileLoader.parseFile(br);
		assertEquals(fullTestStructure,
				testStructures.get(testStructures.size() - 1));
	}

	/**
	 * Test Count of Rows read from the Real Country File.
	 * 
	 * @throws FileNotFoundException
	 */
	// @Ignore("Test has external dependencies")
	@Test
	public void parseCountTestStructureFromRealFile()
			throws FileNotFoundException {
		assertEquals(260, fileLoader.loadFile("../data/iso_country_codes.txt")
				.size());
	}

	/**
	 * Test class for testing the File Loader since it is an abstract.
	 * 
	 */
	public class TestLoader extends FileLoader<TestStructure> {

		/**
		 * From a String of Fixed Position characters parse each field and
		 * populate a Test Structure.
		 * 
		 * @param row
		 *            Fixed Position Row String read from file
		 * @return Test Structure Object (null if @row is empty)
		 */
		protected TestStructure parseRow(String row) {
			TestStructure country = null;
			if (row != null && row.length() != 0) {
				country = new TestStructure();
				country.setFirstColumn(parseFirstColumn(row));
				country.setMiddleColumn(parseMiddleColumn(row));
				country.setLastColumn(parseLastColumn(row));
			}
			return country;
		}

		/**
		 * From a String containing the Row that was read parse the First
		 * Column. It is expected to be fixed position row with trailing
		 * whitespace. The resulting First Column will be trimmed.
		 * 
		 * @param row
		 *            Fixed Position Row String read from file
		 * @return Trimmed First Column as String
		 */
		protected String parseFirstColumn(String row) {
			return parseColumn(row, FIRST_COLUMN_BEGIN, FIRST_COLUMN_END);
		}

		/**
		 * From a String containing the Row that was read parse the Middle
		 * Column. It is expected to be fixed position row with trailing
		 * whitespace. The resulting Middle Column will be trimmed.
		 * 
		 * @param row
		 *            Fixed Position Row String read from file
		 * @return Trimmed Middle Column as String
		 */
		protected String parseMiddleColumn(String row) {
			return parseColumn(row, MIDDLE_COLUMN_BEGIN, MIDDLE_COLUMN_END);
		}

		/**
		 * From a String containing the Row that was read parse the Last Column.
		 * It is expected to be fixed position row with trailing whitespace. The
		 * resulting Last Column will be trimmed.
		 * 
		 * @param row
		 *            Fixed Position Row String read from file
		 * @return Trimmed Last Column as String
		 */
		protected String parseLastColumn(String row) {
			return parseColumn(row, LAST_COLUMN_BEGIN, LAST_COLUMN_END);
		}

	}

	/**
	 * Test Structure for testing the abstract File Loader class.
	 * 
	 */
	public class TestStructure {

		private String firstColumn;
		private String middleColumn;
		private String lastColumn;

		public boolean equals(Object anObject) {
			if (this == anObject) {
				return true;
			}
			if (anObject instanceof TestStructure) {
				TestStructure compareTestStructure = (TestStructure) anObject;
				if (sameFirstColumn(compareTestStructure)
						&& sameMiddleColumn(compareTestStructure)
						&& sameLastColumn(compareTestStructure)) {
					return true;
				}
			}
			return false;

		}

		/**
		 * Check to see if the First Columns are the same
		 * 
		 * @param compareTestStructure
		 * @return
		 */
		private boolean sameFirstColumn(TestStructure compareTestStructure) {
			return (this.firstColumn == null && compareTestStructure
					.getFirstColumn() == null)
					|| (this.firstColumn != null && this.firstColumn
							.equals(compareTestStructure.getFirstColumn()));
		}

		/**
		 * Check to see if the Middle Columns are the same
		 * 
		 * @param compareTestStructure
		 * @return
		 */
		private boolean sameMiddleColumn(TestStructure compareTestStructure) {
			return (this.middleColumn == null && compareTestStructure
					.getMiddleColumn() == null)
					|| (this.middleColumn != null && this.middleColumn
							.equals(compareTestStructure.getMiddleColumn()));
		}

		/**
		 * Check to see if the Last Columns are the same
		 * 
		 * @param compareTestStructure
		 * @return
		 */
		private boolean sameLastColumn(TestStructure compareTestStructure) {
			return (this.lastColumn == null && compareTestStructure
					.getLastColumn() == null)
					|| (this.lastColumn != null && this.lastColumn
							.equals(compareTestStructure.getLastColumn()));
		}

		public int hashCode() {
			assert false : "hashCode not designed";
			return 42; // any arbitrary constant will do
		}

		/**
		 * @return the firstColumn
		 */
		public String getFirstColumn() {
			return firstColumn;
		}

		/**
		 * @return the middleColumn
		 */
		public String getMiddleColumn() {
			return middleColumn;
		}

		/**
		 * @return the lastColumn
		 */
		public String getLastColumn() {
			return lastColumn;
		}

		/**
		 * @param firstColumn
		 *            the firstColumn to set
		 */
		public void setFirstColumn(String firstCol) {
			this.firstColumn = firstCol;
		}

		/**
		 * @param middleColumn
		 *            the middleColumn to set
		 */
		public void setMiddleColumn(String middleCol) {
			this.middleColumn = middleCol;
		}

		/**
		 * @param lastColumn
		 *            the lastColumn to set
		 */
		public void setLastColumn(String lastCol) {
			this.lastColumn = lastCol;
		}

		public String toString() {
			return "First Column='" + this.firstColumn + "'; Second Column='"
					+ this.middleColumn + "'; Last Column='" + this.lastColumn
					+ "'";
		}
	}

}
