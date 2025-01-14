package me.jellysquid.mods.sodium.client.cullvis;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;

import java.lang.reflect.Field;

public class Commands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(
                    literal("capture_chunks").executes(ctx -> {
                        captureChunks();
                        return 1;
                    }));

            dispatcher.register(
                    literal("toggle_culling").executes(ctx -> {
                        toggleCulling();
                        return 1;
                    }));

            dispatcher.register(
                    literal("toggle_frustum").executes(ctx -> {
                        toggleFrustumCapture();
                        return 1;
                    }));

            dispatcher.register(
                    literal("toggle_subchunk_info").executes(ctx -> {
                        toggleSubchunkInfo();
                        return 1;
                    }));
        });
    }

    public static void captureChunks() {
        CullingVisualizer.state.cullInfo = CullState.getInstance().cullInfo;
        CullingVisualizer.state.visible = CullState.getInstance().visible;
        CullingVisualizer.state.activeFrame = CullState.getInstance().activeFrame;

        sendChat(new LiteralText("Chunks visible: " + CullingVisualizer.state.visible.size()));
        sendChat(new LiteralText("Active frame: " + CullingVisualizer.state.activeFrame));
    }

    public static void toggleCulling() {
        MinecraftClient instance = MinecraftClient.getInstance();
        instance.chunkCullingEnabled = !instance.chunkCullingEnabled;

        sendChat(new LiteralText("Chunk culling " + (instance.chunkCullingEnabled ? "enabled" : "disabled")));
    }

    public static void toggleFrustumCapture() {
        WorldRenderer renderer = MinecraftClient.getInstance().worldRenderer;

        if (renderer.capturedFrustum == null) {
            renderer.shouldCaptureFrustum = true;
            sendChat(new LiteralText("Captured camera frustum"));
        } else {
            renderer.capturedFrustum = null;
            sendChat(new LiteralText("Cleared camera frustum"));
        }
    }

    public static void toggleSubchunkInfo() {
        CullingVisualizer.shouldDrawSubchunkInfo = !CullingVisualizer.shouldDrawSubchunkInfo;

        sendChat(new LiteralText(
                "Subchunk info " + (CullingVisualizer.shouldDrawSubchunkInfo ? "enabled" : "disabled")));
    }

    private static void sendChat(LiteralText text) {
        MinecraftClient.getInstance().inGameHud.getChatHud()
                .addMessage(new LiteralText("")
                        .append(new TranslatableText("debug.prefix").formatted(Formatting.YELLOW, Formatting.BOLD))
                        .append(" ").append(text));
    }

    private static void sendError(LiteralText text) {
        MinecraftClient.getInstance().inGameHud.getChatHud()
                .addMessage(new LiteralText("")
                        .append(new TranslatableText("debug.prefix").formatted(Formatting.RED, Formatting.BOLD))
                        .append(" ").append(text));
    }
}