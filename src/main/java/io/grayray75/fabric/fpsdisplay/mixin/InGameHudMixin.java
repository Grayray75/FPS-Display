package io.grayray75.fabric.fpsdisplay.mixin;

import io.grayray75.fabric.fpsdisplay.FpsDisplayMod;
import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import net.minecraft.client.MinecraftClient;
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
            float textPosX = ((client.getWindow().getScaledWidth() - client.textRenderer.getStringWidth(displayString) - 2)) * ((float) config.horizontalPosition / 200f) + 2;
            float textPosY = ((client.getWindow().getScaledHeight() - client.textRenderer.fontHeight) - 2) * ((float) config.verticalPosition / 200f) + 2;
            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            if (config.drawWithShadows) {
                client.textRenderer.drawWithShadow(displayString, textPosX, textPosY, textColor);
            } else {
                client.textRenderer.draw(displayString, textPosX, textPosY, textColor);
            }
        }
    }
}
