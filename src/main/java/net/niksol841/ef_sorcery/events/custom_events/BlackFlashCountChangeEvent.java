package net.niksol841.ef_sorcery.events.custom_events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class BlackFlashCountChangeEvent extends PlayerEvent {
    public BlackFlashCountChangeEvent(Player player) {
        super(player);
    }
}
