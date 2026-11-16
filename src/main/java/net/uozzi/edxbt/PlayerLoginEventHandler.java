package net.uozzi.edxbt;

import com.elenai.feathers.api.FeathersHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerLoginEventHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().isLocalPlayer()) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            int maxFeathers = ModConfig.MAX_FEATHERS.get();
            FeathersHelper.setMaxFeathers(player,maxFeathers);
        }
    }
}
