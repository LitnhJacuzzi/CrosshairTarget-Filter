package org.litnhjacuzzi.crosshairtargetfilter;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants.Type;

import net.minecraft.client.KeyMapping;

public class CTFKeyBindings {
	public static final KeyMapping TOGGLE_ENTITY_FILTER_KEY;
	public static final KeyMapping TOGGLE_BLOCK_FILTER_KEY;
	
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
