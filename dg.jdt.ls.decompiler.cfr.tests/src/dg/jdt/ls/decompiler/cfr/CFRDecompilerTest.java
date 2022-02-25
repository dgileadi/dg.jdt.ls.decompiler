/*******************************************************************************
 * Copyright (c) 2022 David Gileadi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Gileadi - initial API and implementation
 *******************************************************************************/
package dg.jdt.ls.decompiler.cfr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ls.core.internal.preferences.Preferences;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CFRDecompilerTest {

	public static String HELLO_CLASS_PATH = "testclasses/HelloWorld.class";
	public static String EXPECTED_DECOMPILED_CODE = "package org.eclipse.jdt.ls.decompiler.fernflowe;\n" +
			"\n" +
			"public class org.eclipse.jdt.ls.decompiler.fernflowe.HelloWorld {\n" +
			"    public void sayHello() {\n" +
			"        java.lang.System.out.println(\"Hello world!\");\n" +
			"    }\n" +
			"}";

	protected IProgressMonitor monitor = new NullProgressMonitor();
	Path path;
	URI uri;

	@Mock
	IClassFile classFile;

	@Before
	public void getTestClassPath() throws IOException, JavaModelException {
		path = Paths.get(HELLO_CLASS_PATH);
		uri = path.toUri();
	}

	@Test
	public void testSetPreferences() throws Exception {
		CFRDecompiler decompiler = new CFRDecompiler();
		Map<String, Object> options = new HashMap<>();
		options.put("sugarboxing", true);
		Map<String, Object> configuration = new HashMap<>();
		configuration.put("java.decompiler.cfr", options);
		Preferences preferences = Preferences.createFrom(configuration);

		decompiler.setPreferences(preferences);

		Field field = CFRDecompiler.class.getDeclaredField("options");
		field.setAccessible(true);
		Map<String, Object> resultOptions = (Map<String, Object>) field.get(decompiler);

		assertEquals("true", resultOptions.get("sugarboxing"));
	}

	@Test
	public void testGetContent() throws CoreException {
		CFRDecompiler decompiler = new CFRDecompiler();
		String decompiled = decompiler.getContent(uri, monitor);
		assertTrue(decompiled, decompiled.endsWith(EXPECTED_DECOMPILED_CODE));
	}

	@Test
	public void testGetSource() throws CoreException, IOException {
		CFRDecompiler decompiler = new CFRDecompiler();
		byte[] bytes = Files.readAllBytes(path);
		when(classFile.getBytes()).thenReturn(bytes);
		when(classFile.getHandleIdentifier()).thenReturn("test identifier");
		String decompiled = decompiler.getSource(classFile, monitor);
		assertTrue(decompiled, decompiled.endsWith(EXPECTED_DECOMPILED_CODE));
	}
}
