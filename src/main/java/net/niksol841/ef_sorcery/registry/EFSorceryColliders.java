package net.niksol841.ef_sorcery.registry;

import net.minecraft.resources.ResourceLocation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.gameasset.ColliderPreset;
import net.niksol841.ef_sorcery.main.*;

public class EFSorceryColliders {
    public static final Collider DIVERGENT_FIST_DASH = ColliderPreset.registerCollider(new ResourceLocation(ef_sorcery.MOD_ID, "divergent_fist_dash"), new OBBCollider(1, 1, 1, 0, -1, -0.1));
}
