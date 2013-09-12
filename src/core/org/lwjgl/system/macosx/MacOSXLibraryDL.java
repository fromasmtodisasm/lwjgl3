/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;

import static org.lwjgl.system.macosx.DynamicLinkLoader.*;

/** Implements a {@link org.lwjgl.system.DynamicLinkLibrary} on the MacOS X using dlopen. */
public class MacOSXLibraryDL extends MacOSXLibrary {

	private final long handle;

	public MacOSXLibraryDL(String name) {
		super(name);

		this.handle = dlopen(name, RTLD_LAZY | RTLD_GLOBAL);
		if ( handle == 0 ) // TODO: better error handling
			throw new RuntimeException("Failed to dynamically load library: " + name);
	}

	@Override
	public long getHandle() {
		return handle;
	}

	@Override
	public long getFunctionAddress(ByteBuffer name) {
		return dlsym(handle, name);
	}

	@Override
	public long getFunctionAddress(String name) {
		return dlsym(handle, name);
	}

	@Override
	public void destroy() {
		dlclose(handle);
	}

}