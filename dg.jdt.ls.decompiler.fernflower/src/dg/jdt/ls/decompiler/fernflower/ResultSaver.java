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

import java.util.jar.Manifest;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;

public class ResultSaver implements IResultSaver {

	private String content;

	@Override
	public void saveFolder(String arg0) {
		// Not implemented
	}

	@Override
	public void copyFile(String arg0, String arg1, String arg2) {
		// Not implemented
	}

	@Override
	public void saveClassFile(String filename, String qualifiedName, String entryName, String content,
			int[] originalLinesMapping) {
		this.content = content;
	}

	@Override
	public void createArchive(String arg0, String arg1, Manifest arg2) {
		// Not implemented
	}

	@Override
	public void saveDirEntry(String arg0, String arg1, String arg2) {
		// Not implemented
	}

	@Override
	public void copyEntry(String arg0, String arg1, String arg2, String arg3) {
		// Not implemented
	}

	@Override
	public void saveClassEntry(String archivePath, String filename, String qualifiedName, String entryName,
			String content) {
		// Not implemented
	}

	@Override
	public void closeArchive(String arg0, String arg1) {
		// Not implemented
	}

	public String getContent() {
		return content;
	}

}
