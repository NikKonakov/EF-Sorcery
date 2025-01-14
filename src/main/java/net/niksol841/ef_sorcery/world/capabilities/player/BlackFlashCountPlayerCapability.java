package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.nbt.CompoundTag;

public class BlackFlashCountPlayerCapability {
    private int black_flash_count = 0;
    private static final int MAX_BLACK_FLASH_COUNT = 3;
    private static final int MIN_BLACK_FLASH_COUNT = 0;

    public int getBlackFlashCount(){
        return this.black_flash_count;
    };

    public void addBlackFlashCount(){
        if (black_flash_count<MAX_BLACK_FLASH_COUNT){
            black_flash_count++;
        }
    }

    public void subBlackFlashCount(){
        if( black_flash_count>MIN_BLACK_FLASH_COUNT){
            black_flash_count--;
        }
    }

    public void nullifyBlackFlashCount(){
        black_flash_count=0;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("black_flash_count",black_flash_count);
    }

    public void loadNBTData(CompoundTag nbt){
        black_flash_count = nbt.getInt("black_flash_count");
    }
}
