package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class CursedEnergyPlayerCapability implements ICursedEnergyCapability {
    private int cursed_energy_capacity = 0;
    private final int MIN_CURSED_ENERGY_CAPACITY = 0;
    private int max_cursed_energy_capacity = 0;
    private int cursed_energy_regen_rate = 0;
    private ServerPlayer serverPlayer = null;

    public CursedEnergyPlayerCapability(){}

    public CursedEnergyPlayerCapability(ServerPlayer serverPlayer){
        this.serverPlayer = serverPlayer;
    }

    public CursedEnergyPlayerCapability(int capacity, int regen_rate_per_second, int max_capacity, ServerPlayer serverPlayer){
        this.cursed_energy_capacity = capacity;
        this.cursed_energy_regen_rate = regen_rate_per_second;
        this.max_cursed_energy_capacity = max_capacity;
        this.serverPlayer = serverPlayer;
    }

    @Override
    public int getCE() {
        return this.cursed_energy_capacity;
    }

    public void regenerateCE(){
        cursed_energy_capacity = Math.min(cursed_energy_capacity+cursed_energy_regen_rate, max_cursed_energy_capacity);
    }

    public void setCE(int cursed_energy){
        if (this.cursed_energy_capacity <= max_cursed_energy_capacity && this.cursed_energy_capacity < MIN_CURSED_ENERGY_CAPACITY){
            this.cursed_energy_capacity = cursed_energy;
        }
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
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new NullPointerException();
        this.cursed_energy_capacity = Math.min(cursed_energy_capacity+amount, max_cursed_energy_capacity);
    }

    @Override
    public void removeCE(int amount) throws NullPointerException {
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new NullPointerException();
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
        if (amount < MIN_CURSED_ENERGY_CAPACITY) throw new NullPointerException();
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

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

    public void cloneFrom(CursedEnergyPlayerCapability oldObject){
        this.cursed_energy_capacity = oldObject.cursed_energy_capacity;
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

    private void setServerPlayer(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    ;
}
