package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import java.util.List;
import java.util.Map;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.MappedRegistry;
import net.minecraft.tags.TagKey;

@Mixin(MappedRegistry.class)
public abstract class TagBindListenerV2 {
	@Inject(method = { "bindTags", "m_203652_" }, at = @At("TAIL"), require = 0)
	private void onTagBind(Map<?, ?> tags, CallbackInfo ci) {
		CTFCore.markTagReloaded();
	}
	
	//1.21.3~1.21.11
	@Inject(method = { "bindTag", "method_62681" }, at = @At("TAIL"), require = 0)
	private void onTagBind(TagKey<?> tagKey, List<?> list, CallbackInfo ci) {
		CTFCore.markTagReloaded();
	}
}
