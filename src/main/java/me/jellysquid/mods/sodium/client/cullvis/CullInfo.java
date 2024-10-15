package me.jellysquid.mods.sodium.client.cullvis;

import net.minecraft.util.math.Direction;

public class CullInfo {
    public boolean culled;

    public boolean[] checkDirs;

    public CullInfo() {
        this.checkDirs = new boolean[6];
    }
}