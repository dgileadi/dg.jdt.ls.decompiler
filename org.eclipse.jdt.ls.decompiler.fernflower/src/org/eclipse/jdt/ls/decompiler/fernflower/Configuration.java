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
package org.eclipse.jdt.ls.decompiler.fernflower;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;

public class Configuration {
	public final Map<String, Object> options;

	public Configuration(String configuration) {
		options = new HashMap<>();
		populateDefaultOptions();
		parse(configuration);
	}

	private void populateDefaultOptions() {
		options.put(IFernflowerPreferences.REMOVE_SYNTHETIC, "1");
		options.put(IFernflowerPreferences.DECOMPILE_GENERIC_SIGNATURES, "1");
		options.put(IFernflowerPreferences.DECOMPILE_INNER, "1");
		options.put(IFernflowerPreferences.DECOMPILE_ENUM, "1");
		options.put(IFernflowerPreferences.LOG_LEVEL, IFernflowerLogger.Severity.ERROR.name());
		options.put(IFernflowerPreferences.ASCII_STRING_CHARACTERS, "1");
	}

	public void parse(String configuration) {
		if (configuration == null) {
			return;
		}
		String[] args = configuration.split("\\s+");
		for (String arg : args) {
			if (arg.length() > 5 && arg.charAt(0) == '-' && arg.charAt(4) == '=') {
				String value = arg.substring(5);
				if ("true".equalsIgnoreCase(value)) {
					value = "1";
				} else if ("false".equalsIgnoreCase(value)) {
					value = "0";
				}
				options.put(arg.substring(1, 4), value);
			}
		}
	}
}
