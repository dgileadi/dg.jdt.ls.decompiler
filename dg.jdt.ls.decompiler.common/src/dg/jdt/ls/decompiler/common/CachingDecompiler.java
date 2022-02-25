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
package dg.jdt.ls.decompiler.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.ls.core.internal.IDecompiler;

public abstract class CachingDecompiler implements IDecompiler {

	private static final int MAX_CACHE_SIZE = 20;

	protected Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(MAX_CACHE_SIZE).build();

	@Override
	public String getContent(URI uri, IProgressMonitor monitor) throws CoreException {
		String cacheKey = uri.toString();
		String content = cache.getIfPresent(cacheKey);
		if (content == null) {
			content = decompileContent(uri, monitor);
		}
		if (content != null) {
			cache.put(cacheKey, content);
		}
		return content;
	}

	@Override
	public String getSource(IClassFile classFile, IProgressMonitor monitor) throws CoreException {
		String cacheKey = classFile.getHandleIdentifier();
		String content = cache.getIfPresent(cacheKey);
		if (content == null) {
			content = decompileContent(classFile, monitor);
		}
		if (content != null) {
			cache.put(cacheKey, content);
		}
		return content;
	}

	protected abstract String decompileContent(URI uri, IProgressMonitor monitor) throws CoreException;
	protected abstract String decompileContent(IClassFile classFile, IProgressMonitor monitor) throws CoreException;
}
