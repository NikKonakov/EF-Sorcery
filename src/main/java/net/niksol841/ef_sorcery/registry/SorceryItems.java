package net.niksol841.ef_sorcery.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.world.item.CursedFistsItem;

public class SorceryItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> CURSED_FISTS;

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ef_sorcery.MOD_ID);
        CURSED_FISTS = ITEMS.register("cursed_fists", () -> new CursedFistsItem((new Item.Properties()).rarity(Rarity.RARE)));
    }
}
