package io.grayray75.mods.fpsdisplay.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import io.grayray75.mods.fpsdisplay.FpsDisplayMod;
import io.grayray75.mods.fpsdisplay.gui.ClothOptionScreen;
import io.grayray75.mods.fpsdisplay.gui.FallbackOptionScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            return parent -> ClothOptionScreen.generateScreen(parent);
        }
        else {
            return parent -> new FallbackOptionScreen(parent);
        }
    }

    @Override
    public String getModId() {
        return FpsDisplayMod.MOD_ID;
    }
}
