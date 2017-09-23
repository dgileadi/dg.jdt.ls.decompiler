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

import java.io.IOException;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

class BytecodeProvider implements IBytecodeProvider {

	private final IClassFile classFile;

	public BytecodeProvider(IClassFile classFile) {
		this.classFile = classFile;
	}

	@Override
	public byte[] getBytecode(String externalPath, String internalPath) throws IOException {
		try {
			return classFile.getBytes();
		} catch (JavaModelException e) {
			throw new IOException();
		}
	}
	
}