/*******************************************************************************
 * Copyright (c) 2017 David Gileadi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Gileadi - initial API and implementation
 *******************************************************************************/
package dg.jdt.ls.decompiler.fernflower;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dg.jdt.ls.decompiler.fernflower.Configuration;

public class TestConfiguration {

	@Test
	public void testDefaultConfiguration() {
		Map<String, Object> expectedOptions = createExpectedOptions();
		Configuration config = new Configuration(null);
		assertEquals(expectedOptions, config.options);
	}

	@Test
	public void testParseConfiguration() {
		String configuration = "-inn=true -mpm=hello -fdi=1 -ind=false   -longer=nope -sh=ort";
		Map<String, Object> expectedOptions = createExpectedOptions();
		expectedOptions.put("inn", "1");
		expectedOptions.put("mpm", "hello");
		expectedOptions.put("fdi", "1");
		expectedOptions.put("ind", "0");

		Configuration config = new Configuration(configuration);
		assertEquals(expectedOptions, config.options);
	}

	private Map<String, Object> createExpectedOptions() {
		Map<String, Object> options = new HashMap<>();
		options.put("asc", "1");
		options.put("den", "1");
		options.put("dgs", "1");
		options.put("din", "1");
		options.put("log", "ERROR");
		options.put("rsy", "1");
		return options;
	}
}
