package net.aconamos.ACOordHud.mixin;

import net.aconamos.ACOordHud.HudConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    public OptionsScreenMixin() {
        super(new TranslatableText("options"));
    }

    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo callbackInfo) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, Text.of("ACOordHud Config"), (button) -> this.client.setScreen(new HudConfigScreen(this))));
    }
}
