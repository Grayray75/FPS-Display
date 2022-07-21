package io.grayray75.fabric.fpsdisplay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.grayray75.fabric.fpsdisplay.FpsDisplayMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static FpsDisplayConfig config;

    public static FpsDisplayConfig getConfig() {
        return config;
    }

    private static File getConfigFile() {
        Path path = Paths.get(FabricLoader.getInstance().getConfigDirectory().toString(), FpsDisplayMod.MOD_ID + ".json");
        return new File(path.toString());
    }

    public static FpsDisplayConfig loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getConfigFile()));
            FpsDisplayConfig parsed = gson.fromJson(br, FpsDisplayConfig.class);

            if (parsed != null) {
                config = parsed;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load FPS-Display configuration file; reverting to defaults.");
            e.printStackTrace();
        }

        if (config == null) {
            config = new FpsDisplayConfig();
            saveConfig();
        }

        return config;
    }

    public static void saveConfig() {
        try {
            String jsonString = gson.toJson(config);
            FileWriter fileWriter = new FileWriter(getConfigFile());
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Couldn't save FPS-Display configuration file.");
            e.printStackTrace();
        }
    }
}
