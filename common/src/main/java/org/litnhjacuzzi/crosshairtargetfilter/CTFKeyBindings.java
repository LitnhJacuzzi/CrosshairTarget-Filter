package org.litnhjacuzzi.crosshairtargetfilter;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants.Type;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class CTFKeyBindings {
	public static final KeyMapping TOGGLE_ENTITY_FILTER_KEY;
	public static final KeyMapping TOGGLE_BLOCK_FILTER_KEY;
	
	private static final String TOGGLE_ENTITY_FILTER_I18N_KEY = "crosshairtargetfilter.indicatortext.toggleentityfilter";
	private static final String TOGGLE_BLOCK_FILTER_I18N_KEY = "crosshairtargetfilter.indicatortext.toggleblockfilter";
	private static final String ENABLED_I18N_KEY = "crosshairtargetfilter.indicatortext.enabled";
	private static final String DISABLED_I18N_KEY = "crosshairtargetfilter.indicatortext.disabled";
	
	public static void tick() {
		Player player = Minecraft.getInstance().player;
		if (TOGGLE_ENTITY_FILTER_KEY.consumeClick() && player != null) {
			CTFCore.CONFIG.toggleEntityFilter();
			displayIndicator(createIndicatorComponent(TOGGLE_ENTITY_FILTER_I18N_KEY, 
					CTFCore.CONFIG.isEntityFilterEnabled()), player);
		}
		if (TOGGLE_BLOCK_FILTER_KEY.consumeClick() && player != null) {
			CTFCore.CONFIG.toggleBlockFilter();
			displayIndicator(createIndicatorComponent(TOGGLE_BLOCK_FILTER_I18N_KEY, 
					CTFCore.CONFIG.isBlockFilterEnabled()), player);
		}
		while (TOGGLE_ENTITY_FILTER_KEY.consumeClick());
		while (TOGGLE_BLOCK_FILTER_KEY.consumeClick());
	}
	
	private static MutableComponent createIndicatorComponent(String toggleKey, boolean state) {
		if (MinecraftClientUtil.isGameVersionReached(766/*1.20.5*/)) {
			Component stateComponent = state ?
					Component.translatable(ENABLED_I18N_KEY).withStyle(ChatFormatting.GREEN) :
					Component.translatable(DISABLED_I18N_KEY).withStyle(ChatFormatting.RED);
			return Component.translatable(toggleKey).append(stateComponent);
		} else {
			String stateKey, color;
			if (state) {
				stateKey = ENABLED_I18N_KEY;
				color = "green";
			} else {
				stateKey = DISABLED_I18N_KEY;
				color = "red";
			}
			return Component.Serializer.fromJson("{\"translate\":\"" + toggleKey + 
					"\",\"extra\":[{\"translate\":\"" + stateKey + "\",\"color\":\"" + color + "\"}]}");
		}
	}
	
	private static void displayIndicator(Component indicator, Player player) {
		if (MinecraftClientUtil.isGameVersionReached(775/*26.1*/)) {
			player.sendOverlayMessage(indicator);
		} else {
			player.displayClientMessage(indicator, true);
		}
	}
	
	static {
		KeyMapping toggleEntityFilterKey = null, toggleBlockFilterKey = null;
		try {
			toggleEntityFilterKey = new KeyMapping("key.toggleentityfilter", Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.crosshairtargetfilter");
			toggleBlockFilterKey = new KeyMapping("key.toggleblockfilter", Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.crosshairtargetfilter");
		} catch (Throwable e) {
			Class<?> categoryCls = null;
			Object category = null;
			if (CTFCore.isIntermediary()) {
				try {
					Class<?> identifierCls = Class.forName("net.minecraft.class_2960");
					Object identifier = ReflectionUtil.invokeMethod(identifierCls, null, null, 
							"method_60655", new Class[] {String.class, String.class}, CTFCore.MODID, "togglefilter");
					categoryCls = Class.forName("net.minecraft.class_304$class_11900");
					category = ReflectionUtil.invokeMethod(categoryCls, null, null, 
							"method_74698", new Class[] {identifierCls}, identifier);
				} catch (Throwable e1) {}
			} else {
				try {
					Class<?> identifierCls = MinecraftClientUtil.isGameVersionReached(774/*1.21.11*/) ? 
							Class.forName("net.minecraft.resources.Identifier") : 
							Class.forName("net.minecraft.resources.ResourceLocation");
					Object identifier = ReflectionUtil.invokeMethod(identifierCls, null, null, 
							"fromNamespaceAndPath", new Class[] {String.class, String.class}, CTFCore.MODID, "togglefilter");
					categoryCls = Class.forName("net.minecraft.client.KeyMapping$Category");
					category = ReflectionUtil.invokeMethod(categoryCls, null, null, 
							"register", new Class[] {identifierCls}, identifier);
				} catch (Throwable e1) {}
			}
			toggleEntityFilterKey = createWithCategory("key.toggleentityfilter", categoryCls, category);
			toggleBlockFilterKey = createWithCategory("key.toggleblockfilter", categoryCls, category);
		}
		TOGGLE_ENTITY_FILTER_KEY = toggleEntityFilterKey;
		TOGGLE_BLOCK_FILTER_KEY = toggleBlockFilterKey;
	}
	
	private static KeyMapping createWithCategory(String registryName, Class<?> categoryCls, Object category) {
		return ReflectionUtil.newInstance(KeyMapping.class, 
				new Class[] {String.class, Type.class, int.class, categoryCls}, 
				registryName, Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, category);
	}
}
