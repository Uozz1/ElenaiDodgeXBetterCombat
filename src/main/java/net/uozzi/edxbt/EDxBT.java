package net.uozzi.edxbt;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;


@Mod(EDxBT.MODID)
public class EDxBT {
    public static final String MODID = "edxbt";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public EDxBT() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_CONFIG);
        MinecraftForge.EVENT_BUS.register(PlayerFeatherHandler.class);
        MinecraftForge.EVENT_BUS.register(PlayerLoginEventHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
        ModSounds.REGISTRY.register(modEventBus);
        LOGGER.info("{} has been initialized!", MODID);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        int packetId = 0;
        CHANNEL.registerMessage(packetId++,
                PerfectAttackPacket.class,
                PerfectAttackPacket::encode,
                PerfectAttackPacket::decode,
                PerfectAttackPacket::handle);
    }
}

