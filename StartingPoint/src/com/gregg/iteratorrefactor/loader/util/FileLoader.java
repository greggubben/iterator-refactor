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


public class FileLoader {

	public FileLoader() {
		super();
	}

	/**
	 * Load the Country File into a Memory Array
	 * 
	 * @param filename
	 * @return ArrayList of Top Level Domains
	 * @throws FileNotFoundException
	 */
	/*
	 * public ArrayList<E> loadFile(String filename) throws
	 * FileNotFoundException { BufferedReader br = new BufferedReader(new
	 * FileReader(filename));
	 * 
	 * return parseFile(br); }
	 */
	/**
	 * Parse row by row through a file.
	 * 
	 * @param br
	 * @return
	 */
	/*
	 * protected ArrayList<E> parseFile(BufferedReader br) { ArrayList<E>
	 * topLevelDomains = new ArrayList<E>(); String row = null; try { while
	 * ((row = br.readLine()) != null) { topLevelDomains.add(parseRow(row)); } }
	 * catch (IOException ioe) { // Print the stack trace and return what we
	 * have // TODO: This needs better error handling ioe.printStackTrace(); }
	 * return topLevelDomains; }
	 * 
	 * protected abstract E parseRow(String row);
	 */
	/**
	 * From a String containing the Row that was read parse the Column specified
	 * between begColIndex and endColIndex. It is expected to be fixed position
	 * row with trailing whitespace. The resulting Column will be trimmed.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @param begColIndex
	 *            The Beginning Position of the Column
	 * @param endColIndex
	 *            The Ending Position of the Column
	 * @return Trimmed Column as String
	 */
	protected String parseColumn(String row, int beginColIndex, int endColIndex) {
		int begIndex = beginColIndex;
		int endIndex = endColIndex;
		int rowlen = row.length();
		if (rowlen < begIndex) {
			// Row ends before Column starts - Column does not exist
			return "";
		}
		if (rowlen < endIndex) {
			// Row ends before Column Ends - shorten end of Column
			endIndex = rowlen;
		}
		return row.substring(begIndex, endIndex).trim();
	}

}