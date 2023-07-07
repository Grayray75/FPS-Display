package io.grayray75.mods.fpsdisplay.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.grayray75.mods.fpsdisplay.gui.ClothOptionScreen;
import io.grayray75.mods.fpsdisplay.gui.FallbackOptionScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return parent -> ClothOptionScreen.generateScreen(parent);
        }
        else {
            return parent -> new FallbackOptionScreen(parent);
        }
    }
}
