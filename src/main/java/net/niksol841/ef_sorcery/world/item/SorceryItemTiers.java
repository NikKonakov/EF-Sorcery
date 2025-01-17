package net.niksol841.ef_sorcery.world.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public enum SorceryItemTiers implements Tier {
    CURSED_FISTS(
            2,
            3000,
            9.0F,
            2,
            5,
            () -> Ingredient.of(Items.IRON_INGOT));


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float damage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    SorceryItemTiers(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier repairMaterialIn) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.damage = damage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterialIn);
    }

    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}
