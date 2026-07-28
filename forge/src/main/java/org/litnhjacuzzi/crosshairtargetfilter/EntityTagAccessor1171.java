package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.List;
import java.util.function.Function;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

public class EntityTagAccessor1171 implements Function<String, List<? extends EntityType<?>>> {
	@Override
	public List<? extends EntityType<?>> apply(String tagName) {
		return EntityTypeTags.m_13126_().m_7689_(ResourceLocation.tryParse(tagName)).m_6497_();
	}
}
