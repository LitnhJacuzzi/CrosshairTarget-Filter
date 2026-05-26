package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import java.util.function.BiFunction;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(value = BlockGetter.class, priority = 100001)
public interface BlockGetterMixin {
	@ModifyArg(method = "clip", at = @At(value = "INVOKE", target = 
			"Lnet/minecraft/world/level/BlockGetter;traverseBlocks(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Ljava/lang/Object;Ljava/util/function/BiFunction;Ljava/util/function/Function;)Ljava/lang/Object;"))
	private BiFunction<ClipContext, BlockPos, BlockHitResult> wrapBlockPredicate(BiFunction<ClipContext, BlockPos, BlockHitResult> original) { 
		return (clipContext, blockPos) -> {
			if (CTFCore.CONFIG.isBlockFilterEnabled() && CTFCore.isPicking && !CTFCore.evaluateBlock(blockPos)) {
				return null;
			}
			return original.apply(clipContext, blockPos);
		};
	}
}
