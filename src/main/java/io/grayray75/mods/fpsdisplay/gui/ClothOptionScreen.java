package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

// https://shedaniel.gitbook.io/cloth-config/

public class ClothOptionScreen {
    public static Screen generateScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(new TranslatableText("text.fpsdisplay.options.title").asString());
        builder.setSavingRunnable(() -> {
            ConfigManager.saveConfig();
        });

        final ConfigData configDefaults = new ConfigData();
        ConfigData config = ConfigManager.getConfig();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory("General");
        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("text.fpsdisplay.options.enabled").asString(), config.enabled)
                .setDefaultValue(configDefaults.enabled)
                .setSaveConsumer(newValue -> config.enabled = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("text.fpsdisplay.options.advancedStats").asString(), config.advancedStats)
                .setDefaultValue(configDefaults.advancedStats)
                .setSaveConsumer(newValue -> config.advancedStats = newValue)
                .build());

        general.addEntry(entryBuilder.startFloatField(new TranslatableText("text.fpsdisplay.options.textSize").asString(), config.textSize)
                .setDefaultValue(configDefaults.textSize)
                .setSaveConsumer(newValue -> config.textSize = newValue)
                .build());
        general.addEntry(entryBuilder.startColorField(new TranslatableText("text.fpsdisplay.options.textColor").asString(), config.textColor)
                .setDefaultValue(configDefaults.textColor)
                .setSaveConsumer(newValue -> config.textColor = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(new TranslatableText("text.fpsdisplay.options.textAlpha").asString(), config.textAlpha, 0, 255)
                .setDefaultValue(configDefaults.textAlpha)
                .setSaveConsumer(newValue -> config.textAlpha = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("text.fpsdisplay.options.textShadows").asString(), config.textShadows)
                .setDefaultValue(configDefaults.textShadows)
                .setSaveConsumer(newValue -> config.textShadows = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(new TranslatableText("text.fpsdisplay.options.offsetTop").asString(), config.offsetTop)
                .setDefaultValue(configDefaults.offsetTop)
                .setSaveConsumer(newValue -> config.offsetTop = newValue)
                .build());
        general.addEntry(entryBuilder.startIntField(new TranslatableText("text.fpsdisplay.options.offsetLeft").asString(), config.offsetLeft)
                .setDefaultValue(configDefaults.offsetLeft)
                .setSaveConsumer(newValue -> config.offsetLeft = newValue)
                .build());

        general.addEntry(entryBuilder.startEnumSelector(new TranslatableText("text.fpsdisplay.options.keybindMode").asString(), ConfigData.KeyMode.class, config.keybindMode)
                .setDefaultValue(configDefaults.keybindMode)
                .setSaveConsumer(newValue -> config.keybindMode = newValue)
                .build());

        return builder.build();
    }
}
