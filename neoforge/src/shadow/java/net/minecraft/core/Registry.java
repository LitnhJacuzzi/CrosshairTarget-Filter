package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceLocation;

public interface Registry<T> {
	Optional<T> getOptional(ResourceLocation resourceLocation);
	Optional<T> getOptional(Identifier identifier);
}
