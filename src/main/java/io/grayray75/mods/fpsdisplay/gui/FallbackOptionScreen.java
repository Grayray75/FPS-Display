package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
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
        ButtonWidget doneButton = new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, I18n.translate("gui.done"), (button) -> {
            this.minecraft.openScreen(this.parent);
        });
        this.addButton(doneButton);

        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.asFormattedString(), this.width / 2, 20, 0xFFFFFF);
        this.drawCenteredString(this.font, new TranslatableText("text.fpsdisplay.options.fallback_message").asString(), this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(mouseX, mouseY, delta);
    }

    @Override
    public void onClose() {
        this.minecraft.openScreen(this.parent);
    }
}
