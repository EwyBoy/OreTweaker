package com.ewyboy.oretweaker.mixin;

import com.ewyboy.oretweaker.config.Settings;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseGeneratorSettings.class)
public class VeinKiller {

    @Inject(method = "oreVeinsEnabled()Z", at = @At("RETURN"), cancellable = true)
    private void oreVeinsEnabled(CallbackInfoReturnable<Boolean> callbackReturnable) {
        if (Settings.SETTINGS.disbaleLargeVeins.get()) {
            callbackReturnable.setReturnValue(false);
        }
    }

}
