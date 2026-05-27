package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({ "deprecation", "unchecked" })
public class BlockRegistryAccessorImpl implements BlockRegistryAccessor {
	
	private static final Function<String, Optional<Block>> byStringImpl;

	@Override
	public Optional<Block> byString(String registryName) {
		return byStringImpl.apply(registryName);
	}

	static {
		Function<String, Optional<Block>> byStringImplSelector = null;
		if (MinecraftClientUtil.isGameVersionReached(761/*1.19.3*/)) {
			byStringImplSelector = registryName -> BuiltInRegistries.BLOCK.getOptional(ResourceLocation.tryParse(registryName));
		} else {
			byStringImplSelector = registryName -> Optional.ofNullable(((MappedRegistry<Block>) Registry.f_122824_).m_7745_(ResourceLocation.tryParse(registryName)));
		}
		byStringImpl = byStringImplSelector;
	}
}
