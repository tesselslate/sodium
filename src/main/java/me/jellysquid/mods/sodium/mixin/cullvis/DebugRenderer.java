package me.jellysquid.mods.sodium.mixin.cullvis;

import me.jellysquid.mods.sodium.client.cullvis.CullingVisualizer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.debug.DebugRenderer.class)
public abstract class DebugRenderer {
    @Inject(method = "render", at = @At("HEAD"))
    private void render(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY,
            double cameraZ, CallbackInfo info) {
        if (CullingVisualizer.shouldDrawSubchunkInfo) {
            CullingVisualizer.drawSubchunkBorders();
            CullingVisualizer.drawBfsDirections();
            CullingVisualizer.drawSubchunkInfo();
        }
    }
}