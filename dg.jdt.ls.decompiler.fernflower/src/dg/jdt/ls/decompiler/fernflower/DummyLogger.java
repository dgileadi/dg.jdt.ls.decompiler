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

import org.eclipse.jdt.ls.core.internal.JavaLanguageServerPlugin;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

class DummyLogger extends IFernflowerLogger {

	@Override
	public void writeMessage(String message, Severity severity) {
		switch (severity) {
		case TRACE:
		case INFO:
		case WARN:
			JavaLanguageServerPlugin.logInfo(message);
			break;
		case ERROR:
			JavaLanguageServerPlugin.logError(message);
			break;
		}
	}

	@Override
	public void writeMessage(String message, Severity severity, Throwable t) {
		JavaLanguageServerPlugin.logException(message, t);
	}

}