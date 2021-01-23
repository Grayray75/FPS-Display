package io.grayray75.fabric.fpsdisplay;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.apache.logging.log4j.LogManager;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().info("Initializing FPS-Display Mod");
	}
}
