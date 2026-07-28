package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public interface Registry<T> {
	/**ENTITY_TYPE*/
	public static final DefaultedRegistry<EntityType<?>> f_122826_ = null;
	/**ENTITY_TYPE_REGISTRY*/
	public static final ResourceKey<Registry<EntityType<?>>> f_122903_ = null;
	/**BLOCK*/
	public static final DefaultedRegistry<Block> f_122824_ = null;
	/**BLOCK_REGISTRY*/
	public static final ResourceKey<Registry<Block>> f_122901_ = null;
	
	Optional<T> getOptional(ResourceLocation resourceLocation);

	Iterable<Holder<T>> getTagOrEmpty(TagKey<T> tagKey);
}
