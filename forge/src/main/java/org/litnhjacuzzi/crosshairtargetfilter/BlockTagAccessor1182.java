package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({ "unchecked" })
public class BlockTagAccessor1182 implements Function<String, List<Block>> {
	@Override
	public List<Block> apply(String tagName) {
		Optional<Iterable<Holder<Block>>> blockHolder = ((MappedRegistry<Block>) Registry.f_122824_)
				.m_203431_(TagKey.create(Registry.f_122901_, ResourceLocation.tryParse(tagName)));
		return blockHolder.isPresent() ? StreamSupport.stream(blockHolder.get().spliterator(), true).map(Holder::value).toList() : Collections.emptyList();
	}
}
