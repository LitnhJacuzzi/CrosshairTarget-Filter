package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.class_2378;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockRegistryAccessorLegacy implements Function<String, Optional<Block>> {
	@Override
	public Optional<Block> apply(String registryName) {
		return class_2378.field_11146.method_17966(ResourceLocation.tryParse(registryName));
	}
}
