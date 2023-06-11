package io.grayray75.fabric.fpsdisplay.mixin;

import io.grayray75.fabric.fpsdisplay.FpsDisplayMod;
import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(DrawContext context, float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        FpsDisplayConfig config = FpsDisplayMod.CONFIG;

        if (!client.options.debugEnabled && config.enabled && config.textAlpha > 3 && FpsDisplayMod.SHOW_FPS_OVERLAY) {

            String displayString = ((MinecraftClientMixin) client).getCurrentFPS() + " FPS";
            int textPosX = config.offsetLeft;
            int textPosY = config.offsetTop;

            double guiScale = client.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            // Prevent FPS-Display to render outside screenspace
            int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(displayString);
            int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            this.renderText(context, client.textRenderer, displayString, textPosX, textPosY, textColor, config.textSize, config.drawWithShadows);
        }
    }

    private void renderText(DrawContext context, TextRenderer textRenderer, String text, int x, int y, int color, float scale, boolean shadowed) {
        MatrixStack matrixStack = context.getMatrices();

        matrixStack.push();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(-x, -y, 0);

        context.drawText(textRenderer, text, x, y, color, shadowed);
        matrixStack.pop();
    }
}
