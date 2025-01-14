package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.nbt.CompoundTag;

public class CursedEnergyPlayerCapability implements ICursedEnergyCapability {
    private int cursed_energy_capacity = 0;
    private final int MIN_CURSED_ENERGY_CAPACITY = 0;
    private int max_cursed_energy_capacity = 0;

    private int cursed_energy_regen_rate = 0;

    @Override
    public int getCE() {
        return this.cursed_energy_capacity;
    }

    @Override
    public int getMaxCE() {
        return this.max_cursed_energy_capacity;
    }

    @Override
    public int getCERegenRate() {
        return this.cursed_energy_regen_rate;
    }

    @Override
    public void addCE(int amount) throws NullPointerException {
        if (amount < 0) throw new NullPointerException();
        this.cursed_energy_capacity = Math.min(cursed_energy_capacity+amount, max_cursed_energy_capacity);
    }

    @Override
    public void removeCE(int amount) throws NullPointerException {
        if (amount < 0) throw new NullPointerException();
        this.cursed_energy_capacity = Math.max(cursed_energy_capacity-amount, MIN_CURSED_ENERGY_CAPACITY);
    }

    @Override
    public void setToNullCE() {
        this.cursed_energy_capacity = 0;
    }

    @Override
    public void setCERegenRate(int amount) {
        this.cursed_energy_regen_rate = amount;
    }

    @Override
    public void setMaxCE(int amount) {
        this.max_cursed_energy_capacity = amount;
    }

    @Override
    public boolean hasEnoughCE(int amount) throws NullPointerException {
        if (amount < 0) throw new NullPointerException();
        return cursed_energy_capacity >= amount;
    };

    @Override
    public boolean isCursedUser() {
        return max_cursed_energy_capacity == 0;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("cursed_energy_capacity", cursed_energy_capacity);
    }

    public void loadNBTData(CompoundTag nbt){
        cursed_energy_capacity = nbt.getInt("cursed_energy_capacity");
    }
}
