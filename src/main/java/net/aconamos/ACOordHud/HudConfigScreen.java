package net.aconamos.ACOordHud;

import net.aconamos.ACOordHud.Config;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HudConfigScreen extends Screen {
    private final Screen parent;
    private ButtonWidget menuButton;
    protected TextFieldWidget xOffsetWidget;
    protected TextFieldWidget yOffsetWidget;
    protected TextFieldWidget yMarginWidget;
    protected TextFieldWidget colorWidget;

    public HudConfigScreen(Screen parent) {
        super(Text.of("ACOordHud Config"));
        this.parent = parent;
    }

    public final void init() {
        this.menuButton = new ButtonWidget(this.width / 2 - 100, this.height / 2 + 22, 200, 20, ScreenTexts.DONE, (button -> this.client.setScreen(this.parent)));
        this.addDrawableChild(this.menuButton);

        this.xOffsetWidget = new TextFieldWidget(this.textRenderer, this.width / 2 - 48, this.height / 2 - 64 + 20 * 0, 146, 16, this.xOffsetWidget, Text.of("X Offset"));
        this.xOffsetWidget.setChangedListener((value) -> {
            try {
                Config.x_offset = Float.parseFloat(value);
            } catch (Exception e) {
                Config.x_offset = 5.0F;
            }
        });
        this.xOffsetWidget.setText(String.valueOf(Config.x_offset));
        this.addSelectableChild(this.xOffsetWidget);
        this.yOffsetWidget = new TextFieldWidget(this.textRenderer, this.width / 2 - 48, this.height / 2 - 64 + 20 * 1, 146, 16, this.yOffsetWidget, Text.of("Y Offset"));
        this.yOffsetWidget.setChangedListener((value) -> {
            try {
                Config.y_offset = Float.parseFloat(value);
            } catch (Exception e) {
                Config.y_offset = 5.0F;
            }
        });
        this.yOffsetWidget.setText(String.valueOf(Config.y_offset));
        this.addSelectableChild(this.yOffsetWidget);
        this.yMarginWidget = new TextFieldWidget(this.textRenderer, this.width / 2 - 48, this.height / 2 - 64 + 20 * 2, 146, 16, this.yMarginWidget, Text.of("Y Margin"));
        this.yMarginWidget.setChangedListener((value) -> {
            try {
                Config.y_margin = Float.parseFloat(value);
            } catch (Exception e) {
                Config.y_margin = 10.0F;
            }

        });
        this.yMarginWidget.setText(String.valueOf(Config.y_margin));
        this.addSelectableChild(this.yMarginWidget);
        this.colorWidget = new TextFieldWidget(this.textRenderer, this.width / 2 - 48, this.height / 2 - 64 + 20 * 3, 146, 16, this.colorWidget, Text.of("Color"));
        this.colorWidget.setChangedListener((value) -> {
            try {
                Config.color = Integer.parseInt(value, 16);
            } catch (Exception e) { // this is pretty scary code imo, but I haven't a clue what exception it throws
                Config.color = 0xFFFFFF;
            }
        });
        this.colorWidget.setText(Integer.toHexString(Config.color));
        this.addSelectableChild(this.colorWidget);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.client.world == null) {
            this.renderBackground(matrices);
        }
        this.textRenderer.drawWithShadow(matrices, "x_offset: ", this.width / 2 - 98, this.height / 2 + 4 - 64 + 20 * 0, 0xFFFFFF);
        this.xOffsetWidget.render(matrices, mouseX, mouseY, delta);
        this.textRenderer.drawWithShadow(matrices, "y_offset: ", this.width / 2 - 98, this.height / 2 + 4 - 64 + 20 * 1, 0xFFFFFF);
        this.yOffsetWidget.render(matrices, mouseX, mouseY, delta);
        this.textRenderer.drawWithShadow(matrices, "y_margin: ", this.width / 2 - 98, this.height / 2 + 4 - 64 + 20 * 2, 0xFFFFFF);
        this.yMarginWidget.render(matrices, mouseX, mouseY, delta);
        this.textRenderer.drawWithShadow(matrices, "color: ", this.width / 2 - (98 - 6 * 3) - 1, this.height / 2 + 4 - 64 + 20 * 3, 0xFFFFFF);
        this.colorWidget.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void tick() {
        this.xOffsetWidget.tick();
        this.yOffsetWidget.tick();
        this.yMarginWidget.tick();
        this.colorWidget.tick();
    }

    public void removed() {
    }
}
