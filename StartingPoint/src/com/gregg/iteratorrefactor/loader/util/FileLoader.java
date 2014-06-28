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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileLoader<E> {

	public FileLoader() {
		super();
	}

	/**
	 * Load the Data File into a Memory Array
	 * 
	 * @param filename
	 * @return ArrayList of Data
	 * @throws FileNotFoundException
	 */

	public ArrayList<E> loadFile(String filename) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filename));

		return parseFile(br);
	}

	/**
	 * Parse row by row through a file.
	 * 
	 * @param br
	 * @return
	 */
	protected ArrayList<E> parseFile(BufferedReader br) {
		ArrayList<E> records = new ArrayList<E>();
		String row = null;
		try {
			while ((row = br.readLine()) != null) {
				records.add(parseRow(row));
			}
		} catch (IOException ioe) {
			// Print the stack trace and return what we have
			// TODO: This needs better error handling
			ioe.printStackTrace();
		}
		return records;
	}

	/**
	 * From a String of Fixed Position characters parse each field and populate
	 * a Data Structure.
	 * 
	 * @param row
	 *            Fixed Position Row String read from file
	 * @return Data Structure Object (null if @row is empty)
	 */
	protected abstract E parseRow(String row);

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