package net.niksol841.ef_sorcery.world.effects;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import java.util.UUID;

public class BlackFlashBuffEffect extends MobEffect {

    public BlackFlashBuffEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    private static final UUID HEALTH_BOOST_UUID = UUID.fromString("81e368ad-13e2-46b3-b48a-eb19ccfeaf3b");
    private static final UUID DAMAGE_BOOST_UUID = UUID.fromString("0dedda93-7908-4380-8a1a-db606266c657");
    private static final UUID SPEED_BOOST_UUID = UUID.fromString("c07fce9c-e2ca-42c9-ad63-d5e84c24095d");
    private static final UUID ATTACK_SPEED_BOOST_UUID = UUID.fromString("61df9887-be1b-47af-ba7f-3e3a1fa82a22");


    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        //Send message into a chat if instance is player
        if (pLivingEntity instanceof Player) {
            pLivingEntity.sendSystemMessage(Component.translatable("black_flash_buff_effect_start_message").withStyle(ChatFormatting.DARK_RED));
        }

        //Add health boost
        AttributeInstance healthAttribute = pLivingEntity.getAttribute(Attributes.MAX_HEALTH);
        healthAttribute.addTransientModifier(new AttributeModifier(
                HEALTH_BOOST_UUID,
                "black_flash_health_boost",
                0.10F + (pAmplifier * 1 * 0.01),
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));

        //Add damage boost
        AttributeInstance damageAttribute = pLivingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        damageAttribute.addTransientModifier(new AttributeModifier(
                DAMAGE_BOOST_UUID,
                "black_flash_damage_boost",
                0.15F + (pAmplifier * 15 * 0.01),
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));

        //Add speed boost
        AttributeInstance speedAttribute = pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        speedAttribute.addTransientModifier(new AttributeModifier(
                SPEED_BOOST_UUID,
                "black_flash_speed_boost",
                0.10F + (pAmplifier * 3 * 0.01), //Increases for 30% for every amp level (ignores 0)
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));

        //Add attack speed boost
        AttributeInstance damageSpeedAttribute = pLivingEntity.getAttribute(Attributes.ATTACK_SPEED);
        damageSpeedAttribute.addTransientModifier(new AttributeModifier(
                ATTACK_SPEED_BOOST_UUID,
                "black_flash_attack_speed_boost",
                0.3F + (pAmplifier * 5 * 0.01), //Increases for 50% for every amp level
                AttributeModifier.Operation.MULTIPLY_TOTAL
        ));
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        if (pLivingEntity instanceof Player) {
            pLivingEntity.sendSystemMessage(Component.translatable("black_flash_buff_effect_end_message").withStyle(ChatFormatting.DARK_RED));
        }

        if (!pLivingEntity.level().isClientSide()) {
            //Remove health boost
            AttributeInstance healthAttribute = pLivingEntity.getAttribute(Attributes.MAX_HEALTH);
            if (healthAttribute != null) {
                healthAttribute.removeModifier(HEALTH_BOOST_UUID);
            }

            //Remove damage boost
            AttributeInstance damageAttribute = pLivingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (damageAttribute != null) {
                damageAttribute.removeModifier(DAMAGE_BOOST_UUID);
            }

            //Remove speed boost
            AttributeInstance speedAttribute = pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
            if (speedAttribute != null) {
                speedAttribute.removeModifier(SPEED_BOOST_UUID);
            }

            //Remove attack speed boost
            AttributeInstance attackSpeedAttribute = pLivingEntity.getAttribute(Attributes.ATTACK_SPEED);
            if (attackSpeedAttribute != null) {
                attackSpeedAttribute.removeModifier(ATTACK_SPEED_BOOST_UUID);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return false;
    }

}
