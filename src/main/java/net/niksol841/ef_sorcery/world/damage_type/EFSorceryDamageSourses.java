package net.niksol841.ef_sorcery.world.damage_type;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.server.level.ServerLevel;
import net.niksol841.ef_sorcery.main.ef_sorcery;

public class EFSorceryDamageSourses {
    public static final ResourceKey<DamageType> BLACK_FLASH_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ef_sorcery.MODID, "black_flash"));

    public static DamageSource getBlackFlashDamage(ServerLevel level) {
        // Access the registry and get the custom DamageType
        return new DamageSource(
                level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(BLACK_FLASH_KEY)
        );
    }
}