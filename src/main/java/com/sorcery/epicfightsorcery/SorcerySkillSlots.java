package com.sorcery.epicfightsorcery;

import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillCategory;
import yesman.epicfight.skill.SkillSlot;

/**
 * Extra skill slots introduced by the Sorcerer subclass.
 * Names must be globally unique across the whole modpack (see Epic Fight's
 * "Registering custom skill slots" doc) - hence the "SORCERY_" prefix.
 */
public enum SorcerySkillSlots implements SkillSlot {

    SORCERY_MANA_INFUSE(SkillCategories.WEAPON_INNATE),
    SORCERY_IDENTITY(SkillCategories.IDENTITY),
    ;

    final SkillCategory category;
    final int id;

    SorcerySkillSlots(SkillCategory category) {
        this.category = category;
        this.id = SkillSlot.ENUM_MANAGER.assign(this);
    }

    @Override
    public SkillCategory category() {
        return category;
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }
}
