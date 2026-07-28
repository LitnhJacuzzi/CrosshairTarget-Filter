package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.class_5415;

@Mixin(targets = "net.minecraft.class_5120")
public abstract class TagBindListenerV1 {
	@Inject(method = "method_26771", at = @At("TAIL"))
	private void onTagBind(class_5415 tagName, CallbackInfo ci) {
		CTFCore.markTagReloaded();
	}
}
