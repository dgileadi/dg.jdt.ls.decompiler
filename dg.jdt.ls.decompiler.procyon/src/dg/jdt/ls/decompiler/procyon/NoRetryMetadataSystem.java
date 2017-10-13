package dg.jdt.ls.decompiler.procyon;

import java.util.HashSet;
import java.util.Set;

import com.strobel.assembler.metadata.ITypeLoader;
import com.strobel.assembler.metadata.MetadataSystem;
import com.strobel.assembler.metadata.TypeDefinition;

final class NoRetryMetadataSystem extends MetadataSystem {
	private final Set<String> _failedTypes = new HashSet<>();

	NoRetryMetadataSystem() {
	}

	NoRetryMetadataSystem(String classPath) {
		super(classPath);
	}

	NoRetryMetadataSystem(ITypeLoader typeLoader) {
		super(typeLoader);
	}

	protected TypeDefinition resolveType(String descriptor, boolean mightBePrimitive) {
		if (this._failedTypes.contains(descriptor)) {
			return null;
		} else {
			TypeDefinition result = super.resolveType(descriptor, mightBePrimitive);
			if (result == null) {
				this._failedTypes.add(descriptor);
			}

			return result;
		}
	}
}