package io.grayray75.mods.fpsdisplay;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import io.grayray75.mods.fpsdisplay.mixin.MinecraftClientAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.legacyfabric.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {
    public static final String MOD_ID = "fpsdisplay";

    public static boolean ShowOverlay = true;

    public static FpsHistory FpsHistory = new FpsHistory();

    @Override
    public void onInitializeClient() {
        ConfigData config = ConfigManager.loadConfig();

        KeyBinding toggleKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fpsdisplay.toggleOverlay",
                Keyboard.KEY_NONE,
                "key.fpsdisplay.category"));

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            int currentFps = ((MinecraftClientAccessor) client).getCurrentFps();
            FpsHistory.add(currentFps);
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeybinding.wasPressed() && config.keybindMode == ConfigData.KeyMode.Toggle) {
                config.enabled = !config.enabled;
            }
            if (config.keybindMode == ConfigData.KeyMode.PushToShow) {
                ShowOverlay = toggleKeybinding.isPressed();
            }
            else {
                ShowOverlay = config.enabled;
            }
        });
    }
}
