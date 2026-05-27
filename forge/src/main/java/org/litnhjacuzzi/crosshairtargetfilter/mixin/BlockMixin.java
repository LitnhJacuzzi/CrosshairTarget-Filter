package org.litnhjacuzzi.crosshairtargetfilter.mixin;

import org.litnhjacuzzi.crosshairtargetfilter.FilterTarget;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.block.Block;

@Mixin(Block.class)
public abstract class BlockMixin implements FilterTarget {
	private boolean ctf$isListed = false;
	
	@Override
	public void ctf$markListed() {
		ctf$isListed = true;
	}
	
	@Override
	public void ctf$markUnlisted() {
		ctf$isListed = false;
	}
	
	@Override
	public boolean ctf$isListed() {
		return ctf$isListed;
	}
}
