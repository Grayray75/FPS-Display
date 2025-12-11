package io.grayray75.mods.fpsdisplay.mixin;

import io.grayray75.mods.fpsdisplay.FpsDisplayMod;
import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {
    @Inject(at = @At("TAIL"), method = "render")
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        ConfigData config = ConfigManager.getConfig();

        if (!client.options.hideGui && !client.debugEntries.isOverlayVisible() && config.enabled && config.textAlpha > 3 && FpsDisplayMod.ShowOverlay) {
            String text;
            if (!config.advancedStats) {
                text = client.getFps() + " FPS";
            }
            else {
                text = String.format("%d FPS (%d min | %d avg | %d max)", client.getFps(),
                    FpsDisplayMod.FpsHistory.getMinimum(), FpsDisplayMod.FpsHistory.getAverage(), FpsDisplayMod.FpsHistory.getMaximum());
            }

            int textPosX = config.offsetLeft;
            int textPosY = config.offsetTop;

            double guiScale = client.getWindow().getGuiScale();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            // Prevent text to render outside screenspace
            int maxTextPosX = client.getWindow().getGuiScaledWidth() - client.font.width(text);
            int maxTextPosY = client.getWindow().getGuiScaledHeight() - client.font.lineHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            int textColor = ((config.textAlpha & 0xFF) << 24) | (config.textColor & 0xFFFFFF);

            this.renderText(context, client.font, text, textPosX, textPosY, textColor, config.textSize, config.textShadows);
        }
    }

    @Unique
    private void renderText(GuiGraphics context, Font textRenderer, String text, int x, int y, int color, float scale, boolean shadowed) {
        if (scale != 1.0f) {
            Matrix3x2fStack matrixStack = context.pose();
            matrixStack.pushMatrix();
            matrixStack.translate(x, y);
            matrixStack.scale(scale, scale);
            matrixStack.translate(-x, -y);
            context.drawString(textRenderer, text, x, y, color, shadowed);
            matrixStack.popMatrix();
        }
        else {
            context.drawString(textRenderer, text, x, y, color, shadowed);
        }
    }
}
