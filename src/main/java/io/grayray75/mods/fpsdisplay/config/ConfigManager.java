package io.grayray75.mods.fpsdisplay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.grayray75.mods.fpsdisplay.FpsDisplayMod;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static ConfigData config;

    public static ConfigData getConfig() {
        return config;
    }

    private static File getFilePath() {
        Path path = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), FpsDisplayMod.MOD_ID + ".json");
        return new File(path.toString());
    }

    public static ConfigData loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFilePath()));
            ConfigData parsed = GSON.fromJson(br, ConfigData.class);
            br.close();

            if (parsed != null) {
                config = parsed;
            }
        } catch (FileNotFoundException ex) {
            LOGGER.info("[" + FpsDisplayMod.MOD_ID + "] " + "Couldn't load configuration file, reverting to defaults!");
        } catch (Exception ex) {
            LOGGER.info("[" + FpsDisplayMod.MOD_ID + "] " + "Couldn't load configuration file, reverting to defaults!");
            ex.printStackTrace();
        }

        if (config == null) {
            config = new ConfigData();
            saveConfig();
        }

        return config;
    }

    public static void saveConfig() {
        try {
            String jsonString = GSON.toJson(config);
            FileWriter fileWriter = new FileWriter(getFilePath());
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.info("[" + FpsDisplayMod.MOD_ID + "] " + "Couldn't save configuration file.");
            e.printStackTrace();
        }
    }
}
