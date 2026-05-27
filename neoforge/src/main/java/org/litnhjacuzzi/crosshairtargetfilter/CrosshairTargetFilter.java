package org.litnhjacuzzi.crosshairtargetfilter;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CTFCore.MODID)
public class CrosshairTargetFilter {
	public CrosshairTargetFilter(ModContainer container) {
		container.getEventBus().register(new Object() {
			@SubscribeEvent
			public void registerKeyBindings(RegisterKeyMappingsEvent event) {
				event.register(CTFKeyBindings.TOGGLE_ENTITY_FILTER_KEY);
				event.register(CTFKeyBindings.TOGGLE_BLOCK_FILTER_KEY);
			}
		});
		
		NeoForge.EVENT_BUS.register(new Object() {
			@SubscribeEvent
			public void onStartTick(ClientTickEvent.Pre e) {
				CTFKeyBindings.tick();
			}
		});
		
		AutoConfig.register(CTFAutoConfig.class, GsonConfigSerializer::new);
		CTFCore.CONFIG = AutoConfig.getConfigHolder(CTFAutoConfig.class).getConfig();
		container.registerExtensionPoint(IConfigScreenFactory.class, new IConfigScreenFactory() {
			@Override
			public Screen createScreen(Minecraft minecraft, Screen modListScreen) {
				return CTFAutoConfig.getConfigScreen(modListScreen);
			}
			
			@SuppressWarnings("unused")
			public Screen createScreen(ModContainer container, Screen modListScreen) {
				return CTFAutoConfig.getConfigScreen(modListScreen);
			}
		});
	}
}
