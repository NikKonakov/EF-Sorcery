package com.sorcery.epicfightsorcery;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.types.entity.DealDamageEvent;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

/**
 * Entry point of the Epic Fight: Sorcery addon.
 * <p>
 * Epic Fight is declared as a required, "AFTER"-ordered dependency in
 * neoforge.mods.toml, so by the time this constructor runs its classes
 * are already on the classpath and safe to reference directly.
 */
@Mod(EpicFightSorcery.MOD_ID)
public class EpicFightSorcery {

    public static final String MOD_ID = "epicfight_sorcery";

    public EpicFightSorcery(IEventBus modEventBus, ModContainer modContainer) {
        // Register any custom Epic Fight extensible enums (skill slots,
        // living motions, factions, ...) as soon as possible.
        SkillSlot.ENUM_MANAGER.registerEnumCls(MOD_ID, SorcerySkillSlots.class);

        // TODO: register animations (AnimationRegistryEvent), entity patches
        // (EntityPatchRegistryEvent) and skills on modEventBus as the addon
        // grows. See docs/universal_docs/Starting.en.md in the Epic Fight
        // repository for the exact event hooks.

        //Training Material 1.
        EpicFightEventHooks.Entity.DELIVER_DAMAGE_POST.registerEvent(EpicFightSorcery::OnLivingEntityDamaged);
    }

    private static void OnLivingEntityDamaged(DealDamageEvent.Post post) {
        if (post.getEntityPatch().getOriginal() instanceof ServerPlayer p) {
            p.sendSystemMessage(Component.literal("Dealt " + post.getModifiedDamage() + " damage to " + post.getTarget().getName().getString()));
            p.serverLevel().sendParticles(ParticleTypes.CRIT, p.getX(), p.getY() + 1, p.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
        }
    }



}
