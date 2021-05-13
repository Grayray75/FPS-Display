package io.grayray75.fabric.fpsdisplay.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(FpsDisplayConfig.class, parent).get();
    }

    @Override
    public String getModId() {
        return "fpsdisplay";
    }
}
