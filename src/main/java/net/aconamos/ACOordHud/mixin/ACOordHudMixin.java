package net.aconamos.ACOordHud.mixin;

import net.aconamos.ACOordHud.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ACOordHudMixin {
	@Final
	@Shadow
	private MinecraftClient client;
	private static double format(double value) {
		return ((double)((int)(value * 10))) / 10;
	}
	@Inject(at = @At("HEAD"), method = "render")
	private void Test(MatrixStack matrices, float tickDelta, CallbackInfo callbackInfo) {
		if ( !this.client.options.debugEnabled ) {
			Vec3d pos = this.client.player.getPos();
			this.client.textRenderer.drawWithShadow(matrices, "x: " + format(pos.x), Config.x_offset, Config.y_offset + Config.y_margin * 0, Config.color);
			this.client.textRenderer.drawWithShadow(matrices, "y: " + format(pos.y), Config.x_offset, Config.y_offset + Config.y_margin * 1, Config.color);
			this.client.textRenderer.drawWithShadow(matrices, "z: " + format(pos.z), Config.x_offset, Config.y_offset + Config.y_margin * 2, Config.color);
		}
	}
}
//	private static double round(double value, int precision) {
//		int scale = (int)Math.pow(10.0, precision);
//		return (double)Math.round(value * (double)scale) / (double)scale;
//	}
