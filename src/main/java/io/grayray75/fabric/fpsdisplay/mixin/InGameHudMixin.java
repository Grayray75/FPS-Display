package io.grayray75.fabric.fpsdisplay.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        FpsDisplayConfig config = AutoConfig.getConfigHolder(FpsDisplayConfig.class).getConfig();

        if (!client.options.debugEnabled && config.enabled && config.textAlpha > 3) {

            String displayString = ((MinecraftClientMixin) client).getCurrentFPS() + " FPS";
            float textPosX = 2;
            float textPosY = 2;
            int textColor = ((config.textAlpha & 0xFF) << 24) | config.textColor;

            if (config.drawWithShadows) {
                client.textRenderer.drawWithShadow(displayString, textPosX, textPosY, textColor);
            } else {
                client.textRenderer.draw(displayString, textPosX, textPosY, textColor);
            }
        }
    }
}