package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;

import net.minecraft.world.level.block.Block;

public interface BlockRegistryAccessor {
	default boolean isIntermediary() { return false; }
	Optional<Block> byString(String registryName);
}
