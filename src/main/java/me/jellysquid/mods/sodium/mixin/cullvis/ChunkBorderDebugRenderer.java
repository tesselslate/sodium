package me.jellysquid.mods.sodium.mixin.cullvis;

import me.jellysquid.mods.sodium.client.cullvis.CullState;
import me.jellysquid.mods.sodium.client.cullvis.CullingVisualizer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(net.minecraft.client.render.debug.ChunkBorderDebugRenderer.class)
public abstract class ChunkBorderDebugRenderer {
    @Inject(method = "render", cancellable = true, at = @At("HEAD"))
    private void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo info) {
        CullingVisualizer.draw();
        if (!CullingVisualizer.drawChunkBorders) {
            info.cancel();
        }
    }
}