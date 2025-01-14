package net.niksol841.ef_sorcery.world.capabilities.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.gameasset.EFSorcerySkills;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mod.EventBusSubscriber(
        modid = "ef_sorcery",
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class EFCursedFistCapability {
    public static final Function<Item, CapabilityItem.Builder> CURSED_FISTS = (item) -> {
        CapabilityItem.Builder builder = WeaponCapability.builder()
                .category(CapabilityItem.WeaponCategories.FIST)
                .styleProvider((playerpatch) -> {
                    PlayerPatch<?> tplayerpatch = (PlayerPatch<?>) playerpatch;
                    return tplayerpatch.getSkill(SkillSlots.WEAPON_INNATE).isActivated() ? CapabilityItem.Styles.OCHS : CapabilityItem.Styles.TWO_HAND;
        }).collider(ColliderPreset.FIST)
                .hitSound(EpicFightSounds.BLUNT_HIT.get()).hitParticle(EpicFightParticles.HIT_BLUNT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.FIST_AUTO1, Animations.FIST_AUTO2, Animations.FIST_AUTO3, Animations.FIST_DASH, Animations.FIST_AIR_SLASH)
                .newStyleCombo(CapabilityItem.Styles.OCHS, Animations.DAGGER_AUTO1, Animations.DAGGER_AUTO2, Animations.DAGGER_AUTO3, Animations.DAGGER_DASH, Animations.DAGGER_AIR_SLASH)
                .newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SPEAR_MOUNT_ATTACK)
                .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EFSorcerySkills.DIVERGENT_FIST)
                .innateSkill(CapabilityItem.Styles.OCHS, (itemstack) -> EFSorcerySkills.DIVERGENT_FIST)
                .comboCancel((style) -> false)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_IDLE)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_SPEAR)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, Animations.BIPED_IDLE)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.CHASE, Animations.BIPED_WALK)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, Animations.BIPED_HOLD_SPEAR)
                .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
        return builder;
    };


    public EFCursedFistCapability() {
    }


    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation("ef_sorcery", "cursed_fists"), CURSED_FISTS);
    }

}