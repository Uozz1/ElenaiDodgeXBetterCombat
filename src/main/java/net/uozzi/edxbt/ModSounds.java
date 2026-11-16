package net.uozzi.edxbt;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EDxBT.MODID);

    public static final RegistryObject<SoundEvent> SPECIAL_ATTACK = REGISTRY.register("special_attack",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(EDxBT.MODID, "special_attack")));
}
