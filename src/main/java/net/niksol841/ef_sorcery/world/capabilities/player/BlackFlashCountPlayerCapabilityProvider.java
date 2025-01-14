package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlackFlashCountPlayerCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<BlackFlashCountPlayerCapability> PLAYER_BLACK_FLASH = CapabilityManager.get(new CapabilityToken<>() {
    });

    private BlackFlashCountPlayerCapability black_flash_count = null;

    private final LazyOptional<BlackFlashCountPlayerCapability> optional = LazyOptional.of(this::createBlackFlashCountPlayerCapability);

    private BlackFlashCountPlayerCapability createBlackFlashCountPlayerCapability() {
        if (this.black_flash_count == null) {
            this.black_flash_count = new BlackFlashCountPlayerCapability();
        }
        return this.black_flash_count;
    }

    /**
     * Retrieves the Optional handler for the capability requested on the specific side.
     * The return value <strong>CAN</strong> be the same for multiple faces.
     * Modders are encouraged to cache this value, using the listener capabilities of the Optional to
     * be notified if the requested capability get lost.
     *
     * @param cap  The capability to check
     * @param side The Side to check from,
     *             <strong>CAN BE NULL</strong>. Null is defined to represent 'internal' or 'self'
     * @return The requested an optional holding the requested capability.
     */
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_BLACK_FLASH) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }
    /**
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createBlackFlashCountPlayerCapability().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createBlackFlashCountPlayerCapability().loadNBTData(nbt);
    }
}
