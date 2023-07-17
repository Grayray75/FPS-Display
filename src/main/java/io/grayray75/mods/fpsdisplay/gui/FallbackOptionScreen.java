package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class FallbackOptionScreen extends Screen {
    protected final Screen parent;
    protected final ConfigData config;

    public FallbackOptionScreen(Screen parent) {
        super(Text.translatable("fpsdisplay.options.fallback_title"));
        this.parent = parent;
        this.config = ConfigManager.getConfig();
    }

    @Override
    protected void init() {
        // create done button
        ButtonWidget doneButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).width(200).position(this.width / 2 - 100, this.height - 27).build();
        this.addDrawableChild(doneButton);

        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("fpsdisplay.options.fallback_message"), this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
