package me.jellysquid.mods.sodium.mixin.features.chunk_rendering;

import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public abstract class MixinBackgroundRenderer {
    @Shadow
    private static float red;

    @Shadow
    private static float blue;

    @Shadow
    private static float green;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V"))
    private static void handleNaNIntensity(CallbackInfo ci) {
        if (Float.isNaN(red)) {
            red = 0;
        }
        if (Float.isNaN(green)) {
            green = 0;
        }
        if (Float.isNaN(blue)) {
            blue = 0;
        }
    }
}
