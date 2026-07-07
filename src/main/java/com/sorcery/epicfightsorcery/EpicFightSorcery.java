package com.sorcery.epicfightsorcery;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import yesman.epicfight.skill.SkillSlot;

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
    }
}
