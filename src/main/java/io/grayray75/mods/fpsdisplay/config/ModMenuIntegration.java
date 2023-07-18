package io.grayray75.mods.fpsdisplay.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import io.grayray75.mods.fpsdisplay.gui.ClothOptionScreen;
import io.grayray75.mods.fpsdisplay.gui.FallbackOptionScreen;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            return parent -> ClothOptionScreen.generateScreen(parent);
        }
        else {
            return parent -> new FallbackOptionScreen(parent);
        }
    }
}
