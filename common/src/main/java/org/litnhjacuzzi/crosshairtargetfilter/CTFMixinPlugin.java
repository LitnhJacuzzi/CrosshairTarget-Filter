package org.litnhjacuzzi.crosshairtargetfilter;

import static org.litnhjacuzzi.crosshairtargetfilter.MinecraftProtocolVersions.*;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.google.common.collect.Lists;

public class CTFMixinPlugin implements IMixinConfigPlugin {
	private static final List<String> validMixins;
	
	static {
		validMixins = Lists.newArrayList(
				"MinecraftClientMixin",
				"BlockGetterMixin",
				"BlockMixin",
				"EntityTypeMixin",
				"MobCategoryMixin");
		
		if (MinecraftClientUtil.isGameVersionReached(v1_21_11)) {
			validMixins.add("LocalPlayerMixin");
		} else {
			validMixins.add("GameRendererMixin");
		}
		
		if (MinecraftClientUtil.isGameVersionReached(v1_18_2)) {
			validMixins.add("TagBindListenerV2");
		} else {
			validMixins.add("TagBindListenerV1");
		}
	}

	@Override
	public List<String> getMixins() {
		return validMixins;
	}

	public void onLoad(String mixinPackage) {}
	public String getRefMapperConfig() { return null; }
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) { return true; }
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
