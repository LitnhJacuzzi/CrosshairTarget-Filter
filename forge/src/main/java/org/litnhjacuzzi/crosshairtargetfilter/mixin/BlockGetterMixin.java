package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import org.litnhjacuzzi.crosshairtargetfilter.CTFCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(value = Entity.class, priority = 100001)
public abstract class BlockGetterMixin {
	@Redirect(method = "pick", at = @At(value = "INVOKE", target = 
			"Lnet/minecraft/world/level/Level;clip(Lnet/minecraft/world/level/ClipContext;)Lnet/minecraft/world/phys/BlockHitResult;"))
	public BlockHitResult redirectBlockGetter(Level level, ClipContext clipContext) { 
		return BlockGetter.traverseBlocks(clipContext.getFrom(), clipContext.getTo(), clipContext, (clipContextx, blockPos) -> {
			if (CTFCore.CONFIG.isBlockFilterEnabled() && CTFCore.isPicking && !CTFCore.evaluateBlock(blockPos)) {
				return null;
			}
			BlockState blockState = level.getBlockState(blockPos);
			FluidState fluidState = level.getFluidState(blockPos);
			Vec3 vec3 = clipContextx.getFrom();
			Vec3 vec32 = clipContextx.getTo();
			VoxelShape voxelShape = clipContextx.getBlockShape(blockState, level, blockPos);
			BlockHitResult blockHitResult = level.clipWithInteractionOverride(vec3, vec32, blockPos, voxelShape, blockState);
			VoxelShape voxelShape2 = clipContextx.getFluidShape(fluidState, level, blockPos);
			BlockHitResult blockHitResult2 = voxelShape2.clip(vec3, vec32, blockPos);
			double d = blockHitResult == null ? Double.MAX_VALUE : clipContextx.getFrom().distanceToSqr(blockHitResult.getLocation());
			double e = blockHitResult2 == null ? Double.MAX_VALUE : clipContextx.getFrom().distanceToSqr(blockHitResult2.getLocation());
			return d <= e ? blockHitResult : blockHitResult2;
		}, clipContextx -> {
			Vec3 target = clipContextx.getTo();
			Vec3 vec3 = clipContextx.getFrom().subtract(target);
			return BlockHitResult.miss(target, Direction.getNearest(vec3.x, vec3.y, vec3.z), 
					new BlockPos(Mth.floor(target.x), Mth.floor(target.y), Mth.floor(target.z)));
		});
	}
}
