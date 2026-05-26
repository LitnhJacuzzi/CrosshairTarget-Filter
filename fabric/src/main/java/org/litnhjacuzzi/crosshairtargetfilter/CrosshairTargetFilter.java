package org.litnhjacuzzi.crosshairtargetfilter;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class CrosshairTargetFilter implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AutoConfig.register(CTFAutoConfig.class, GsonConfigSerializer::new);
		CTFCore.CONFIG = AutoConfig.getConfigHolder(CTFAutoConfig.class).getConfig();
	}
}