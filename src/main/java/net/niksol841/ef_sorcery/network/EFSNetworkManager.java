package net.niksol841.ef_sorcery.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.niksol841.ef_sorcery.main.ef_sorcery;

public class EFSNetworkManager {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ef_sorcery.MODID, "network_manager"),
            () -> "1",
            "1"::equals,
            "1"::equals);

    public EFSNetworkManager() {

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToClient(MSG message, PacketDistributor.PacketTarget packetTarget) {
        INSTANCE.send(packetTarget, message);
    }

    public static <MSG> void sendToAll(MSG message) {
        sendToClient(message, PacketDistributor.ALL.noArg());
    }

    public static void registerPackets() {
        int id = 0;
    }
}
