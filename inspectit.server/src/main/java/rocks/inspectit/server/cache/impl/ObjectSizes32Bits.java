package rocks.inspectit.server.cache.impl;

import rocks.inspectit.server.cache.AbstractObjectSizes;
import rocks.inspectit.shared.all.cmr.cache.IObjectSizes;

/**
 * This class provides a implementation of {@link IObjectSizes} appropriate for calculations of
 * object sizes on 32-bit Sun VM. Works only with Java 7.
 * 
 * @author Ivan Senic
 * 
 */
public class ObjectSizes32Bits extends AbstractObjectSizes {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getReferenceSize() {
		return 4;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getSizeOfObjectHeader() {
		return 8;
	}

}
