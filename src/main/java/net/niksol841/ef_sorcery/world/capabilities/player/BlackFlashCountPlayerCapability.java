package net.niksol841.ef_sorcery.world.capabilities.player;

import net.minecraft.nbt.CompoundTag;

public class BlackFlashCountPlayerCapability {
    //Default values and Constants
    private int black_flash_count = 0;
    private int max_black_flash_count = 3;
    private static final int MIN_BLACK_FLASH_COUNT = 0;

    public BlackFlashCountPlayerCapability(){}

    public BlackFlashCountPlayerCapability(int max_black_flash_count){
        this.max_black_flash_count = max_black_flash_count;
    }

    public int getMaxBlackFlashCount(){
        return this.max_black_flash_count;
    }

    public int getBlackFlashCount(){
        return this.black_flash_count;
    };

    public void addBlackFlashCount(){
        if (black_flash_count< max_black_flash_count){
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

    public void cloneFrom(BlackFlashCountPlayerCapability oldObject){
        this.max_black_flash_count = oldObject.max_black_flash_count;
    }
}
