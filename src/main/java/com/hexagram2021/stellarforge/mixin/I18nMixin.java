package com.hexagram2021.stellarforge.mixin;

import com.hexagram2021.stellarforge.common.config.SFCommonConfig;
import com.hexagram2021.stellarforge.common.util.RegistryChecker;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.locale.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(I18n.class)
public class I18nMixin {
	@Inject(method = "setLanguage", at = @At(value = "TAIL"))
	private static void checkI18n(Language language, CallbackInfo ci) {
		if(SFCommonConfig.ENABLE_REGISTRY_CHECK.get()) {
			RegistryChecker.i18nCheck();
		}
	}
}
