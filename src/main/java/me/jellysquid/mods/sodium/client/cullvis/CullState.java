package me.jellysquid.mods.sodium.client.cullvis;

import net.minecraft.util.math.Vec3i;

import java.util.HashMap;
import java.util.HashSet;

import me.jellysquid.mods.sodium.client.render.chunk.cull.graph.ChunkGraphNode;

public class CullState {
    public HashMap<Vec3i, CullInfo> cullInfo = new HashMap<>();
    public HashSet<Vec3i> visible = new HashSet<>();
    public int activeFrame = -1;

    private static CullState instance = new CullState();

    public static CullState getInstance() {
        return instance;
    }

    public CullInfo getOrAdd(ChunkGraphNode node) {
        Vec3i pos = new Vec3i(node.getChunkX(), node.getChunkY(), node.getChunkZ());

        if (!this.cullInfo.containsKey(pos)) {
            CullInfo info = new CullInfo();

            this.cullInfo.put(pos, info);
            return info;
        }

        return this.cullInfo.get(pos);
    }

    public void reset() {
        this.cullInfo = new HashMap<>();
        this.visible = new HashSet<>();
        this.activeFrame = -1;
    }
}