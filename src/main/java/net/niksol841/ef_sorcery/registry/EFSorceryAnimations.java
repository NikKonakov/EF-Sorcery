package net.niksol841.ef_sorcery.registry;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.niksol841.ef_sorcery.main.*;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.StunType;


public class EFSorceryAnimations {
    public static StaticAnimation DIVERGENT_FIST_IDLE;
    public static StaticAnimation DIVERGENT_FIST_AUTO_1;
    public static StaticAnimation DIVERGENT_FIST_AUTO_2;
    public static StaticAnimation DIVERGENT_FIST_AUTO_3;
    public static StaticAnimation DIVERGENT_FIST_DASH;
    public static StaticAnimation DIVERGENT_FIST_TRIPLE_KICK_SKILL;
    public static StaticAnimation DIVERGENT_FIST_STRONG_STRIKE;
    public static MovementAnimation DIVERGENT_FIST_RUN;

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
        DIVERGENT_FIST_AUTO_1 = (new BasicAttackAnimation(0.2F, 0.6F, 0.8F, 0.4F, null, biped.toolL, "biped/combat/divergent_fist_auto_1",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_AUTO_2 = (new BasicAttackAnimation(0.2F, 0.6F, 0.8F, 0.4F, null, biped.toolR, "biped/combat/divergent_fist_auto_2",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_AUTO_3 = (new BasicAttackAnimation(0.3F, 0.6F, 0.8F, 0.4F, null, biped.toolL, "biped/combat/divergent_fist_auto_3",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
//        DIVERGENT_FIST_DASH = (new DashAttackAnimation(0.3F, 0.05F, 0.15F, 0.4F, 3F, ColliderPreset.HEADBUTT_RAVAGER, biped.toolR, "biped/combat/divergent_fist_dash",  biped)
//                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(4F))
//                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
//                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
//                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
//                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
//                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
//                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false)
//        );
        DIVERGENT_FIST_DASH = (new DashAttackAnimation(0.3F, "biped/combat/divergent_fist_dash", biped, new AttackAnimation.Phase[]{
                new AttackAnimation.Phase(0.0F, 0.2F, 0.0F, 0.3F, 0.2F, biped.toolR, null),
                new AttackAnimation.Phase(0.2F, 0.1F, 1F, 0.3F, 0.3F, biped.toolR, EFSorceryColliders.DIVERGENT_FIST_DASH)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.ROCKET_JUMP.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN),
                new AttackAnimation.Phase(0.3F, 0.3F, 0.7F, 0.3F, 1F, biped.toolR, EFSorceryColliders.DIVERGENT_FIST_DASH)
                        .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.GROUND_SLAM.get())
        }));
        DIVERGENT_FIST_STRONG_STRIKE = (new BasicAttackAnimation(0.3F, 0.2F, 0.4F, 2F, null, biped.toolR, "biped/combat/divergent_fist_heavy_punch_skill",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_TRIPLE_KICK_SKILL = (new BasicAttackAnimation(1F, 0.05F, 0.15F, 1F, null, biped.toolR, "biped/combat/divergent_fist_triple_kick",  biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(2.7F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.AIR_BURST)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
        );
        DIVERGENT_FIST_IDLE = new StaticAnimation(0.1F, true, "biped/living/divergent_fist_idle", biped).addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 0.35F);
        DIVERGENT_FIST_RUN = new MovementAnimation(0.1F, true, "biped/living/divergent_fist_run", biped);

    }
}
