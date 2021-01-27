package io.grayray75.fabric.fpsdisplay.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (!client.options.debugEnabled && FpsDisplayConfig.enabled) {

            String displayString = ((MinecraftClientMixin) client).getCurrentFPS() + " FPS";
            int textPosX = 2;
            int textPosY = 2;
            int textColor = 0xDDEEEEEE;

            client.textRenderer.draw(displayString, textPosX, textPosY, textColor);
        }
    }
}