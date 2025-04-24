package net.niksol841.ef_sorcery.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import reascer.wom.animation.attacks.SpecialAttackAnimation;
import reascer.wom.gameasset.WOMWeaponColliders;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.StunType;


public class EFSorceryAnimations {
    public static StaticAnimation DIVERGENT_FIST_AUTO_1;
    public static StaticAnimation DIVERGENT_FIST_AUTO_2;
    public static StaticAnimation DIVERGENT_FIST_AUTO_3;
    public static StaticAnimation DIVERGENT_FIST_DASH;
    public static StaticAnimation DIVERGENT_FIST_TRIPLE_KICK_SKILL;
    public static StaticAnimation DIVERGENT_FIST_HEAVY_PUNCH_SKILL;

    public EFSorceryAnimations(){
        // Register the animations
        ef_sorcery.LOGGER.info("Registering EFSorcery Animations");
    }

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(ef_sorcery.MOD_ID, EFSorceryAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;
        DIVERGENT_FIST_AUTO_1 = (new BasicAttackAnimation(0.3F, 0.5F, 0.15F, 1F, null, biped.toolL, "biped/combat/divergent_fist_auto_1",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_AUTO_2 = (new BasicAttackAnimation(0.4F, 1F, 0.15F, 1F, null, biped.toolR, "biped/combat/divergent_fist_auto_2",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_AUTO_3 = (new BasicAttackAnimation(0.7F, 1F, 0.15F, 10F, null, biped.toolL, "biped/combat/divergent_fist_auto_3",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_DASH = (new BasicAttackAnimation(0.08F, 0.05F, 0.15F, 1F, null, biped.toolR, "biped/combat/divergent_fist_dash",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_HEAVY_PUNCH_SKILL = (new BasicAttackAnimation(0.08F, 0.05F, 0.15F, 1F, null, biped.toolR, "biped/combat/divergent_fist_heavy_punch_skill",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_TRIPLE_KICK_SKILL = (new BasicAttackAnimation(0.08F, 0.05F, 0.15F, 1F, null, biped.toolR, "biped/combat/divergent_fist_triple_kick_skill",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
    }
}
