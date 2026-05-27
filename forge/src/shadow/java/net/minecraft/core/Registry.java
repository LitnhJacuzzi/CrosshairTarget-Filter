package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface Registry<T> {
	/**BLOCK*/
	public static final DefaultedRegistry<Block> f_122824_ = null;
	
	Optional<T> getOptional(ResourceLocation resourceLocation);	
}
