package net.uozzi.edxbt.mixin;

import com.elenai.elenaidodge2.event.ClientEvents;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.elenai.elenaidodge2.client.KeyBinding;

@Mixin(LocalPlayer.class)
public class HandsBusyMixin {

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void forceHandsBusy(CallbackInfo ci) {
        if(ClientEvents.currentCooldown >= 6) {
            ((LocalPlayerAccessor) this).setHandsBusy(true);
        } else {
            ((LocalPlayerAccessor) this).setHandsBusy(false);
        }
    }
}
