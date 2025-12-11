package io.grayray75.mods.fpsdisplay;

import com.mojang.blaze3d.platform.InputConstants;
import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {
    public static final String MOD_ID = "fpsdisplay";

    public static boolean ShowOverlay = true;

    public static FpsHistory FpsHistory = new FpsHistory();

    @Override
    public void onInitializeClient() {
        ConfigData config = ConfigManager.loadConfig();

        KeyMapping.Category keybinCategory = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "category"));
        KeyMapping toggleKeybinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.fpsdisplay.toggleOverlay",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_DONT_CARE,
            keybinCategory));

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            int currentFps = client.getFps();
            FpsHistory.add(currentFps);
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeybinding.consumeClick() && config.keybindMode == ConfigData.KeyMode.Toggle) {
                config.enabled = !config.enabled;
            }
            if (config.keybindMode == ConfigData.KeyMode.PushToShow) {
                ShowOverlay = toggleKeybinding.isDown();
            }
            else {
                ShowOverlay = config.enabled;
            }
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            ConfigManager.saveConfig();
        });
    }
}
