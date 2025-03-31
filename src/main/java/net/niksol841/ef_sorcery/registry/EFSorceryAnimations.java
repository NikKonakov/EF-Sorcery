package net.niksol841.ef_sorcery.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import reascer.wom.animation.attacks.SpecialAttackAnimation;
import reascer.wom.gameasset.WOMWeaponColliders;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.StunType;


public class EFSorceryAnimations {
    public static StaticAnimation STAFF_KINGKONG;
    public static StaticAnimation STAFF_CHARYBDIS;

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;
        STAFF_KINGKONG = (new BasicMultipleAttackAnimation(0.05F, "biped/combat/staff_kingkong", biped,
                new AttackAnimation.Phase(0.0F, 0.35F, 0.45F, 0.95F, Float.MAX_VALUE, biped.toolR, null)))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.4F))
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(3.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.0F)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.3F, 0.55F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entityPatch, speed, prevElapsedTime, elapsedTime) -> {
            if (elapsedTime >= 0.6F && elapsedTime < 0.85F) {
                float dpx = (float) entityPatch.getOriginal().getX();
                float dpy = (float) entityPatch.getOriginal().getY();
                float dpz = (float) entityPatch.getOriginal().getZ();

                for(BlockState block = entityPatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz));
                    (block.getBlock() instanceof BushBlock || block.isAir()) && !block.is(Blocks.VOID_AIR);
                    block = entityPatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz))) {
                    --dpy;
                }

                float distanceToGround = (float)Math.max(Math.abs(entityPatch.getOriginal().getY() - (double)dpy) - (double)1.0F, 0.0F);
                return 1.0F - (1.0F / (-distanceToGround - 1.0F) + 1.0F);
            } else {
                return speed;
            }
        });

        STAFF_CHARYBDIS = (new SpecialAttackAnimation(0.1F, "biped/skill/staff_charybdisandscylla", biped,
                new AttackAnimation.Phase(0.0F, 0.1F, 0.14F, 0.15F, 0.15F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.15F, 0.2F, 0.24F, 0.25F, 0.25F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.25F, 0.3F, 0.34F, 0.35F, 0.35F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.35F, 0.4F, 0.44F, 0.45F, 0.45F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.45F, 0.55F, 0.59F, 0.6F, 0.6F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.6F, 0.65F, 0.69F, 0.7F, 0.7F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.7F, 0.75F, 0.79F, 0.8F, 0.8F, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS),
                new AttackAnimation.Phase(0.8F, 0.85F, 0.89F, 0.9F, Float.MAX_VALUE, biped.toolR, WOMWeaponColliders.STAFF_CHARYBDIS)))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 1)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 2)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 3)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 4)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 5)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 6)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F), 7)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(0.8F), 7)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 1)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 2)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 3)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 4)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 5)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 6)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE, 7)
                .addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 2)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.65F);

    }
}
