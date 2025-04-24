package net.niksol841.ef_sorcery.skill.weapon_innate;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.niksol841.ef_sorcery.registry.EFSorceryAnimations;
import net.niksol841.ef_sorcery.skill.EFSSkillDataKeys;
import reascer.wom.gameasset.WOMAnimations;
import yesman.epicfight.api.animation.AttackAnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.network.client.CPExecuteSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class SimpleTaijutsuSkill extends WeaponInnateSkill {
    public SimpleTaijutsuSkill(Builder builder) {
        super(builder);
        builder.setResource(Resource.WEAPON_CHARGE);
        builder.setActivateType(ActivateType.ONE_SHOT);
        builder.setCategory(SkillCategories.WEAPON_INNATE);
    }

    @OnlyIn(Dist.CLIENT)
    public Object getExecutionPacket(LocalPlayerPatch executor, FriendlyByteBuf args) {
        Input input = executor.getOriginal().input;
        int forward = input.up ? 1 : 0;
        int backward = input.down ? -1 : 0;
        int left = input.left ? 1 : 0;
        int right = input.right ? -1 : 0;
        int vertical = forward + backward;
        int horizon = left + right;
        int animation;
        if (vertical == 0) {
            if (horizon == 0) {
                animation = -3;
            } else {
                animation = horizon >= 0 ? 0 : 1;
            }
        } else {
            animation = -3;
        }

        CPExecuteSkill packet = new CPExecuteSkill(executor.getSkill(this).getSlotId());
        packet.getBuffer().writeInt(animation);
        return packet;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
    }

    @Override
    public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args) {
        int i = args.readInt();
        ServerPlayer player = executor.getOriginal();
        if (player.onGround() || player.isInWater() || !(player.fallDistance < 0.1F) || !player.level().isEmptyBlock(player.blockPosition().below()) && !(player.yo - (double)player.blockPosition().getY() > 0.2)){
            if (player.isSprinting()){
                executor.playAnimationSynchronized(EFSorceryAnimations.DIVERGENT_FIST_HEAVY_PUNCH_SKILL, 0F);
            }
        }
    }

    @Override
    public boolean isExecutableState(PlayerPatch<?> executor) {
        return true;
    }
}
