package net.uozzi.edxbt.mixin;

import com.elenai.elenaidodge2.util.DodgeHandler;
import net.bettercombat.api.MinecraftClient_BetterCombat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.uozzi.edxbt.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DodgeHandler.class)
public class DodgeMixin {
    private static final float SWING_PROCESS = ModConfig.SWING_PROCESS.get().floatValue();

    @Redirect(
            method = "handleDodge(Lcom/elenai/elenaidodge2/client/animation/DodgeAnimator$DodgeDirection;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isBlocking()Z"
            )
    )
    private static boolean modifyIfCondition(LocalPlayer player) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft instanceof MinecraftClient_BetterCombat betterCombat) {
            // Compare BetterCombat swing progress with SWING_PROCESS
            if (minecraft.options.keyAttack.isDown() || betterCombat.getUpswingTicks() > 0 || betterCombat.getSwingProgress() < SWING_PROCESS) {
                return true;
            }
        }
        return player.isBlocking();
    }
}

