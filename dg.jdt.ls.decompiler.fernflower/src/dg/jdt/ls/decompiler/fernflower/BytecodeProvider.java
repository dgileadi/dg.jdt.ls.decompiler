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
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ls.core.internal.JDTUtils;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

class BytecodeProvider implements IBytecodeProvider {

	private URI uri;
	private byte[] bytecode;

	public BytecodeProvider(URI uri) {
		this.uri = uri;
	}

	public BytecodeProvider(IClassFile classFile) {
		try {
			bytecode = loadClassFileBytes(classFile);
		} catch (IOException e) {}
	}

	@Override
	public byte[] getBytecode(String externalPath, String internalPath) throws IOException {
		if (bytecode != null) {
			return bytecode;
		}
		bytecode = loadClassFileBytes(JDTUtils.resolveClassFile(uri));
		if (bytecode == null) {
			bytecode = loadBytecode(uri);
		}
		return bytecode;
	}

	private byte[] loadBytecode(URI uri) throws IOException {
		if (uri == null) {
			return null;
		}
		Path path = Paths.get(uri);
		return Files.readAllBytes(path);
	}

	private byte[] loadClassFileBytes(IClassFile classFile) throws IOException {
		if (classFile == null) {
			return null;
		}
		try {
			return classFile.getBytes();
		} catch (JavaModelException e) {
			throw new IOException(e);
		}
	}
}