package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

// https://shedaniel.gitbook.io/cloth-config/

public class ClothOptionScreen {
    public static Screen generateScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(Component.translatable("text.fpsdisplay.options.title"));
        builder.setSavingRunnable(() -> {
            ConfigManager.saveConfig();
        });

        final ConfigData configDefaults = new ConfigData();
        ConfigData config = ConfigManager.getConfig();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Component.nullToEmpty("General"));
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.fpsdisplay.options.enabled"), config.enabled)
                .setDefaultValue(configDefaults.enabled)
                .setSaveConsumer(newValue -> config.enabled = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.fpsdisplay.options.advancedStats"), config.advancedStats)
                .setDefaultValue(configDefaults.advancedStats)
                .setSaveConsumer(newValue -> config.advancedStats = newValue)
                .build());

        general.addEntry(entryBuilder.startFloatField(Component.translatable("text.fpsdisplay.options.textSize"), config.textSize)
                .setDefaultValue(configDefaults.textSize)
                .setSaveConsumer(newValue -> config.textSize = newValue)
                .build());
        general.addEntry(entryBuilder.startColorField(Component.translatable("text.fpsdisplay.options.textColor"), config.textColor)
                .setDefaultValue(configDefaults.textColor)
                .setSaveConsumer(newValue -> config.textColor = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Component.translatable("text.fpsdisplay.options.textAlpha"), config.textAlpha, 0, 255)
                .setDefaultValue(configDefaults.textAlpha)
                .setSaveConsumer(newValue -> config.textAlpha = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.fpsdisplay.options.textShadows"), config.textShadows)
                .setDefaultValue(configDefaults.textShadows)
                .setSaveConsumer(newValue -> config.textShadows = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Component.translatable("text.fpsdisplay.options.offsetTop"), config.offsetTop)
                .setDefaultValue(configDefaults.offsetTop)
                .setSaveConsumer(newValue -> config.offsetTop = newValue)
                .build());
        general.addEntry(entryBuilder.startIntField(Component.translatable("text.fpsdisplay.options.offsetLeft"), config.offsetLeft)
                .setDefaultValue(configDefaults.offsetLeft)
                .setSaveConsumer(newValue -> config.offsetLeft = newValue)
                .build());

        general.addEntry(entryBuilder.startEnumSelector(Component.translatable("text.fpsdisplay.options.keybindMode"), ConfigData.KeyMode.class, config.keybindMode)
                .setDefaultValue(configDefaults.keybindMode)
                .setSaveConsumer(newValue -> config.keybindMode = newValue)
                .build());

        return builder.build();
    }
}
