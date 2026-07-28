package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface Registry<T> {
	/**BLOCK*/
	public static final DefaultedRegistry<Block> f_122824_ = null;
	/**BLOCK_REGISTRY*/
	public static final ResourceKey<Registry<Block>> f_122901_ = null;
	
	Optional<T> getOptional(ResourceLocation resourceLocation);	
}
