package io.grayray75.mods.fpsdisplay;

import io.grayray75.mods.fpsdisplay.config.Config;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {
    public static final String MOD_ID = "fpsdisplay";

    public static boolean ShowOverlay = true;

    @Override
    public void onInitializeClient() {
        Config config = ConfigManager.loadConfig();

        KeyBinding toggleKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fpsdisplay.toggleOverlay",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_DONT_CARE,
                "key.fpsdisplay.category"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeybinding.wasPressed() && config.keybindMode == Config.KeyMode.Toggle) {
                config.enabled = !config.enabled;
            }
            if (config.keybindMode == Config.KeyMode.PushToShow) {
                ShowOverlay = toggleKeybinding.isPressed();
            } else {
                ShowOverlay = config.enabled;
            }
        });
    }
}
