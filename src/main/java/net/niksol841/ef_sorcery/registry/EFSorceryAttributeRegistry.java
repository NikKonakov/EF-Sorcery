package net.niksol841.ef_sorcery.registry;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niksol841.ef_sorcery.main.ef_sorcery;

@Mod.EventBusSubscriber(
        modid = ef_sorcery.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class EFSorceryAttributeRegistry {
    private static final DeferredRegister<Attribute> ATTRIBUTES;
    public static final RegistryObject<Attribute> CE_MAX;
    public static final RegistryObject<Attribute> CE_CURRENT_CAPACITY;
    public static final RegistryObject<Attribute> CE_REGEN_RATE;
    public static final RegistryObject<Attribute> BLACK_FLASH_CAPACITY;

    public EFSorceryAttributeRegistry() {
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    static {
        ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, ef_sorcery.MODID);
        CE_MAX = ATTRIBUTES.register("cursed_energy_max",
                () -> new RangedAttribute(
                        "attribute.ef_sorcery.cursed_energy_max",
                        0.0D, 0.0D, 1024.0D
                ).setSyncable(true));
        CE_CURRENT_CAPACITY = ATTRIBUTES.register("cursed_energy_current_capacity",
                () -> new RangedAttribute(
                        "attribute.ef_sorcery.cursed_energy_current_capacity",
                        0.0D, 0.0D, 1024.0D
                ).setSyncable(true));
        CE_REGEN_RATE = ATTRIBUTES.register("cursed_energy_regen_rate",
                () -> new RangedAttribute(
                        "attribute.ef_sorcery.cursed_energy_regen_rate",
                        0.0D, 0.0D, 1024.0D
                ).setSyncable(true));
        BLACK_FLASH_CAPACITY = ATTRIBUTES.register("black_flash_current_capacity",
                () -> new RangedAttribute(
                        "attribute.ef_sorcery.black_flash_current_capacity",
                        0.0D, 0.0D, 8.0D
                ).setSyncable(true));
    }

}
