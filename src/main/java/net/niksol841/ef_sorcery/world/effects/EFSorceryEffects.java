package net.niksol841.ef_sorcery.world.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niksol841.ef_sorcery.main.ef_sorcery;

public class EFSorceryEffects {
    public static final DeferredRegister<MobEffect> EFFECTS;
    public static final RegistryObject<MobEffect> BLACK_FLASH_BUFF_EFFECT;
    public static final RegistryObject<MobEffect> BLACK_FLASH_DEBUFF_EFFECT;

    static {
        EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ef_sorcery.MODID);
        BLACK_FLASH_BUFF_EFFECT = EFFECTS.register("black_flash_buff_effect", () -> {
            return new BlackFlashBuffEffect(MobEffectCategory.BENEFICIAL, 16185078);
        });
        BLACK_FLASH_DEBUFF_EFFECT = EFFECTS.register("black_flash_debuff_effect", () -> {
            return  new BlackFlashDebuffEffect(MobEffectCategory.HARMFUL, 16185078);
        });
    }
}
