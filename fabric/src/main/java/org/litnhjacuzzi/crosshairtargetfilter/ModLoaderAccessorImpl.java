package org.litnhjacuzzi.crosshairtargetfilter;

import static org.litnhjacuzzi.crosshairtargetfilter.MinecraftProtocolVersions.*;

import org.litnhjacuzzi.crosshairtargetfilter.accessor.ModLoaderAccessor;

public class ModLoaderAccessorImpl implements ModLoaderAccessor {
	@Override
	public boolean isIntermediary() {
		return !MinecraftClientUtil.isGameVersionReached(v26_1);
	}
}
