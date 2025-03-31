package net.niksol841.ef_sorcery.world.capabilities.styles;

import yesman.epicfight.world.capabilities.item.Style;

public enum DivergentFistStyles implements Style {
    // Divergent Fist Styles

    /**
     * Normal divergent fist style
     */
    DIVERGENT_FIST_NORMAL(false),

    /**
     * Enhanced divergent fist style,
     * enhanced divergent fist style is a style that is unlocked
     * after the player has reached a certain level of CE mastery.
     * This style allows the player to use more powerful moves
     * and combine normal combo attacks with CE attacks.
     */
    DIVERGENT_FIST_ENHANCED(false),
    
    /**
     * Awakening divergent fist style,
     * awakening divergent fist style is a style that is unlocked
     * after the player has reached the highest level of CE mastery.
     * Unlocks ability to use Black Flashes
     * that deliver devastating damage to the enemies
     */
    DIVERGENT_FIST_AWAKENING(false);

    final boolean offHandUse;
    final int id;

    private DivergentFistStyles(boolean offHandUse){
        this.id = ENUM_MANAGER.assign(this);
        this.offHandUse = offHandUse;
    }

    @Override
    public boolean canUseOffhand() {
        return this.offHandUse;
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }
}
