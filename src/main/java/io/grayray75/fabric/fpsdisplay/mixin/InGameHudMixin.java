package io.grayray75.fabric.fpsdisplay.mixin;

import io.grayray75.fabric.fpsdisplay.FpsDisplayMod;
import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        FpsDisplayConfig config = FpsDisplayMod.CONFIG;

        if (!client.options.debugEnabled && config.enabled && config.textAlpha > 3 && FpsDisplayMod.SHOW_FPS_OVERLAY) {

            String displayString = ((MinecraftClientMixin) client).getCurrentFPS() + " FPS";
            float textPosX = (client.getWindow().getScaledWidth() - client.textRenderer.getWidth(displayString)) * ((float) config.horizontalPosition / 200f);
            float textPosY = (client.getWindow().getScaledHeight() - client.textRenderer.fontHeight) * ((float) config.verticalPosition / 200f);
            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            if (config.drawWithShadows) {
                client.textRenderer.drawWithShadow(matrixStack, displayString, textPosX, textPosY, textColor);
            } else {
                client.textRenderer.draw(matrixStack, displayString, textPosX, textPosY, textColor);
            }
        }
    }
}
