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
package dg.jdt.ls.decompiler.test.common;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.CompletionRequestor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IBufferFactory;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICodeCompletionRequestor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.ICompletionRequestor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IProblemRequestor;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;

public class FakeClassFile implements IClassFile {
	private final byte[] bytes;

	public FakeClassFile(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public IType findPrimaryType() {
		return null;
	}

	@Override
	public IJavaElement getElementAt(int position) throws JavaModelException {
		return null;
	}

	@Override
	public ICompilationUnit getWorkingCopy(WorkingCopyOwner owner, IProgressMonitor monitor) throws JavaModelException {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public IJavaElement getAncestor(int ancestorType) {
		return null;
	}

	@Override
	public String getAttachedJavadoc(IProgressMonitor monitor) throws JavaModelException {
		return null;
	}

	@Override
	public String getElementName() {
		return null;
	}

	@Override
	public int getElementType() {
		return 0;
	}

	@Override
	public String getHandleIdentifier() {
		return null;
	}

	@Override
	public IJavaModel getJavaModel() {
		return null;
	}

	@Override
	public IJavaProject getJavaProject() {
		return null;
	}

	@Override
	public IOpenable getOpenable() {
		return null;
	}

	@Override
	public IJavaElement getParent() {
		return null;
	}

	@Override
	public IPath getPath() {
		return null;
	}

	@Override
	public IJavaElement getPrimaryElement() {
		return null;
	}

	@Override
	public ISchedulingRule getSchedulingRule() {
		return null;
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}

	@Override
	public boolean isStructureKnown() throws JavaModelException {
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public IJavaElement[] getChildren() throws JavaModelException {
		return null;
	}

	@Override
	public boolean hasChildren() throws JavaModelException {
		return false;
	}

	@Override
	public void close() throws JavaModelException {

	}

	@Override
	public String findRecommendedLineSeparator() throws JavaModelException {
		return null;
	}

	@Override
	public IBuffer getBuffer() throws JavaModelException {
		return null;
	}

	@Override
	public boolean hasUnsavedChanges() throws JavaModelException {
		return false;
	}

	@Override
	public boolean isConsistent() throws JavaModelException {
		return false;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void makeConsistent(IProgressMonitor progress) throws JavaModelException {

	}

	@Override
	public void open(IProgressMonitor progress) throws JavaModelException {

	}

	@Override
	public void save(IProgressMonitor progress, boolean force) throws JavaModelException {

	}

	@Override
	public String getSource() throws JavaModelException {
		return null;
	}

	@Override
	public ISourceRange getSourceRange() throws JavaModelException {
		return null;
	}

	@Override
	public ISourceRange getNameRange() throws JavaModelException {
		return null;
	}

	@Override
	public void codeComplete(int offset, ICodeCompletionRequestor requestor) throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, ICompletionRequestor requestor) throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, CompletionRequestor requestor) throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, CompletionRequestor requestor, IProgressMonitor monitor)
			throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, ICompletionRequestor requestor, WorkingCopyOwner owner)
			throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, CompletionRequestor requestor, WorkingCopyOwner owner)
			throws JavaModelException {

	}

	@Override
	public void codeComplete(int offset, CompletionRequestor requestor, WorkingCopyOwner owner,
			IProgressMonitor monitor) throws JavaModelException {

	}

	@Override
	public IJavaElement[] codeSelect(int offset, int length) throws JavaModelException {
		return null;
	}

	@Override
	public IJavaElement[] codeSelect(int offset, int length, WorkingCopyOwner owner) throws JavaModelException {
		return null;
	}

	@Override
	public ICompilationUnit becomeWorkingCopy(IProblemRequestor problemRequestor, WorkingCopyOwner owner,
			IProgressMonitor monitor) throws JavaModelException {
		return null;
	}

	@Override
	public byte[] getBytes() throws JavaModelException {
		return bytes;
	}

	@Override
	public IType getType() {
		return null;
	}

	@Override
	public IJavaElement getWorkingCopy(IProgressMonitor monitor, IBufferFactory factory) throws JavaModelException {
		return null;
	}

	@Override
	public boolean isClass() throws JavaModelException {
		return false;
	}

	@Override
	public boolean isInterface() throws JavaModelException {
		return false;
	}

	@Override
	public IResource getCorrespondingResource() throws JavaModelException {
		return null;
	}

	@Override
	public IResource getResource() {
		return null;
	}

	@Override
	public IResource getUnderlyingResource() throws JavaModelException {
		return null;
	}

}
