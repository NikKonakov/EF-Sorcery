package net.niksol841.ef_sorcery.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niksol841.ef_sorcery.main.EFSorcery;

public class SorceryItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> CURSED_FISTS;

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EFSorcery.MOD_ID);
        CURSED_FISTS = ITEMS.register("cursed_fists", () -> {
           return new CursedFistsItem((new Item.Properties()).rarity(Rarity.RARE));
        });
    }
}
