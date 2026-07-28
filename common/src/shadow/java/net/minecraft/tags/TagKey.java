package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public abstract class TagKey<T> {
	public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> resourceKey, Identifier identifier){ return null; }
	public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> resourceKey, ResourceLocation location){ return null; }
}
