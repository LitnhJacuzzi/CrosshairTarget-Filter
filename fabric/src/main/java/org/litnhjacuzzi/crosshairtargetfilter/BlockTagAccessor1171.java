package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.List;
import java.util.function.Function;

import net.minecraft.class_3481;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockTagAccessor1171 implements Function<String, List<Block>> {
	@Override
	public List<Block> apply(String tagName) {
		return class_3481.method_15073().method_30213(ResourceLocation.tryParse(tagName)).method_15138();
	}
}