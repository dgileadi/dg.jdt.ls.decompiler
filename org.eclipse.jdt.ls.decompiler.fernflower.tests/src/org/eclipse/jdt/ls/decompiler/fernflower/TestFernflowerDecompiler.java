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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestFernflowerDecompiler {

	public static String HELLO_CLASS_PATH = "testclasses/HelloWorld.class";
	public static String EXPECTED_DECOMPILED_CODE = "package org.eclipse.jdt.ls.decompiler.fernflowe;\n" + 
			"\n" + 
			"public class HelloWorld {\n" + 
			"   public void sayHello() {\n" + 
			"      System.out.println(\"Hello world!\");\n" + 
			"   }\n" + 
			"}\n" + 
			"";

	protected IProgressMonitor monitor = new NullProgressMonitor();
	@Mock
	IClassFile classFile;

	@Before
	public void returnClassFileBytes() throws IOException, JavaModelException {
		Path path = Paths.get(HELLO_CLASS_PATH);
		byte[] bytes = Files.readAllBytes(path);
		when(classFile.getBytes()).thenReturn(bytes);
	}

	@Test
	public void testDecompile() throws IOException {
		FernflowerDecompiler decompiler = new FernflowerDecompiler();
		String decompiled = decompiler.decompile(classFile, null, monitor);
		assertEquals(EXPECTED_DECOMPILED_CODE, decompiled);
	}

}
