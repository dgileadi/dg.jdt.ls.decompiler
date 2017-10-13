package dg.jdt.ls.decompiler.cfr;

import java.io.IOException;
import java.util.Collection;

import org.benf.cfr.reader.api.ClassFileSource;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.Pair;
import org.benf.cfr.reader.state.ClassFileSourceImpl;
import org.benf.cfr.reader.util.getopt.Options;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;

public class JDTClassFileSource extends ClassFileSourceImpl {
	public static final String FAKE_PATH = "Fake.class";
	public final Pair<byte[], String> content;

	public JDTClassFileSource(IClassFile classFile, Options options) throws JavaModelException {
		super(options);
		content = new Pair<byte[], String>(classFile.getBytes(), FAKE_PATH);
	}

	@Override
	public Pair<byte[], String> getClassFileContent(String inputPath) throws IOException {
		if (FAKE_PATH.equals(inputPath)) {
			return content;
		}
		return super.getClassFileContent(inputPath);
	}

}
