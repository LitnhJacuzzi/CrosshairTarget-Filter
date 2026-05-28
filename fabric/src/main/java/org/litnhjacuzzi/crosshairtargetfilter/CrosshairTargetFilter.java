package org.litnhjacuzzi.crosshairtargetfilter;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class CrosshairTargetFilter implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (MinecraftClientUtil.isGameVersionReached(775/*26.1*/)) {
			KeyMappingHelper.registerKeyMapping(CTFKeyBindings.TOGGLE_ENTITY_FILTER_KEY);
			KeyMappingHelper.registerKeyMapping(CTFKeyBindings.TOGGLE_BLOCK_FILTER_KEY);
		} else {
			KeyBindingHelper.registerKeyBinding(CTFKeyBindings.TOGGLE_ENTITY_FILTER_KEY);
			KeyBindingHelper.registerKeyBinding(CTFKeyBindings.TOGGLE_BLOCK_FILTER_KEY);
		}
		ClientTickEvents.START_CLIENT_TICK.register(client -> CTFKeyBindings.tick());
		
		AutoConfig.register(CTFAutoConfig.class, GsonConfigSerializer::new);
		CTFCore.CONFIG = AutoConfig.getConfigHolder(CTFAutoConfig.class).getConfig();
	}
}