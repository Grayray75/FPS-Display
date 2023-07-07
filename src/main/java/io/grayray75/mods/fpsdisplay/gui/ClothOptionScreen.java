package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.Config;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

// https://shedaniel.gitbook.io/cloth-config/

public class ClothOptionScreen {
    public static Screen generateScreen(Screen parent ){
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(Text.translatable("text.fpsdisplay.options.title"));
        builder.setSavingRunnable(() -> {
            ConfigManager.saveConfig();
        });

        Config config = ConfigManager.getConfig();
        Config configDefaults = new Config();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));
        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("text.fpsdisplay.options.enabled"), config.enabled)
                .setDefaultValue(configDefaults.enabled)
                .setSaveConsumer(newValue -> config.enabled = newValue)
                .build());

        general.addEntry(entryBuilder.startFloatField(Text.translatable("text.fpsdisplay.options.textSize"), config.textSize)
                .setDefaultValue(configDefaults.textSize)
                .setSaveConsumer(newValue -> config.textSize = newValue)
                .build());
        general.addEntry(entryBuilder.startColorField(Text.translatable("text.fpsdisplay.options.textColor"), config.textColor)
                .setDefaultValue(config.textColor)
                .setSaveConsumer(newValue -> config.textColor = newValue)
                .build());
        general.addEntry(entryBuilder.startIntField(Text.translatable("text.fpsdisplay.options.textAlpha"), config.textAlpha)
                .setDefaultValue(config.textAlpha)
                .setSaveConsumer(newValue -> config.textAlpha = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("text.fpsdisplay.options.textShadows"), config.textShadows)
                .setDefaultValue(configDefaults.textShadows)
                .setSaveConsumer(newValue -> config.textShadows = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("text.fpsdisplay.options.offsetTop"), config.offsetTop)
                .setDefaultValue(config.offsetTop)
                .setSaveConsumer(newValue -> config.offsetTop = newValue)
                .build());
        general.addEntry(entryBuilder.startIntField(Text.translatable("text.fpsdisplay.options.offsetLeft"), config.offsetLeft)
                .setDefaultValue(config.offsetLeft)
                .setSaveConsumer(newValue -> config.offsetLeft = newValue)
                .build());

        general.addEntry(entryBuilder.startEnumSelector(Text.translatable("text.fpsdisplay.options.keybindMode"), Config.KeyMode.class, config.keybindMode)
                .setDefaultValue(config.keybindMode)
                .setSaveConsumer(newValue -> config.keybindMode = newValue)
                .build());

        return builder.build();
    }
}
