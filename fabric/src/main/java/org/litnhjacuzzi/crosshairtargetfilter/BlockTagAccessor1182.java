package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import net.minecraft.class_2378;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTagAccessor1182 implements Function<String, List<Block>> {
	@Override
	public List<Block> apply(String tagName) {
		Optional<Iterable<Holder<Block>>> blockHolder = class_2378.field_11146.method_40266(TagKey.create(
				class_2378.field_25105, ResourceLocation.tryParse(tagName)));
		return blockHolder.isPresent() ? StreamSupport.stream(blockHolder.get().spliterator(), true).map(Holder::value).toList() : Collections.emptyList();
	}
}
