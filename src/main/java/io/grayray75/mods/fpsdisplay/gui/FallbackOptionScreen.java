package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

public class FallbackOptionScreen extends Screen {
    protected final Screen parent;
    protected final String title;
    protected final ConfigData config;

    public FallbackOptionScreen(Screen parent) {
        this.parent = parent;
        this.title = new TranslatableText("text.fpsdisplay.options.fallback_title").asFormattedString();
        this.config = ConfigManager.getConfig();
    }

    public void init() {
        // create done button
        this.buttons.add(new ButtonWidget(200, this.width / 2 - 100, this.height / 6 + 120, I18n.translate("gui.done")));

        super.init();
    }

    protected void buttonClicked(ButtonWidget button) {
        if (!button.active) return;

        if (button.id == 200) {
            this.client.setScreen(this.parent);
        }
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        this.drawCenteredString(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        this.drawCenteredString(this.textRenderer, "Currently there is no config screen for legacy-fabric :(",
                this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(mouseX, mouseY, delta);
    }
}
