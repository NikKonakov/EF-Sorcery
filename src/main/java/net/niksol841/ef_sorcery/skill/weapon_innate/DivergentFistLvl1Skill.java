package net.niksol841.ef_sorcery.skill.weapon_innate;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.skill.WOMSkillDataKeys;
import yesman.epicfight.api.animation.AttackAnimationProvider;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.UUID;

public class DivergentFistLvl1Skill extends ConditionalWeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f3f6b3b3-4b3b-4b3b-4b3b-4b3b4b3b4b3b");

    public DivergentFistLvl1Skill(Builder builder) {
        super(builder);
        builder.setSelector((executor) -> {
            int combo = (Integer)executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue((SkillDataKey) WOMSkillDataKeys.COMBO.get());
            return combo;
        });
        builder.setAnimations(() -> (yesman.epicfight.api.animation.types.AttackAnimation) WOMAnimations.ENDERBLASTER_TWOHAND_TOMAHAWK, () -> (yesman.epicfight.api.animation.types.AttackAnimation) WOMAnimations.ENDERBLASTER_TWOHAND_TOMAHAWK);
        builder.setActivateType(ActivateType.ONE_SHOT);
        builder.setCategory(SkillCategories.WEAPON_INNATE);
        builder.setResource(Resource.WEAPON_CHARGE);
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
        if (executor.isInAir()){
            executor.getOriginal().sendSystemMessage(Component.literal("24 Carrots in the air"));
        } else {
            executor.getOriginal().sendSystemMessage(Component.literal("24 Carrots on the ground"));
        }
        if (executor.getOriginal().isBlocking()){
            executor.getOriginal().sendSystemMessage(Component.literal("Blocking"));
        } else {
            executor.getOriginal().sendSystemMessage(Component.literal("Not Blocking"));
        }
        executor.playAnimationSynchronized(WOMAnimations.KATANA_GESSHOKU, 0.0F);
    }

    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        return this;
    }

    @Override
    public void executeOnClient(LocalPlayerPatch executor, FriendlyByteBuf args) {
        super.executeOnClient(executor, args);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
    }

    @Override
    public void cancelOnServer(ServerPlayerPatch executor, FriendlyByteBuf args) {
        super.cancelOnServer(executor, args);
    }

    @Override
    public boolean isExecutableState(PlayerPatch<?> executor) {
        return true;
    }
}