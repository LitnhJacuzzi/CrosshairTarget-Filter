package org.litnhjacuzzi.crosshairtargetfilter;

import org.litnhjacuzzi.crosshairtargetfilter.accessor.ModLoaderAccessor;

public class ModLoaderAccessorImpl implements ModLoaderAccessor {
	@Override
	public boolean isIntermediary() {
		return !MinecraftClientUtil.isGameVersionReached(775/*26.1*/);
	}
}
