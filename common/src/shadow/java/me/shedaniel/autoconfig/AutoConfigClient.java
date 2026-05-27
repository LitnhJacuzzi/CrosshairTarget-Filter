package me.shedaniel.autoconfig;

import java.util.function.Supplier;

import net.minecraft.client.gui.screens.Screen;

public abstract class AutoConfigClient {
	public static <T extends ConfigData> Supplier<Screen> getConfigScreen(Class<T> configClass, Screen parent) { return null; }
}
