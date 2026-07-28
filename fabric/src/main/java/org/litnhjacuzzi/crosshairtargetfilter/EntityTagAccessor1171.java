package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.List;
import java.util.function.Function;

import net.minecraft.class_3483;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class EntityTagAccessor1171 implements Function<String, List<? extends EntityType<?>>> {
	@Override
	public List<? extends EntityType<?>> apply(String tagName) {
		return class_3483.method_15082().method_30213(ResourceLocation.tryParse(tagName)).method_15138();
	}
}
