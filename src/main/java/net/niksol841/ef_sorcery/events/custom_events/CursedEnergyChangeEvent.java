package net.niksol841.ef_sorcery.events.custom_events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapability;

public class CursedEnergyChangeEvent extends PlayerEvent {
    private final CursedEnergyPlayerCapability cursedEnergyPlayerCapability;
    private final float oldCE;
    private float newCE;

    public CursedEnergyChangeEvent(Player player, CursedEnergyPlayerCapability cursedEnergyPlayerCapability, float oldCE, float newCE) {
        super(player);
        this.cursedEnergyPlayerCapability = cursedEnergyPlayerCapability;
        this.oldCE = oldCE;
        this.newCE = newCE;
    }

    public CursedEnergyPlayerCapability getCursedEnergyPlayerCapability() {
        return cursedEnergyPlayerCapability;
    }

    public float getOldCE() {
        return oldCE;
    }

    public float getNewCE() {
        return newCE;
    }

    public void setNewCE(float newCE){
        this.newCE = newCE;
    }
}
