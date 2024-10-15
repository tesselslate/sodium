package me.jellysquid.mods.sodium.client.cullvis;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.Vector4f;

class Draw {
    private static Camera camera;
    private static Tessellator tessellator;
    private static BufferBuilder builder;

    // draw state
    private static Vector4f color;

    /*
    Prepares rendering subsystem for drawing various shapes.
     */
    public static void start(Camera cam) {
        camera = cam;

        RenderSystem.enableDepthTest();
        RenderSystem.shadeModel(7425);
        RenderSystem.enableAlphaTest();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.disableTexture();
        RenderSystem.disableBlend();
        RenderSystem.lineWidth(2.0F);

        tessellator = Tessellator.getInstance();
        builder = tessellator.getBuffer();
        builder.begin(1, VertexFormats.POSITION_COLOR);
    }

    /*
    Finishes drawing.
     */
    public static void end() {
        tessellator.draw();
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
        RenderSystem.shadeModel(7424);
        RenderSystem.lineWidth(1.0F);
    }

    public static void setColor(float r, float g, float b, float a) {
        color = new Vector4f(r, g, b, a);
    }

    public static void line(int ax, int ay, int az, int bx, int by, int bz) {
        builder.vertex(
                (double) ax - camera.getPos().x,
                (double) ay - camera.getPos().y,
                (double) az - camera.getPos().z
        ).color(color.getX(), color.getY(), color.getZ(), color.getW()).next();
        builder.vertex(
                (double) bx - camera.getPos().x,
                (double) by - camera.getPos().y,
                (double) bz - camera.getPos().z
        ).color(color.getX(), color.getY(), color.getZ(), color.getW()).next();
    }
}