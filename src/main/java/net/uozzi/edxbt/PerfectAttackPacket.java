package net.uozzi.edxbt;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PerfectAttackPacket {
    private final boolean playSound;

    public PerfectAttackPacket(boolean playSound) {
        this.playSound = playSound;
    }

    public static void encode(PerfectAttackPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.playSound);
    }

    public static PerfectAttackPacket decode(FriendlyByteBuf buf) {
        return new PerfectAttackPacket(buf.readBoolean());
    }

    public static void handle(PerfectAttackPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {

                ServerLevel serverWorld = sender.serverLevel();
                double x = sender.getX();
                double y = sender.getY() + 1;
                double z = sender.getZ();

                boolean showParticle = ModConfig.SHOW_PARTICLE.get();
                boolean useCustomSound = ModConfig.USE_CUSTOM_SOUND.get();
                float volume = ModConfig.PERFECT_ATTACK_VOLUME.get().floatValue();

                // particle
                if (showParticle == true) {
                    serverWorld.sendParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, 1, 0, 0, 0, 0);
                }

                // Riproduci il suono appropriato solo al primo tick
                if (msg.playSound) {
                    SoundEvent soundToPlay = useCustomSound
                            ? ModSounds.SPECIAL_ATTACK.get()
                            : SoundEvents.PLAYER_ATTACK_SWEEP;

                    serverWorld.playSound(null, sender.blockPosition(), soundToPlay,
                            SoundSource.PLAYERS, volume, 1.0F);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}




