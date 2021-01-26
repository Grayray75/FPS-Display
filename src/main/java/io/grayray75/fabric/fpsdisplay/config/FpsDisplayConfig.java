package io.grayray75.fabric.fpsdisplay.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "fpsdisplay")
public class FpsDisplayConfig implements ConfigData {

    public boolean enabled = true;
    
    public boolean drawWithShadows = false;

    @ConfigEntry.ColorPicker
    public int textColor = 0xEEEEEE;
}
