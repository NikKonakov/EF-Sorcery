package net.niksol841.ef_sorcery.skill.weapon_innate;

import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.skill.WOMSkillDataKeys;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

public class DivergentFistLvl2Skill extends ConditionalWeaponInnateSkill {
    public DivergentFistLvl2Skill(Builder builder) {
        super(builder);
        builder.setSelector((executor) -> {
            int combo = (Integer)executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue((SkillDataKey) WOMSkillDataKeys.COMBO.get());
            return combo;
        });
        builder.setAnimations(() -> (yesman.epicfight.api.animation.types.AttackAnimation) WOMAnimations.ENDERBLASTER_TWOHAND_TOMAHAWK, () -> (yesman.epicfight.api.animation.types.AttackAnimation) WOMAnimations.ENDERBLASTER_TWOHAND_TOMAHAWK);
    }
}
