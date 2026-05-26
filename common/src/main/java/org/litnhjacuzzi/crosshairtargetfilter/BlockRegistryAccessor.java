package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;

import net.minecraft.world.level.block.Block;

public interface BlockRegistryAccessor {
	Optional<Block> byString(String registryName);
}
