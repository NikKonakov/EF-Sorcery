package net.niksol841.ef_sorcery.skill;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import reascer.wom.gameasset.WOMAnimations;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.List;

public class DivergentFistSkill extends WeaponInnateSkill {
    public DivergentFistSkill(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        float pConsumption = 5.0F;
        if (!container.getExecuter().isLogicalClient()) {
            this.setConsumption(container, pConsumption);
            this.setConsumptionSynchronize((ServerPlayerPatch) container.getExecuter(), pConsumption);
        }
        container.setResource(pConsumption);
    }

    @Override
    public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args) {
        if (executor.consumeForSkill(this, Resource.STAMINA, this.consumption) || executor.getOriginal().isCreative()) {
            executor.playAnimationSynchronized(WOMAnimations.TORMENT_BERSERK_AIRSLAM, 0.0F);
            super.executeOnServer(executor, args);
        }
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemstack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        return super.getTooltipOnItem(itemstack, cap, playerCap);
    }

    @Override
    public boolean isExecutableState(PlayerPatch<?> executor) {
        executor.updateEntityState();
        EntityState playerState = executor.getEntityState();
        return !(executor.getOriginal().isFallFlying()
                && executor.currentLivingMotion != LivingMotions.FALL
                && playerState.canUseSkill()
                && executor.getEntityState().canBasicAttack());
    }

    @Override
    public Skill registerPropertiesToAnimation() {
        return this;
    }

}
