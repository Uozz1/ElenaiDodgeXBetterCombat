package net.uozzi.edxbt.mixin;

import com.elenai.elenaidodge2.event.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.bettercombat.api.MinecraftClient_BetterCombat;

@Mixin(LocalPlayer.class)
public abstract class Logs {

    @Shadow public abstract boolean isLocalPlayer();

    @Inject(method = "tick", at = @At("TAIL"))
    private void customTickMethod(CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft instanceof MinecraftClient_BetterCombat betterCombat) {
            if(ClientEvents.currentCooldown > 12 && betterCombat.getUpswingTicks()>0){

                letsGo();
            }
            /*if (trueClientEvents.currentCooldown > 9 && betterCombat.isWeaponSwingInProgress()) {
                perfectDetector(betterCombat.getUpswingTicks(), betterCombat.getSwingProgress(), minecraft);
            } */
        }
    }

    private void letsGo() {
            System.out.println("AAAAAAA");
    }

    private void perfectDetector(int upswingTicks, float swingProgress, Minecraft minecraft) {
        if(minecraft.options.keyAttack.isDown()){
        System.out.println("GO!!! "+minecraft.options.keyAttack.isDown());
        System.out.println("roll cooldown "+ClientEvents.currentCooldown);
        System.out.println("Upswing Ticks "+upswingTicks);
        System.out.println("SWING TICKS!!!! "+swingProgress);
        }
    }
}
