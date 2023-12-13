package io.grayray75.mods.fpsdisplay;

import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {
    public static final String MOD_ID = "fpsdisplay";

    public static boolean ShowOverlay = true;

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
    }
}
