package org.litnhjacuzzi.crosshairtargetfilter;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CTFCore.MODID)
public class CrosshairTargetFilter {
	@SuppressWarnings("removal")
	public CrosshairTargetFilter() {
		this(FMLJavaModLoadingContext.get());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "removal" })
	public CrosshairTargetFilter(FMLJavaModLoadingContext context) {
		Minecraft.getInstance().options.keyMappings = ArrayUtils.addAll(Minecraft.getInstance().options.keyMappings, 
				CTFKeyBindings.TOGGLE_ENTITY_FILTER_KEY, CTFKeyBindings.TOGGLE_BLOCK_FILTER_KEY);
		MinecraftForge.EVENT_BUS.register(new Object() {
			@SubscribeEvent
			public void onStartTick(TickEvent.ClientTickEvent e) {
				if(e.phase == Phase.START) {
					CTFKeyBindings.tick();
				}
			}
		});
		
		AutoConfig.register(CTFAutoConfig.class, GsonConfigSerializer::new);
		CTFCore.CONFIG = AutoConfig.getConfigHolder(CTFAutoConfig.class).getConfig();
		Class configFactoryCls = null;
		try {
			// 1.19+
			configFactoryCls = Class.forName(
					"net.minecraftforge.client.ConfigScreenHandler$ConfigScreenFactory");
		} catch (ClassNotFoundException e) {
			try {
				//1.18.x
				configFactoryCls = Class.forName(
						"net.minecraftforge.client.ConfigGuiHandler$ConfigGuiFactory");
			} catch (ClassNotFoundException e1) {
				try {
					//1.17.x
					configFactoryCls = Class.forName(
							"net.minecraftforge.fmlclient.ConfigGuiHandler$ConfigGuiFactory");
				} catch (ClassNotFoundException e2) {}
			}
		}
		Class _configFactoryCls = configFactoryCls;
		Supplier configFactorySupplier = () -> ReflectionUtil.newInstance(_configFactoryCls, 
				new Class[] {BiFunction.class}, new BiFunction<Minecraft, Screen, Screen>() {
					@Override
					public Screen apply(Minecraft client, Screen parent) {
						return CTFAutoConfig.getConfigScreen(parent);
					}
				});
		ModLoadingContext.get().registerExtensionPoint(configFactoryCls, configFactorySupplier);
	}
}
