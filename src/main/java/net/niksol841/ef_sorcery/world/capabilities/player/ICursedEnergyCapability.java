package net.niksol841.ef_sorcery.world.capabilities.player;

public interface ICursedEnergyCapability {
    /**
     * Gets user's current CE
     * @return current CE
     */
    int getCE();

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
     * @throws NullPointerException if {@param amount} is less than zero
     */
    void addCE(int amount) throws NullPointerException;

    /**
     * Safely subtracts CE from user
     * @param amount - amount to subtract
     * @throws NullPointerException
     * Throws an exception if amount to subtract is less than zero
     */
    void removeCE(int amount) throws NullPointerException;

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
     * Checks if user has any CE
     * @return True if has, false otherwise
     */
    boolean isCursedUser();
}
