package me.jellysquid.mods.sodium.client.cullvis;

import net.minecraft.util.math.Vec3i;

import java.util.HashMap;
import java.util.HashSet;

public class CullState {
    public HashMap<Vec3i, CullInfo> cullInfo = new HashMap();

    public HashSet<Vec3i> visible = new HashSet<>();

    public boolean drawChunkBorders = true;

    private static CullState instance = new CullState();

    public static CullState getInstance() {
        return instance;
    }
}