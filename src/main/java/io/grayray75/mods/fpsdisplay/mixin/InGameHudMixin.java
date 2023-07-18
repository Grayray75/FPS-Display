package io.grayray75.mods.fpsdisplay.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.grayray75.mods.fpsdisplay.FpsDisplayMod;
import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
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
        ConfigData config = ConfigManager.getConfig();

        if (!client.options.debugEnabled && config.enabled && config.textAlpha > 3 && FpsDisplayMod.ShowOverlay) {
            String text;
            if (!config.advancedStats) {
                text = ((MinecraftClientAccessor) client).getCurrentFps() + " FPS";
            }
            else {
                text = String.format("%d FPS (%d min | %d avg | %d max)", ((MinecraftClientAccessor) client).getCurrentFps(),
                        FpsDisplayMod.FpsHistory.getMinimum(), FpsDisplayMod.FpsHistory.getAverage(), FpsDisplayMod.FpsHistory.getMaximum());
            }

            int textPosX = config.offsetLeft;
            int textPosY = config.offsetTop;

            double guiScale = client.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            // Prevent text to render outside screenspace
            int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getStringWidth(text);
            int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            this.renderText(client.textRenderer, text, textPosX, textPosY, textColor, config.textSize, config.textShadows);
        }
    }

    private void renderText(TextRenderer textRenderer, String text, int x, int y, int color, float scale, boolean shadowed) {
        if (scale != 1.0f) {
            RenderSystem.pushMatrix();
            RenderSystem.translatef(x, y, 0);
            RenderSystem.scalef(scale, scale, scale);
            RenderSystem.translatef(-x, -y, 0);

            if (shadowed) {
                textRenderer.drawWithShadow(text, x, y, color);
            } else {
                textRenderer.draw(text, x, y, color);
            }

            RenderSystem.popMatrix();
        }
        else {
            if (shadowed) {
                textRenderer.drawWithShadow(text, x, y, color);
            } else {
                textRenderer.draw(text, x, y, color);
            }
        }
    }
}
