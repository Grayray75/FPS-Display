package io.grayray75.fabric.fpsdisplay.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "fpsdisplay")
public class FpsDisplayConfig implements ConfigData {

    public boolean enabled = true;

    public boolean drawWithShadows = false;

    public float textSize = 1;

    // Unfortunately there is no ColorPicker in this version and I have no clue how to create one.
    // @ConfigEntry.ColorPicker
    public int textColor = 0xEEEEEE;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int textAlpha = 230;

    public int offsetTop = 4;

    public int offsetLeft = 4;

    public boolean holdKeyToShowFps = false;
}
