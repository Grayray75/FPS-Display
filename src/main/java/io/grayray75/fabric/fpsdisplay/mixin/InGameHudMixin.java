package io.grayray75.fabric.fpsdisplay.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import io.grayray75.fabric.fpsdisplay.FpsDisplayMod;
import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        FpsDisplayConfig config = FpsDisplayMod.CONFIG;

        if (!client.options.debugEnabled && config.enabled && config.textAlpha > 3 && FpsDisplayMod.SHOW_FPS_OVERLAY) {

            String displayString = ((MinecraftClientMixin) client).getCurrentFPS() + " FPS";
            float textPosX = config.offsetLeft;
            float textPosY = config.offsetTop;

            double guiScale = client.window.getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            // Prevent FPS-Display to render outside screenspace
            float maxTextPosX = client.window.getScaledWidth() - client.textRenderer.getStringWidth(displayString);
            float maxTextPosY = client.window.getScaledHeight() - client.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            this.renderText(client.textRenderer, displayString, textPosX, textPosY, textColor, config.textSize, config.drawWithShadows);
        }
    }

    private void renderText(TextRenderer textRenderer, String text, float x, float y, int color, float scale, boolean shadowed) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(x, y, 0);
        GlStateManager.scalef(scale, scale, scale);
        GlStateManager.translatef(-x, -y, 0);

        if (shadowed) {
            textRenderer.drawWithShadow(text, x, y, color);
        } else {
            textRenderer.draw(text, x, y, color);
        }
        GlStateManager.popMatrix();
    }
}
