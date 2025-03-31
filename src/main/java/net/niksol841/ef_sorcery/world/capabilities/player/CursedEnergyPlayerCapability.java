package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class CursedEnergyPlayerCapability implements ICursedEnergyCapability {
    private int cursed_energy = 0;
    private final int MIN_CURSED_ENERGY_CAPACITY = 0;
    private int max_cursed_energy_capacity = 100;
    private int cursed_energy_regen_rate = 0;
    public boolean canRegenerate = true;
    private ServerPlayer serverPlayer = null;
    private boolean isCursedUser = false;
    private int cursed_energy_mastery_level;

    public CursedEnergyPlayerCapability(){}

    public CursedEnergyPlayerCapability(ServerPlayer serverPlayer){
        this.serverPlayer = serverPlayer;
    }

    public CursedEnergyPlayerCapability(int capacity, int regen_rate_per_second, int max_capacity, ServerPlayer serverPlayer){
        this.cursed_energy = capacity;
        this.cursed_energy_regen_rate = regen_rate_per_second;
        this.max_cursed_energy_capacity = max_capacity;
        this.serverPlayer = serverPlayer;
    }

    @Override
    public int getCE() {
        return this.cursed_energy;
    }

    @Override
    public boolean isCursedUser(){
        return isCursedUser;
    }

    @Override
    public void setIsCursedUser(boolean isCursedUser){
        this.isCursedUser = isCursedUser;
    }

    public void regenerateCE(){
        if (this.canRegenerate && this.isCursedUser){
            cursed_energy = Math.min(cursed_energy +cursed_energy_regen_rate , max_cursed_energy_capacity);
        }
    }

    @Override
    public void setCE(int cursed_energy){
        if (this.cursed_energy <= max_cursed_energy_capacity && this.isCursedUser){
            this.cursed_energy = cursed_energy;
        }
    }

    @Override
    public int getMaxCE(){
        return this.max_cursed_energy_capacity;
    }

    @Override
    public int getCERegenRate(){
        return this.cursed_energy_regen_rate;
    }

    @Override
    public void addCE(int amount) throws IllegalArgumentException {
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new IllegalArgumentException("Amount to add cannot be negative.");
        else if (isCursedUser){
            this.cursed_energy = Math.min(cursed_energy +amount, max_cursed_energy_capacity);
        }
    }

    @Override
    public void removeCE(int amount) throws IllegalArgumentException {
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new IllegalArgumentException("Amount to add cannot be negative.");
        else if (isCursedUser) {
            this.cursed_energy = Math.max(cursed_energy - amount, MIN_CURSED_ENERGY_CAPACITY);
        }
    }

    @Override
    public void setToNullCE() {
        if (isCursedUser){
            this.cursed_energy = 0;
        }
    }

    @Override
    public void setCERegenRate(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount to set cannot be negative.");
        this.cursed_energy_regen_rate = amount;
    }

    @Override
    public void setMaxCE(int amount) {
        this.max_cursed_energy_capacity = amount;
    }

    @Override
    public boolean hasEnoughCE(int amount) throws IllegalArgumentException {
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new IllegalArgumentException("Amount to check cannot be negative.");
        return cursed_energy >= amount;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("cursed_energy", cursed_energy);
        nbt.putInt("cursed_energy_regen_rate", cursed_energy_regen_rate);
        nbt.putInt("max_cursed_energy_capacity", max_cursed_energy_capacity);
        nbt.putBoolean("is_cursed_user", isCursedUser);
        nbt.putBoolean("can_regenerate_CE", canRegenerate);
    }

    public void loadNBTData(CompoundTag nbt){
        cursed_energy = nbt.getInt("cursed_energy_capacity");
        cursed_energy_regen_rate = nbt.getInt("cursed_energy_regen_rate");
        max_cursed_energy_capacity = nbt.getInt("max_cursed_energy_capacity");
        isCursedUser = nbt.getBoolean("is_cursed_user");
        canRegenerate = nbt.getBoolean("can_regenerate_CE");
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

    public void cloneFrom(CursedEnergyPlayerCapability oldObject){
        this.cursed_energy = oldObject.cursed_energy;
        this.cursed_energy_regen_rate = oldObject.cursed_energy_regen_rate;
        this.max_cursed_energy_capacity = oldObject.max_cursed_energy_capacity;
    }

    public static CursedEnergyPlayerCapability getPlayerCEData(ServerPlayer serverPlayer){
        if (serverPlayer != null){
            LazyOptional<CursedEnergyPlayerCapability> capContainer = serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY);
            if (capContainer.isPresent()){
                Optional<CursedEnergyPlayerCapability> optional = capContainer.resolve();
                if (optional.isEmpty()){
                    return new CursedEnergyPlayerCapability(serverPlayer);
                }else {
                    CursedEnergyPlayerCapability capObject = optional.get();
                    capObject.setServerPlayer(serverPlayer);
                    return capObject;
                }
            }else {
                return new CursedEnergyPlayerCapability(serverPlayer);
            }
        }else {
            return null;
        }
    }

    public void setServerPlayer(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    public String toString(){
        return serverPlayer.toString() + " Max cursed energy capacity: " + max_cursed_energy_capacity + ", current cured energy capacity: " + cursed_energy;
    }

    @Override
    public boolean isCanRegenerate() {
        return canRegenerate;
    }

    @Override
    public void setCanRegenerate(boolean canRegenerate) {
        this.canRegenerate = canRegenerate;
    }

    @Override
    public int getCEMasteryLevel() {
        return cursed_energy_mastery_level;
    }

    @Override
    public void setCEMasteryLevel(int cursed_energy_mastery_level) {
        this.cursed_energy_mastery_level = cursed_energy_mastery_level;
    }
}
