package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import java.util.function.Predicate;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.HitResult;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
	@Inject(method = { "pick", "method_76763" }, at = @At("HEAD"))
	private static void markPicking(Entity cameraEntity, double blockInteractionRange, double entityInteractionRange, float partialTicks, CallbackInfoReturnable<HitResult> ci) {
		CTFCore.isPicking = true;
	}
	
	@Inject(method = { "pick", "method_76763" }, at = @At("TAIL"))
	private static void unmarkPicking(Entity cameraEntity, double blockInteractionRange, double entityInteractionRange, float partialTicks, CallbackInfoReturnable<HitResult> ci) {
		CTFCore.isPicking = false;
	}
	
	@ModifyArg(method = { "pick", "method_76763" }, at = @At(value = "INVOKE", target = 
			"Lnet/minecraft/world/entity/projectile/ProjectileUtil;getEntityHitResult(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;D)Lnet/minecraft/world/phys/EntityHitResult;"))
	private static Predicate<Entity> modifyEntityPredicate(Predicate<Entity> original) { 
		return entity -> original.test(entity) && (!CTFCore.CONFIG.isEntityFilterEnabled() || CTFCore.evaluateEntity(entity));
	}
}
