package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {
	@Inject(method = { "runTick", "m_91383_" }, at = @At("HEAD"))
	public void renderStart(boolean tick, CallbackInfo ci) {
		CTFCore.checkTagReloaded();
	}
}
