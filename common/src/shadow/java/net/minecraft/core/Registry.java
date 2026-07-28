package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public interface Registry<T> {
	Optional<T> getOptional(ResourceLocation resourceLocation);
	Optional<T> getOptional(Identifier identifier);
	Iterable<Holder<T>> getTagOrEmpty(TagKey<T> tagKey);
}
