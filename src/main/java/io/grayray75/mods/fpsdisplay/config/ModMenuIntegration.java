package io.grayray75.mods.fpsdisplay.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import io.grayray75.mods.fpsdisplay.gui.FallbackOptionScreen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new FallbackOptionScreen(parent);
    }
}
