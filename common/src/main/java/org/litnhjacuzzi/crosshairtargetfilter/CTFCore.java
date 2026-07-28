package org.litnhjacuzzi.crosshairtargetfilter;

import static org.litnhjacuzzi.crosshairtargetfilter.MinecraftProtocolVersions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.litnhjacuzzi.crosshairtargetfilter.accessor.ModLoaderAccessor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CTFCore {
	public static final String MODID = "crosshairtargetfilter";
	public static final Logger LOGGER = LogManager.getLogger();
	
	private static final ModLoaderAccessor modLoaderAccessor;
	
	private static final Function<String, Optional<EntityType<?>>> entityTypeRegistryAccessor;
	private static final Function<String, Optional<Block>> blockRegistryAccessor;
	private static final Function<String, List<Block>> blockTagAccessor;
	
	public static boolean isPicking = false;
	
	public static CTFConfig CONFIG = new CTFConfig() {
		@Override
		public boolean isEntityFilterEnabled() {
			return false;
		}
		
		@Override
		public boolean isBlockFilterEnabled() {
			return false;
		}
		
		@Override
		public FilterType getEntityFilterType() {
			return FilterType.BLACKLIST;
		}
		
		@Override
		public FilterType getBlockFilterType() {
			return FilterType.BLACKLIST;
		}
	};
	
	private static final List<FilterTarget> filteredEntityTypes = new ArrayList<>();
	private static final List<FilterTarget> filteredMobCategoties = new ArrayList<>();
	private static final Set<String> filteredEntityNames = new HashSet<>();
	private static final List<FilterTarget> filteredBlocks = new ArrayList<>();
	private static boolean hasEntityNames = false;
	
	public static void bakeFilteredTargets(List<String> entitiesToFilter, List<String> blocksToFilter) {
		filteredEntityTypes.forEach(FilterTarget::ctf$markUnlisted);
		filteredEntityTypes.clear();
		filteredMobCategoties.forEach(FilterTarget::ctf$markUnlisted);
		filteredMobCategoties.clear();
		filteredEntityNames.clear();
		filteredBlocks.forEach(FilterTarget::ctf$markUnlisted);
		filteredBlocks.clear();
		
		for (String entityToFilter : entitiesToFilter) {
			entityToFilter = entityToFilter.trim();
			if (entityToFilter.startsWith("\"") && entityToFilter.endsWith("\"")) {
				if (entityToFilter.length() > 2) {
					filteredEntityNames.add(entityToFilter.substring(1, entityToFilter.length() - 1));
				}
			} else if (entityToFilter.startsWith("[") && entityToFilter.endsWith("]")) {
				if (entityToFilter.length() > 2) {
					try {
						FilterTarget mobCategory = (FilterTarget) (Object) MobCategory
								.valueOf(entityToFilter.substring(1, entityToFilter.length() - 1));
						mobCategory.ctf$markListed();
						filteredMobCategoties.add(mobCategory);
					} catch (Throwable e) {} 
				}
			} else {
				entityTypeRegistryAccessor.apply(entityToFilter).ifPresent(filteredEntityType -> {
					FilterTarget filteredEntityTypeCasted = (FilterTarget) filteredEntityType;
					filteredEntityTypeCasted.ctf$markListed();
					filteredEntityTypes.add(filteredEntityTypeCasted);
				});
			}
		}
		hasEntityNames = !filteredEntityNames.isEmpty();
		
		for (String blockToFilter: blocksToFilter) {
			if (blockToFilter.startsWith("#")) {
				if (blockToFilter.length() > 1) {
					try {
						blockTagAccessor.apply(blockToFilter.substring(1)).forEach(CTFCore::tryAddFilteredBlock);
					} catch (Throwable e) {}
				}
			} else {
				blockRegistryAccessor.apply(blockToFilter).ifPresent(CTFCore::tryAddFilteredBlock);
			}
		}
	}
	
	private static void tryAddFilteredBlock(Block block) {
		FilterTarget blockCasted = (FilterTarget) block;
		if (!blockCasted.ctf$isListed()) {
			blockCasted.ctf$markListed();
			filteredBlocks.add(blockCasted);
		}
	}
	
	public static boolean evaluateEntity(Entity entity) {
		boolean isFiltered = ((FilterTarget) entity.getType()).ctf$isListed() ||
				((FilterTarget) (Object) entity.getType().getCategory()).ctf$isListed() || 
				(hasEntityNames && filteredEntityNames.contains(entity.getName().getString()));
		return isFiltered == (CONFIG.getEntityFilterType() == FilterType.WHITELIST);
	}
	
	public static boolean evaluateBlock(BlockPos pos) {
		if (Minecraft.getInstance().level == null) return true;
		boolean isFiltered = ((FilterTarget) Minecraft.getInstance().level.getBlockState(pos).getBlock()).ctf$isListed();
		return isFiltered == (CONFIG.getBlockFilterType() == FilterType.WHITELIST);
	}
	
	public static boolean isIntermediary() {
		return modLoaderAccessor.isIntermediary();
	}
	
	static {
		modLoaderAccessor = (ModLoaderAccessor) ReflectionUtil.newInstance("org.litnhjacuzzi.crosshairtargetfilter.ModLoaderAccessorImpl", new Class[0]);
		
		if (MinecraftClientUtil.isGameVersionReached(v26_2)) {
			entityTypeRegistryAccessor = registryName -> BuiltInRegistries.ENTITY_TYPE.getOptional(Identifier.tryParse(registryName));
		} else {
			entityTypeRegistryAccessor = EntityType::byString;
		}
		
		if (!modLoaderAccessor.isIntermediary() && MinecraftClientUtil.isGameVersionReached(v1_21_11)) {
			blockRegistryAccessor = registryName -> BuiltInRegistries.BLOCK.getOptional(Identifier.tryParse(registryName));
			blockTagAccessor = tagName -> StreamSupport.stream(BuiltInRegistries.BLOCK.getTagOrEmpty(
					TagKey.create(Registries.BLOCK, Identifier.tryParse(tagName))).spliterator(), true).map(Holder::value).toList();
		} else if (MinecraftClientUtil.isGameVersionReached(v1_19_3)) {
			blockRegistryAccessor = registryName -> BuiltInRegistries.BLOCK.getOptional(ResourceLocation.tryParse(registryName));
			blockTagAccessor = tagName -> StreamSupport.stream(BuiltInRegistries.BLOCK.getTagOrEmpty(
					TagKey.create(Registries.BLOCK, ResourceLocation.tryParse(tagName))).spliterator(), true).map(Holder::value).toList();
		} else {
			blockRegistryAccessor = (Function) ReflectionUtil.newInstance("org.litnhjacuzzi.crosshairtargetfilter.BlockRegistryAccessorLegacy", new Class[0]);
			if (MinecraftClientUtil.isGameVersionReached(v1_18_2)) {
				blockTagAccessor = (Function) ReflectionUtil.newInstance("org.litnhjacuzzi.crosshairtargetfilter.BlockTagAccessor1182", new Class[0]);
			} else {
				blockTagAccessor = (Function) ReflectionUtil.newInstance("org.litnhjacuzzi.crosshairtargetfilter.BlockTagAccessor1171", new Class[0]);
			}
		}
	}
}
