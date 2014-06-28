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

/**
 * @author gregg
 * 
 */
public class TopLevelDomain {

	private String topLevelDomain;

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof TopLevelDomain) {
			TopLevelDomain compareTLD = (TopLevelDomain) anObject;
			if (sameTLD(compareTLD)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Check to see if the Top Level Domains are the same
	 * 
	 * @param compareTLD
	 * @return
	 */
	private boolean sameTLD(TopLevelDomain compareTLD) {
		return (this.topLevelDomain == null && compareTLD.getTopLevelDomain() == null)
				|| (this.topLevelDomain != null && this.topLevelDomain
						.equals(compareTLD.getTopLevelDomain()));
	}

	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

	/**
	 * @return the Top Level Domain
	 */
	public String getTopLevelDomain() {
		return this.topLevelDomain;
	}

	/**
	 * @param tld
	 *            the topLevelDomain to set
	 */
	public void setTopLevelDomain(String tld) {
		this.topLevelDomain = tld;
	}

}
