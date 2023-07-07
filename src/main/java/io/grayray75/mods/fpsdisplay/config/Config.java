package io.grayray75.mods.fpsdisplay.config;

public class Config {

    public boolean enabled = true;

    public float textSize = 1.0f;
    public int textColor = 0xEEEEEE;
    public int textAlpha = 230;
    public boolean textShadows = false;

    public int offsetTop = 4;
    public int offsetLeft = 4;

    public KeyMode keybindMode = KeyMode.Toggle;

    public enum KeyMode {
        Toggle,
        PushToShow
    }
}
