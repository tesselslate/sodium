package me.jellysquid.mods.sodium.mixin.cullvis;

import net.minecraft.server.command.FillCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FillCommand.class)
public abstract class CommandFill {
    @ModifyConstant(method = "execute", constant = @Constant(intValue = 32768))
    private static int execute(int original) {
        return 1 << 30;
    }
}