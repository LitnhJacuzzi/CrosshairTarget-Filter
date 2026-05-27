package net.minecraft.world.entity.player;

import net.minecraft.network.chat.Component;

public abstract class Player {
	public abstract void displayClientMessage(Component message, boolean actionBar);
	public abstract void sendOverlayMessage(Component message);
}
