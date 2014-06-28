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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gregg.iteratorrefactor.domain.CountryTest;
import com.gregg.iteratorrefactor.domain.CurrencyTest;
import com.gregg.iteratorrefactor.domain.TopLevelDomainTest;
import com.gregg.iteratorrefactor.loader.CountryLoaderTest;
import com.gregg.iteratorrefactor.loader.CurrencyLoaderTest;
import com.gregg.iteratorrefactor.loader.TopLevelDomainLoaderTest;
import com.gregg.iteratorrefactor.loader.util.FileLoaderTest;

@RunWith(Suite.class)
@SuiteClasses({ CountryTest.class, CurrencyTest.class,
		TopLevelDomainTest.class, FileLoaderTest.class,
		CountryLoaderTest.class, CurrencyLoaderTest.class,
		TopLevelDomainLoaderTest.class })
public class AllTests {

}
