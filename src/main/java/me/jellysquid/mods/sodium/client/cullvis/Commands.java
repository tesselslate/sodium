package me.jellysquid.mods.sodium.client.cullvis;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            // capture_chunks
            dispatcher.register(
                    literal("capture_chunks").executes(ctx -> {
                        CullingVisualizer.state.cullInfo = CullState.getInstance().cullInfo;
                        CullingVisualizer.state.visible = CullState.getInstance().visible;
                        CullingVisualizer.state.activeFrame = CullState.getInstance().activeFrame;
                        ctx.getSource().sendFeedback(new LiteralText("Chunks visible: " + CullingVisualizer.state.visible.size()), false);
                        ctx.getSource().sendFeedback(new LiteralText("Active frame: " + CullingVisualizer.state.activeFrame), false);
                        return 1;
                    })
            );

            // toggle_chunk_borders
            dispatcher.register(
                    literal("toggle_chunk_borders")
                            .executes(ctx -> {
                                CullingVisualizer.drawChunkBorders = !CullingVisualizer.drawChunkBorders;
                                return 1;
                            })
            );

            // toggle_culling
            dispatcher.register(
                    literal("toggle_culling")
                            .executes(ctx -> {
                                MinecraftClient instance = MinecraftClient.getInstance();
                                instance.chunkCullingEnabled = !instance.chunkCullingEnabled;
                                ctx.getSource().sendFeedback(new LiteralText("Set to " + instance.chunkCullingEnabled), false);
                                return 1;
                            })
            );
        });
    }
}