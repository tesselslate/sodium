package me.jellysquid.mods.sodium.mixin.cullvis;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.jellysquid.mods.sodium.client.cullvis.Commands;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.LiteralText;

@Mixin(net.minecraft.client.Keyboard.class)
public abstract class Keyboard {
    @Inject(method = "processF3", cancellable = true, at = @At("HEAD"))
    private void processF3(int key, CallbackInfoReturnable<Boolean> info) {
        switch (key) {
            case 87: // w
                Commands.captureChunks();
                info.setReturnValue(true);
                return;
            case 69: // e
                Commands.toggleSubchunkInfo();
                info.setReturnValue(true);
                return;
            case 76: // l
                Commands.toggleCulling();
                info.setReturnValue(true);
                return;
        }
    }

    @Inject(method = "processF3", at = @At("RETURN"))
    private void processF3_showKeybinds(int key, CallbackInfoReturnable<Boolean> info) {
        if (key == 81) { // q
            ChatHud chatHud = MinecraftClient.getInstance().inGameHud.getChatHud();

            chatHud.addMessage(new LiteralText("F3 + W = Capture visible chunks"));
            chatHud.addMessage(new LiteralText("F3 + E = Toggle subchunk info"));
            chatHud.addMessage(new LiteralText("F3 + L = Toggle occlusion culling"));
            return;
        }
    }
}