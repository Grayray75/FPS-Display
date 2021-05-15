package io.grayray75.fabric.fpsdisplay.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return screen -> AutoConfig.getConfigScreen(FpsDisplayConfig.class, screen).get();
    }

    @Override
    public String getModId() {
        return "fpsdisplay";
    }
}
