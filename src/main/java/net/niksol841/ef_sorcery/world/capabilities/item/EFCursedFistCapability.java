package net.niksol841.ef_sorcery.world.capabilities.item;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.registry.EFSorceryAnimations;
import net.niksol841.ef_sorcery.registry.EFSorcerySkills;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapabilityProvider;
import net.niksol841.ef_sorcery.world.capabilities.player.ICursedEnergyCapability;
import net.niksol841.ef_sorcery.world.capabilities.styles.DivergentFistStyles;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

@Mod.EventBusSubscriber(
        modid = ef_sorcery.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class EFCursedFistCapability {
    private final Map<Direction, LazyOptional<ICursedEnergyCapability>> cache = new EnumMap<>(Direction.class);
    public static final Function<Item, CapabilityItem.Builder> CURSED_FISTS = (item) -> {
        CapabilityItem.Builder builder = WeaponCapability.builder()
                .category(CapabilityItem.WeaponCategories.FIST)
                .styleProvider((playerPatch) -> {
                    PlayerPatch<?> tplayerpatch = (PlayerPatch<?>) playerPatch;
                    ICursedEnergyCapability cap = tplayerpatch.getOriginal().getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get();
                    if (!cap.isCursedUser()){
                        return CapabilityItem.Styles.TWO_HAND;
                    }else{
                        return switch (cap.getCEMasteryLevel()) {
                            case 1 -> DivergentFistStyles.DIVERGENT_FIST_NORMAL;
                            case 2 -> DivergentFistStyles.DIVERGENT_FIST_ENHANCED;
                            case 3 -> DivergentFistStyles.DIVERGENT_FIST_AWAKENING;
                            default -> throw new IllegalStateException("EF_SORCERY: Unexpected value: Mastery Level =" + cap.getCEMasteryLevel());
                        };
                    }
        }).collider(ColliderPreset.FIST)
                .hitSound(EpicFightSounds.BLUNT_HIT.get()).hitParticle(EpicFightParticles.HIT_BLUNT.get())
                .canBePlacedOffhand(false)

                /**
                 * Combos
                 */
                .newStyleCombo(CapabilityItem.Styles.TWO_HAND, EFSorceryAnimations.DIVERGENT_FIST_AUTO_1, EFSorceryAnimations.DIVERGENT_FIST_AUTO_2, EFSorceryAnimations.DIVERGENT_FIST_AUTO_3, EFSorceryAnimations.DIVERGENT_FIST_DASH, Animations.FIST_AIR_SLASH)
                .newStyleCombo(DivergentFistStyles.DIVERGENT_FIST_NORMAL)
                .newStyleCombo(DivergentFistStyles.DIVERGENT_FIST_ENHANCED)
                .newStyleCombo(DivergentFistStyles.DIVERGENT_FIST_AWAKENING)
                .newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK)

                /**
                 * Skills
                 */
                .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EFSorcerySkills.SIMPLE_TAIJUTSU_SKILL)
//                .innateSkill(DivergentFistStyles.DIVERGENT_FIST_NORMAL, (itemstack) -> EFSorcerySkills.DIVERGENT_FIST_1)
//                .innateSkill(DivergentFistStyles.DIVERGENT_FIST_ENHANCED, (itemstack) -> EFSorcerySkills.DIVERGENT_FIST_2)
//                .innateSkill(DivergentFistStyles.DIVERGENT_FIST_AWAKENING, (itemstack) -> EFSorcerySkills.DIVERGENT_FIST_3)

                /**
                 * Living Motions Animations
                 */
                .comboCancel((style) -> false)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, EFSorceryAnimations.DIVERGENT_FIST_IDLE)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, EFSorceryAnimations.DIVERGENT_FIST_RUN)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
                .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.BIPED_BLOCK);
        return builder;
    };


    public EFCursedFistCapability() {
    }


    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(ef_sorcery.MOD_ID, "cursed_fists"), CURSED_FISTS);
    }

}