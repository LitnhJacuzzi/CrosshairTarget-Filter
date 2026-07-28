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
import net.minecraft.world.entity.EntityType;

@SuppressWarnings({ "unchecked" })
public class EntityTagAccessor1182 implements Function<String, List<? extends EntityType<?>>> {
	@Override
	public List<? extends EntityType<?>> apply(String tagName) {
		Optional<Iterable<Holder<EntityType<?>>>> entityHolder = ((MappedRegistry<EntityType<?>>) Registry.f_122826_)
				.m_203431_(TagKey.create(Registry.f_122903_, ResourceLocation.tryParse(tagName)));
		return entityHolder.isPresent() ? StreamSupport.stream(entityHolder.get().spliterator(), true).map(Holder::value).toList() : Collections.emptyList();
	}
}
