package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.List;
import java.util.function.Function;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class BlockTagAccessor1171 implements Function<String, List<Block>> {
	@Override
	public List<Block> apply(String tagName) {
		return BlockTags.m_13115_().m_7689_(ResourceLocation.tryParse(tagName)).m_6497_();
	}
}