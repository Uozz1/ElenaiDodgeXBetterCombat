package net.uozzi.edxbt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.player.LocalPlayer;

@Mixin(LocalPlayer.class)
public interface LocalPlayerAccessor {
    @Accessor("handsBusy")
    void setHandsBusy(boolean value);
}
