package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockRegistryAccessorImpl implements BlockRegistryAccessor {
	
	private static final Function<String, Optional<Block>> byStringImpl;

	@Override
	public Optional<Block> byString(String registryName) {
		return byStringImpl.apply(registryName);
	}

	static {
		Function<String, Optional<Block>> byStringImplSelector;
		if (MinecraftClientUtil.isGameVersionReached(774/*1.21.11*/)) {
			byStringImplSelector = registryName -> BuiltInRegistries.BLOCK.getOptional(Identifier.tryParse(registryName));
		} else {
			byStringImplSelector = registryName -> BuiltInRegistries.BLOCK.getOptional(ResourceLocation.tryParse(registryName));
		}
		byStringImpl = byStringImplSelector;
	}
}
