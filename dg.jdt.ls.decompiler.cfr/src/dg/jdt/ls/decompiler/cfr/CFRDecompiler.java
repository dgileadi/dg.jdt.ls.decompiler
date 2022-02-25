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

import static org.eclipse.jdt.ls.core.internal.handlers.MapFlattener.getValue;

import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import dg.jdt.ls.decompiler.common.CachingDecompiler;

import org.benf.cfr.reader.apiunreleased.ClassFileSource2;
import org.benf.cfr.reader.entities.ClassFile;
import org.benf.cfr.reader.entities.Method;
import org.benf.cfr.reader.state.ClassFileSourceImpl;
import org.benf.cfr.reader.state.DCCommonState;
import org.benf.cfr.reader.state.TypeUsageCollectingDumper;
import org.benf.cfr.reader.util.getopt.Options;
import org.benf.cfr.reader.util.getopt.OptionsImpl;
import org.benf.cfr.reader.util.output.IllegalIdentifierDump;
import org.benf.cfr.reader.util.output.MethodErrorCollector;
import org.benf.cfr.reader.util.output.StringStreamDumper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.ls.core.internal.IDecompiler;
import org.eclipse.jdt.ls.core.internal.JDTUtils;
import org.eclipse.jdt.ls.core.internal.JavaLanguageServerPlugin;
import org.eclipse.jdt.ls.core.internal.preferences.Preferences;

public class CFRDecompiler extends CachingDecompiler {

	public static final String OPTIONS_KEY = "java.decompiler.cfr";

	private Map<String, String> options = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public void setPreferences(Preferences preferences) {
		this.options = new HashMap<>();
		Object options = getValue(preferences.asMap(), OPTIONS_KEY);
		if (options instanceof Map) {
			// convert all values to strings
			Map<String, Object> optionsMap = (Map<String, Object>) options;
			for (String key : optionsMap.keySet()) {
				Object value = optionsMap.get(key);
				if (value != null && !(value instanceof String)) {
					optionsMap.put(key, value.toString());
				}
			}

			this.options = (Map<String, String>) options;
		}
	}

	@Override
	protected String decompileContent(URI uri, IProgressMonitor monitor) throws CoreException {
		IClassFile classFile = JDTUtils.resolveClassFile(uri);
		if (classFile != null) {
			return decompileContent(classFile, monitor);
		}

		Options options = new OptionsImpl(this.options);
		return getContent(new ClassFileSourceImpl(options), Path.of(uri).toString(), options, monitor);
	}

	@Override
	protected String decompileContent(IClassFile classFile, IProgressMonitor monitor) throws CoreException {
		Options options = new OptionsImpl(this.options);
		return getContent(new JDTClassFileSource(classFile, options), JDTClassFileSource.FAKE_PATH, options, monitor);
	}

	private String getContent(ClassFileSource2 classFileSource, String path, Options options, IProgressMonitor monitor)
			throws CoreException {
		try {
			classFileSource.informAnalysisRelativePathDetail(null, null);
			DCCommonState commonState = new DCCommonState(options, classFileSource);
			ClassFile classFile = commonState.getClassFileMaybePath(path);
			commonState.configureWith(classFile);

			if (((Boolean) options.getOption(OptionsImpl.DECOMPILE_INNER_CLASSES)).booleanValue()) {
				classFile.loadInnerClasses(commonState);
			}

			TypeUsageCollectingDumper collectingDumper = new TypeUsageCollectingDumper(options, classFile);
			classFile.analyseTop(commonState, collectingDumper);

			IllegalIdentifierDump illegalIdentifierDump = IllegalIdentifierDump.Factory.get(options);

			MethodErrorCollector methodErrorCollector = new MethodErrorCollector() {
				@Override
				public void addSummaryError(Method method, String s) {
				}
			};

			final StringBuilder source = new StringBuilder();
			StringStreamDumper dumper = new StringStreamDumper(methodErrorCollector, source,
					collectingDumper.getTypeUsageInformation(), options, illegalIdentifierDump);

			classFile.dump(dumper);

			return source.toString();
		} catch (Throwable t) {
			throw new CoreException(new Status(Status.ERROR, "dg.jdt.ls.decompiler.cfr", "Error decompiling", t));
		}
	}
}
