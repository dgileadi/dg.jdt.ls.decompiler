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

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;

public class Configuration {
	public final Map<String, Object> options;

	public Configuration(Map<String, Object> configuration) {
		options = new HashMap<>();
		convertBooleans(configuration);
		populateOptions(configuration);
	}

	private void populateOptions(Map<String, Object> configuration) {
		options.put(IFernflowerPreferences.REMOVE_SYNTHETIC, "1");
		options.put(IFernflowerPreferences.DECOMPILE_GENERIC_SIGNATURES, "1");
		options.put(IFernflowerPreferences.DECOMPILE_INNER, "1");
		options.put(IFernflowerPreferences.DECOMPILE_ENUM, "1");
		options.put(IFernflowerPreferences.LOG_LEVEL, IFernflowerLogger.Severity.ERROR.name());
		options.put(IFernflowerPreferences.ASCII_STRING_CHARACTERS, "1");
		if (configuration != null) {
			options.putAll(configuration);
		}
	}

	private void convertBooleans(Map<String, Object> configuration) {
		if (configuration == null) {
			return;
		}
		for (String key : configuration.keySet()) {
			if (configuration.get(key) instanceof Boolean) {
				String value = ((Boolean) configuration.get(key)).booleanValue() ? "1" : "0";
				configuration.put(key, value);
			}
		}
	}
}
