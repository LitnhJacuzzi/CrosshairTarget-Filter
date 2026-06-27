package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({ "unchecked" })
public class BlockRegistryAccessorLegacy implements Function<String, Optional<Block>> {
	@Override
	public Optional<Block> apply(String registryName) {
		return Optional.ofNullable(((MappedRegistry<Block>) Registry.f_122824_).m_7745_(ResourceLocation.tryParse(registryName)));
	}
}
