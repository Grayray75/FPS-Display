package io.grayray75.fabric.fpsdisplay;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;

import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Keyboard;

import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().info("Initializing FPS-Display Mod");

		KeyBinding binding_toggleOverlay = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle FPS overlay", Keyboard.KEY_NONE, "FPS-Display"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (binding_toggleOverlay.wasPressed()) {
				FpsDisplayConfig.enabled = !FpsDisplayConfig.enabled;
			}
		});
	}
}
