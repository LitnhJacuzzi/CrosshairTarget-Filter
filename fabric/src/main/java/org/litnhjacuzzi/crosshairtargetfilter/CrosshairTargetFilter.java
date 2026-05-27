package org.litnhjacuzzi.crosshairtargetfilter;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class CrosshairTargetFilter implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyBindingHelper.registerKeyBinding(CTFKeyBindings.TOGGLE_ENTITY_FILTER_KEY);
		KeyBindingHelper.registerKeyBinding(CTFKeyBindings.TOGGLE_BLOCK_FILTER_KEY);
		AutoConfig.register(CTFAutoConfig.class, GsonConfigSerializer::new);
		CTFCore.CONFIG = AutoConfig.getConfigHolder(CTFAutoConfig.class).getConfig();
		ClientTickEvents.START_CLIENT_TICK.register(client -> CTFKeyBindings.tick());
	}
}