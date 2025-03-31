package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ICursedEnergyCapability {

    /**
     * Gets user's current CE
     * @return current CE
     */
    int getCE();

    /**
     * Checks if user has access to cursed energy
     * @return True if has, false otherwise
     */
    boolean isCursedUser();

    /**
     * Sets user's ability to use cursed energy
     * @param isCursedUser - is able to use cursed energy
     */
    void setIsCursedUser(boolean isCursedUser);

    /**
     * Regenerates user's CE
     */
    void setCE(int cursed_energy);

    /**
     * Gets user's max possible CE
     * @return MAX CE
     */
    int getMaxCE();

    /**
     * Gets user's CE regen rate
     * @return regen rate
     */
    int getCERegenRate();

    /**
     * Safely adds CE to user
     * @param amount amount of CE to be added
     * @throws IllegalArgumentException if {@param amount} is less than zero
     */
    void addCE(int amount) throws IllegalArgumentException;

    /**
     * Safely subtracts CE from user
     * @param amount - amount to subtract
     * @throws IllegalArgumentException
     * Throws an exception if amount to subtract is less than zero
     */
    void removeCE(int amount) throws IllegalArgumentException;

    /**
     * Sets CE user's to null
     */
    void setToNullCE();

    /**
     * Set's user CE regen rate to desired amount
     * @param amount amount to set regen rate
     */
    void setCERegenRate(int amount);

    /**
     * Sets user max cursed energy
     * @param amount max CE to set
     */
    void setMaxCE(int amount);

    /**
     * Checks if user has enough CE
     * @param amount - amount CE to check
     * @throws NullPointerException if {@param amount} is less than zero
     * @return True if has, false otherwise
     */
    boolean hasEnoughCE(int amount) throws NullPointerException;

    /**
     * Checks if user can regenerate CE
     * @return True if can, false otherwise
     */
    boolean isCanRegenerate();

    /**
     * Sets user's ability to regenerate CE
     * @param canRegenerate - is able to regenerate CE
     */
    void setCanRegenerate(boolean canRegenerate);

    /**
     * Gets user's CE mastery level
     * @return CE mastery level
     */
    int getCEMasteryLevel();

    /**
     * Sets user's CE mastery level
     * @param cursed_energy_mastery_level - CE mastery level
     */
    void setCEMasteryLevel(int cursed_energy_mastery_level);
}
