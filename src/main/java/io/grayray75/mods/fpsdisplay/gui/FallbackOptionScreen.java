package io.grayray75.mods.fpsdisplay.gui;

import io.grayray75.mods.fpsdisplay.config.ConfigData;
import io.grayray75.mods.fpsdisplay.config.ConfigManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class FallbackOptionScreen extends Screen {
    protected final Screen parent;

    public FallbackOptionScreen(Screen parent) {
        super(Component.translatable("text.fpsdisplay.options.fallback_title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // create done button
        Button doneButton = Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.onClose();
        }).width(200).pos(this.width / 2 - 100, this.height - 27).build();
        this.addRenderableWidget(doneButton);

        super.init();
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(this.font, this.title, this.width / 2, 20, CommonColors.WHITE);
        context.drawCenteredString(this.font, Component.translatable("text.fpsdisplay.options.fallback_message"), this.width / 2, this.height / 2, CommonColors.WHITE);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }
}
