package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CursedEnergyPlayerCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<CursedEnergyPlayerCapability> PLAYER_CURSED_ENERGY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(ef_sorcery.MODID, "cursed_energy_capacity_capability");

    private CursedEnergyPlayerCapability cursed_energy = null;

    private LazyOptional<CursedEnergyPlayerCapability> optional = LazyOptional.of(this::createCursedEnergyPlayerCapability);

    private CursedEnergyPlayerCapability createCursedEnergyPlayerCapability() {
        if (this.cursed_energy == null){
            this.cursed_energy = new CursedEnergyPlayerCapability();
        }
        return this.cursed_energy;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_CURSED_ENERGY_CAPABILITY){
            return optional.cast();
        }
        return LazyOptional.empty();
    }
    void invalidate(){
        this.optional.invalidate();
    }

    public static void attach(final AttachCapabilitiesEvent<LivingEntity> event){
        final CursedEnergyPlayerCapabilityProvider provider = new CursedEnergyPlayerCapabilityProvider();
        event.addCapability(CursedEnergyPlayerCapabilityProvider.IDENTIFIER, provider);
    }

    private CursedEnergyPlayerCapabilityProvider(){
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCursedEnergyPlayerCapability().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCursedEnergyPlayerCapability().loadNBTData(nbt);
    }
}
