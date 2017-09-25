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

public class TestConfiguration {

	@Test
	public void testDefaultConfiguration() {
		Map<String, Object> expectedOptions = createExpectedOptions();
		Configuration config = new Configuration(null);
		assertEquals(expectedOptions, config.options);
	}

	@Test
	public void testConvertBooleans() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("inn", true);

		Map<String, Object> expectedOptions = createExpectedOptions();
		expectedOptions.put("inn", "1");

		Configuration config = new Configuration(options);
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
