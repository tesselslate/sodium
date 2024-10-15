package me.jellysquid.mods.sodium.client.cullvis;

import me.jellysquid.mods.sodium.client.util.math.FrustumExtended;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;

import java.util.HashSet;

import static me.jellysquid.mods.sodium.client.cullvis.Draw.*;

public class CullingVisualizer {
    public static CullState state = new CullState();

    public static void draw() {
        start(MinecraftClient.getInstance().gameRenderer.getCamera());
        drawCameraFrustum();
        drawSubchunkBorders();
        drawBfsDirections();
        end();
    }

    private static void drawCameraFrustum() {
        setColor(1.0F, 0.0F, 0.0F, 1.0F);
    }

    private static void drawSubchunkBorders() {
        for (Vec3i chunk : state.visible) {
            setColor(1.0F, 0.0F, 1.0F, 1.0F);
            int cx = chunk.getX() * 16;
            int cy = chunk.getY() * 16;
            int cz = chunk.getZ() * 16;
            int dx = cx + 16;
            int dy = cy + 16;
            int dz = cz + 16;

            // X facing lines
            line(cx, cy, cz, dx, cy, cz);
            line(cx, dy, cz, dx, dy, cz);
            line(cx, cy, dz, dx, cy, dz);
            line(cx, dy, dz, dx, dy, dz);

            // Y facing lines
            line(cx, cy, cz, cx, dy, cz);
            line(dx, cy, cz, dx, dy, cz);
            line(cx, cy, dz, cx, dy, dz);
            line(dx, cy, dz, dx, dy, dz);

            // Z facing lines
            line(cx, cy, cz, cx, cy, dz);
            line(dx, cy, cz, dx, cy, dz);
            line(cx, dy, cz, cx, dy, dz);
            line(dx, dy, cz, dx, dy, dz);
        }
    }

    private static void drawBfsDirections() {
        for (Vec3i chunk : state.cullInfo.keySet()) {
            int cx = chunk.getX() * 16 + 8;
            int cy = chunk.getY() * 16 + 8;
            int cz = chunk.getZ() * 16 + 8;
            CullInfo info = state.cullInfo.get(chunk);
            setColor(0.0F, 1.0F, 0.0F, 0.5F);
            for (int i = 0; i < 6; i++) {
                if (!info.checkDirs[i]) {
                    continue;
                }
                Vector3f vec = Direction.byId(i).getUnitVector();
                line(cx, cy, cz, cx + (int) vec.getX() * 8, cy + (int) vec.getY() * 8, cz + (int) vec.getZ() * 8);
            }
        }
    }
}