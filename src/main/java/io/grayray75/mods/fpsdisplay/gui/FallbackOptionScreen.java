package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class FallbackOptionScreen extends Screen {
    protected final Screen parent;
    protected final ConfigData config;

    public FallbackOptionScreen(Screen parent) {
        super(new TranslatableText("text.fpsdisplay.options.fallback_title"));
        this.parent = parent;
        this.config = ConfigManager.getConfig();
    }

    @Override
    protected void init() {
        // create done button
        ButtonWidget doneButton = new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        });
        this.addDrawableChild(doneButton);

        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("text.fpsdisplay.options.fallback_message"), this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
