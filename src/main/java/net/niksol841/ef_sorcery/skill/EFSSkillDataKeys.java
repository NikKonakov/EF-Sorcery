package net.niksol841.ef_sorcery.skill;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.skill.weapon_innate.DivergentFistLvl1Skill;
import net.niksol841.ef_sorcery.skill.weapon_innate.SimpleTaijutsuSkill;
import yesman.epicfight.skill.SkillDataKey;

public class EFSSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS_DEFERRED_REGISTER = DeferredRegister.create(new ResourceLocation("epicfight", "skill_data_keys"), ef_sorcery.MOD_ID);
    public static final RegistryObject<SkillDataKey<Integer>> CE_AMOUNT;
    public static final RegistryObject<SkillDataKey<Boolean>> IS_SORCERER;
    public static final RegistryObject<SkillDataKey<Integer>> BF_AMOUNT;
    public static final RegistryObject<SkillDataKey<Integer>> CE_TIMED_ATTACK;
    public static final RegistryObject<SkillDataKey<Integer>> CE_CONCENTRATION;
    public static final RegistryObject<SkillDataKey<Integer>> COMBO;

    public EFSSkillDataKeys(){}


    static {
        CE_AMOUNT = DATA_KEYS_DEFERRED_REGISTER.register("ce_amount",
                ()-> SkillDataKey.createIntKey(0,false,
                        DivergentFistLvl1Skill.class));
        IS_SORCERER = DATA_KEYS_DEFERRED_REGISTER.register("is_sorcerer",
                ()-> SkillDataKey.createBooleanKey(false, false,
                        DivergentFistLvl1Skill.class));
        BF_AMOUNT = DATA_KEYS_DEFERRED_REGISTER.register("bf_amount",
                ()-> SkillDataKey.createIntKey(0, false,
                        DivergentFistLvl1Skill.class));
        CE_TIMED_ATTACK = DATA_KEYS_DEFERRED_REGISTER.register("timed_attack",
                ()-> SkillDataKey.createIntKey(0, false,
                        DivergentFistLvl1Skill.class));
        CE_CONCENTRATION = DATA_KEYS_DEFERRED_REGISTER.register("ce_concentration",
                ()-> SkillDataKey.createIntKey(0, false,
                        DivergentFistLvl1Skill.class));
        COMBO = DATA_KEYS_DEFERRED_REGISTER.register("combo",
                () -> SkillDataKey.createIntKey(0, false,
                        SimpleTaijutsuSkill.class)
        );
    }
}
