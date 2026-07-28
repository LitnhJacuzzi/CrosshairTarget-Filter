package org.litnhjacuzzi.crosshairtargetfilter;

import static org.litnhjacuzzi.crosshairtargetfilter.MinecraftProtocolVersions.*;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.AutoConfigClient;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;
import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import net.minecraft.client.gui.screens.Screen;

@Config(name = CTFCore.MODID)
public class CTFAutoConfig implements CTFConfig, ConfigData {
	boolean enableEntityFilter = true;
	
	@ConfigEntry.Gui.EnumHandler(option = EnumDisplayOption.BUTTON)
	FilterType entityFilterType = FilterType.BLACKLIST;
	
	List<String> entityWhitelist = new ArrayList<>();
	
	List<String> entityBlacklist = new ArrayList<>();
	
	boolean enableBlockFilter = true;
	
	@ConfigEntry.Gui.EnumHandler(option = EnumDisplayOption.BUTTON)
	FilterType blockFilterType = FilterType.BLACKLIST;
	
	List<String> blockWhitelist = new ArrayList<>();
	
	List<String> blockBlacklist = new ArrayList<>();
	
	@Override
	public void reload() {
		validatePostLoad();
	}
	
	@Override
	public void validatePostLoad() {
		List<String> entitiesToFilter = entityFilterType == FilterType.WHITELIST ? entityWhitelist : entityBlacklist;
		List<String> blocksToFilter = blockFilterType == FilterType.WHITELIST ? blockWhitelist : blockBlacklist;
		CTFCore.bakeFilteredTargets(entitiesToFilter, blocksToFilter);
	}
	
	@Override
	public void toggleEntityFilter() {
		enableEntityFilter = !enableEntityFilter;
		AutoConfig.getConfigHolder(CTFAutoConfig.class).save();
	}
	
	@Override
	public void toggleBlockFilter() {
		enableBlockFilter = !enableBlockFilter;
		AutoConfig.getConfigHolder(CTFAutoConfig.class).save();
	}
	
	@Override
	public boolean isEntityFilterEnabled() {
		return enableEntityFilter;
	}
	
	@Override
	public boolean isBlockFilterEnabled() {
		return enableBlockFilter;
	}
	
	public FilterType getEntityFilterType() {
		return entityFilterType;
	}
	
	public FilterType getBlockFilterType() {
		return blockFilterType;
	}
	
	public static Screen getConfigScreen(Screen parent) {
		Screen configScreen = MinecraftClientUtil.isGameVersionReached(v26_1) ?
				AutoConfigClient.getConfigScreen(CTFAutoConfig.class, parent).get() :
				AutoConfig.getConfigScreen(CTFAutoConfig.class, parent).get();
		ConfigHolder<CTFAutoConfig> configHolder = AutoConfig.getConfigHolder(CTFAutoConfig.class);
		((AbstractConfigScreen) configScreen).setSavingRunnable(() -> {
			configHolder.save();
			configHolder.getConfig().validatePostLoad();
		});
		return configScreen;
	}
}
