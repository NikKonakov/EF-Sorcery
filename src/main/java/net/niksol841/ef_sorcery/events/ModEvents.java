package net.niksol841.ef_sorcery.events;


import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.world.capabilities.player.BlackFlashCountPlayerCapability;
import net.niksol841.ef_sorcery.world.capabilities.player.BlackFlashCountPlayerCapabilityProvider;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapability;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapabilityProvider;

@Mod.EventBusSubscriber(modid = ef_sorcery.MOD_ID)
public class ModEvents {

    public static final ResourceLocation RESOURCE_BLACK_FLASH_COUNT = new ResourceLocation(ef_sorcery.MOD_ID, "black_flash_count");
    public static final ResourceLocation RESOURCE_CURSED_ENERGY_CAPACITY = new ResourceLocation(ef_sorcery.MOD_ID, "cursed_energy_capacity");

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<ServerPlayer> event){
        if (event.getObject() instanceof ServerPlayer){
            if (!event.getObject().getCapability(BlackFlashCountPlayerCapabilityProvider.PLAYER_BLACK_FLASH).isPresent()){
                event.addCapability(RESOURCE_BLACK_FLASH_COUNT, new BlackFlashCountPlayerCapabilityProvider());
            }
            if (!event.getObject().getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).isPresent()){
                event.addCapability(RESOURCE_CURSED_ENERGY_CAPACITY, new CursedEnergyPlayerCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent event){
//        if (event.getServer().getTickCount() == 10){
//            event.getServer().getPlayerList().getPlayers().forEach(player -> {
//                player.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).ifPresent(CursedEnergyPlayerCapability::regenerateCE);
//            });
//        };
        if(!event.level.isClientSide){
            if(event.phase == TickEvent.Phase.END && event.level.getServer() != null){
                if (event.level.getServer().getTickCount() % 20 == 0){
                    if (!(event.level.getServer().getPlayerCount() == 0)){
                        event.level.getServer().getPlayerList().getPlayers().forEach(player -> {
                            player.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).ifPresent(CursedEnergyPlayerCapability::regenerateCE);
                        });
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone playerEvent){
        if (playerEvent.isWasDeath()){
            Player originalContainer = playerEvent.getOriginal();
            Player newContainer = playerEvent.getEntity();
            originalContainer.getCapability(BlackFlashCountPlayerCapabilityProvider.PLAYER_BLACK_FLASH).ifPresent(oldBFData -> {
                newContainer.getCapability(BlackFlashCountPlayerCapabilityProvider.PLAYER_BLACK_FLASH).ifPresent(newData -> {
                    newData.cloneFrom(oldBFData);
                    newData.nullifyBlackFlashCount();
                });
            });
            originalContainer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).ifPresent(oldCEData -> {
                newContainer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).ifPresent(newData -> {
                    newData.cloneFrom(oldCEData);
                    newData.setCE(oldCEData.getMaxCE());
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(BlackFlashCountPlayerCapability.class);
        event.register(CursedEnergyPlayerCapability.class);
    }
}
