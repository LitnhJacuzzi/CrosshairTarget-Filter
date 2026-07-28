package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public abstract class MappedRegistry<T> {
	/**get*/
	public abstract T m_7745_(ResourceLocation resourceLocation);
	/**getTag*/
	public abstract Optional<Iterable<Holder<T>>> m_203431_(TagKey<T> tagKey);
}
