package net.niksol841.ef_sorcery.events;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.world.capabilities.player.BlackFlashCountPlayerCapability;
import net.niksol841.ef_sorcery.world.capabilities.player.BlackFlashCountPlayerCapabilityProvider;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapability;

@Mod.EventBusSubscriber(modid = ef_sorcery.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<LivingEntity> event){
        if (event.getObject() instanceof Player){
            if (!event.getObject().getCapability(BlackFlashCountPlayerCapabilityProvider.PLAYER_BLACK_FLASH).isPresent()){
                event.addCapability(new ResourceLocation(ef_sorcery.MODID, "black_flash_count"), new BlackFlashCountPlayerCapabilityProvider());
            }
//            if (!event.getObject().getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY).isPresent()){
//                event.addCapability(new ResourceLocation(ef_sorcery.MODID, "cursed_energy_capacity"), new CursedEnergyPlayerCapabilityProvider());
//            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(BlackFlashCountPlayerCapability.class);
        event.register(CursedEnergyPlayerCapability.class);
    }
}
