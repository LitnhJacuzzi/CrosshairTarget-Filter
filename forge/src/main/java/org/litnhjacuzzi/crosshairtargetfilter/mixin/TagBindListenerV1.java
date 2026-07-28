package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.tags.TagContainer;

@Mixin(targets = "net.minecraft.tags.StaticTagHelper")
public abstract class TagBindListenerV1 {
	@Inject(method = "m_13242_", at = @At("TAIL"))
	private void onTagBind(TagContainer tagName, CallbackInfo ci) {
		CTFCore.markTagReloaded();
	}
}
