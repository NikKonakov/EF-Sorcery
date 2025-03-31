package net.niksol841.ef_sorcery.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.niksol841.ef_sorcery.registry.SorceryItemTiersRegistry;
import yesman.epicfight.world.item.WeaponItem;

import javax.annotation.Nullable;
import java.util.List;

public class CursedFistsItem extends WeaponItem {
    public CursedFistsItem(Item.Properties build) {
        super(SorceryItemTiersRegistry.CURSED_FISTS, 0, -2.0F, build);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn ) {
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.ef_sorcery.cursed_fists.tooltip").withStyle(ChatFormatting.BLUE));
    }

}
