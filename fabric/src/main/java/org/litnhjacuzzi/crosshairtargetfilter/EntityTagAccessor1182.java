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
import net.minecraft.world.entity.EntityType;

public class EntityTagAccessor1182 implements Function<String, List<? extends EntityType<?>>> {
	@Override
	public List<? extends EntityType<?>> apply(String tagName) {
		Optional<Iterable<Holder<EntityType<?>>>> entityHolder = class_2378.field_11145.method_40266(TagKey.create(
				class_2378.field_25107, ResourceLocation.tryParse(tagName)));
		return entityHolder.isPresent() ? StreamSupport.stream(entityHolder.get().spliterator(), true).map(Holder::value).toList() : Collections.emptyList();
	}
}
