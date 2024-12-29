package net.niksol841.ef_sorcery.world.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class BlackFlashDebuffEffect extends MobEffect {
    protected BlackFlashDebuffEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    private static final UUID HEALTH_DEBUFF_UUID = UUID.fromString("d8628415-f207-4f58-8066-bd6ee3bcb115");
    private static final UUID SPEED_DEBUFF_UUID = UUID.fromString("2f66c6bb-8326-41be-8239-b7125c1a987a");

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        //Add health debuff
        AttributeInstance maxHealthAttribute = pLivingEntity.getAttribute(Attributes.MAX_HEALTH);
        maxHealthAttribute.addTransientModifier(new AttributeModifier(
                HEALTH_DEBUFF_UUID,
                "black_flash_health_debuff",
                0.10F+(0.05*pAmplifier),
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));

        //Add movement speed debuff
        AttributeInstance speedAttribute = pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        speedAttribute.addTransientModifier(new AttributeModifier(
                SPEED_DEBUFF_UUID,
                "black_flash_speed_debuff",
                0.10F+(0.05*pAmplifier),
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));
    }

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        AttributeInstance maxHealthAttribute = pLivingEntity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.removeModifier(HEALTH_DEBUFF_UUID);
        }
        AttributeInstance speedAttribute = pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null) {
            speedAttribute.removeModifier(SPEED_DEBUFF_UUID);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return false;
    }
}
