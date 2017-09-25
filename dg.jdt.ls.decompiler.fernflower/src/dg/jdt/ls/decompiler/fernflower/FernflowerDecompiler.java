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

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.ls.core.internal.IDecompiler;
import org.jetbrains.java.decompiler.main.decompiler.BaseDecompiler;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

public class FernflowerDecompiler implements IDecompiler {

	@Override
	public String decompile(IClassFile classFile, Map<String, Object> configuration, IProgressMonitor monitor)
			throws CoreException {
		BytecodeProvider provider = new BytecodeProvider(classFile);
		ResultSaver saver = new ResultSaver();
		IFernflowerLogger logger = new DummyLogger();
		Configuration config = new Configuration(configuration);

		BaseDecompiler fernflower = new BaseDecompiler(provider, saver, config.options, logger);
		try {
			fernflower.addSpace(new File("Fake.class"), true);
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, "dg.jdt.ls.decompiler.fernflower",
					"Error adding fake class to decompile", e));
		}
		fernflower.decompileContext();

		return saver.getContent();
	}
}
