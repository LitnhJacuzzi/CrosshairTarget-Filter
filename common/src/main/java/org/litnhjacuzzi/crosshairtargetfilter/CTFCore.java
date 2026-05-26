package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class CTFCore {
	public static final String MODID = "crosshairtargetfilter";
	public static final Logger LOGGER = LogManager.getLogger();
	
	private static final BlockRegistryAccessor blockRegistryAccessor;
	
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
				EntityType.byString(entityToFilter).ifPresent(filteredEntityType -> {
					FilterTarget filteredEntityTypeCasted = (FilterTarget) filteredEntityType;
					filteredEntityTypeCasted.ctf$markListed();
					filteredEntityTypes.add(filteredEntityTypeCasted);
				});
			}
		}
		hasEntityNames = !filteredEntityNames.isEmpty();
		
		for (String blockToFilter: blocksToFilter) {
			blockRegistryAccessor.byString(blockToFilter).ifPresent(filteredBlock -> {
				FilterTarget filteredBlockCasted = (FilterTarget) filteredBlock;
				filteredBlockCasted.ctf$markListed();
				filteredBlocks.add(filteredBlockCasted);
			});
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
	
	static {
		Class<?> blockRegistryAccessorCls = null;
		try {
			blockRegistryAccessorCls = Class.forName("org.litnhjacuzzi.crosshairtargetfilter.BlockRegistryAccessorImpl");
		} catch (Throwable e) {}
		blockRegistryAccessor = (BlockRegistryAccessor) ReflectionUtil.newInstance(blockRegistryAccessorCls, new Class[0]);
	}
}
