package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import java.util.function.Predicate;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;

@Mixin(value = GameRenderer.class, priority = 100001)
public abstract class GameRendererMixin {
	@Inject(method = "pick(F)V", at = @At("HEAD"))
	public void markPicking(float f, CallbackInfo ci) {
		CTFCore.isPicking = true;
	}
	
	@Inject(method = "pick(F)V", at = @At("TAIL"))
	public void unmarkPicking(float f, CallbackInfo ci) {
		CTFCore.isPicking = false;
	}
	
	@ModifyArg(method = "pick(F)V", at = @At(value = "INVOKE", target = 
			"Lnet/minecraft/world/entity/projectile/ProjectileUtil;getEntityHitResult(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;D)Lnet/minecraft/world/phys/EntityHitResult;"))
	public Predicate<Entity> modifyEntityPredicate(Predicate<Entity> original) {
		return ctf$createFilterPredicate(original);
	}
	
	private Predicate<Entity> ctf$createFilterPredicate(Predicate<Entity> original) {
		return entity -> original.test(entity) && (!CTFCore.CONFIG.isEntityFilterEnabled() || CTFCore.evaluateEntity(entity));
	}
}
