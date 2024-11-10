package me.jellysquid.mods.sodium.client.cullvis;

import net.minecraft.util.math.Direction;

public class CullInfo {
    // ChunkGraphCuller
    public boolean[] flowDirs;

    // ChunkGraphNode
    public int lastVisibleFrame = -1;
    public long visibilityData;
    public byte cullingState;

    public CullInfo() {
        this.flowDirs = new boolean[6];
    }

    public String stringCullingState() {
        StringBuilder builder = new StringBuilder(6);

        for (Direction dir : Direction.values()) {
            if ((this.cullingState & (1 << dir.getId())) != 0) {
                builder.append(Character.toUpperCase(dir.getName().charAt(0)));
            }
        }

        return builder.toString();
    }
}