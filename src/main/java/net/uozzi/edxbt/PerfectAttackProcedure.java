package net.uozzi.edxbt;

import com.elenai.elenaidodge2.event.ClientEvents;
import net.bettercombat.api.MinecraftClient_BetterCombat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.world.level.Level;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class PerfectAttackProcedure {
    private static boolean dotheshit = true;
    private static int cooldown = 0;
    private static int ticksLeftToSend = 0;

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof LocalPlayer player) {
            execute(player);
        }
    }

    private static void execute(LocalPlayer player) {
        Level world = player.level();
        int particles = ModConfig.PARTICLES.get().intValue();
        if (world.isClientSide()) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft instanceof MinecraftClient_BetterCombat betterCombat) {
                if (cooldown > 0) {
                    cooldown--;
                    if (cooldown == 0) {
                        dotheshit = true;
                    }
                }
                if (ClientEvents.currentCooldown > 12 && betterCombat.getUpswingTicks() > 0) {
                    if (dotheshit) {
                        ticksLeftToSend = particles;
                        dotheshit = false;
                        cooldown = 8;
                    }
                }

                if (ticksLeftToSend > 0) {
                    boolean shouldPlaySound = ticksLeftToSend == particles; // suono SOLO al primo tick
                    EDxBT.CHANNEL.sendToServer(new PerfectAttackPacket(shouldPlaySound));
                    ticksLeftToSend--;
                }
            }
        }
    }
}

