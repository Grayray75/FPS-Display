package io.grayray75.fabric.fpsdisplay;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.apache.logging.log4j.LogManager;

import io.grayray75.fabric.fpsdisplay.config.FpsDisplayConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;

@Environment(EnvType.CLIENT)
public class FpsDisplayMod implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().info("Initializing FPS-Display Mod");

		AutoConfig.register(FpsDisplayConfig.class, GsonConfigSerializer::new);
	}
}
