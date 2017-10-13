package dg.jdt.ls.decompiler.procyon;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;

import com.strobel.assembler.InputTypeLoader;
import com.strobel.assembler.metadata.Buffer;
import com.strobel.assembler.metadata.ClassFileReader;
import com.strobel.assembler.metadata.IMetadataResolver;
import com.strobel.assembler.metadata.TypeDefinition;

public class JDTTypeLoader extends InputTypeLoader {

	public static final String FAKE_CLASS_NAME = "Fake.class";

	private final byte[] bytes;
	private IMetadataResolver metadataResolver;
	private String internalName;

	public JDTTypeLoader(IClassFile classFile) throws JavaModelException {
		this.bytes = classFile.getBytes();
	}

	public void setMetadataResolver(IMetadataResolver metadataResolver) {
		this.metadataResolver = metadataResolver;
	}

	@Override
	public boolean tryLoadType(String typeNameOrPath, Buffer buffer) {
		if (typeNameOrPath.equals(FAKE_CLASS_NAME) || typeNameOrPath.equals(internalName)) {
			buffer.reset(bytes.length);
			System.arraycopy(bytes, 0, buffer.array(), 0, bytes.length);

			if (internalName == null) {
				TypeDefinition type = ClassFileReader.readClass(0, metadataResolver, buffer);
				if (type != null) {
					internalName = type.getInternalName();
				}
				buffer.position(0);
			}
			return true;
		}
		return super.tryLoadType(typeNameOrPath, buffer);
	}

}
