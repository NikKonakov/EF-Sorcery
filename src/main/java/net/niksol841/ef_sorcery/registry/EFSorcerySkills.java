package net.niksol841.ef_sorcery.registry;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import net.niksol841.ef_sorcery.skill.weapon_innate.DivergentFistLvl1Skill;
import net.niksol841.ef_sorcery.skill.weapon_innate.DivergentFistLvl2Skill;
import net.niksol841.ef_sorcery.skill.weapon_innate.DivergentFistLvl3Skill;
import net.niksol841.ef_sorcery.skill.weapon_innate.SimpleTaijutsuSkill;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

@Mod.EventBusSubscriber(
        modid = ef_sorcery.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class EFSorcerySkills {
    public static Skill DIVERGENT_FIST_1;
    public static Skill DIVERGENT_FIST_2;
    public static Skill DIVERGENT_FIST_3;
    public static Skill SIMPLE_TAIJUTSU_SKILL;

    public EFSorcerySkills(){

    }

    @SubscribeEvent
    public static void  buildSkillEvent(SkillBuildEvent buildEvent) {
        SkillBuildEvent.ModRegistryWorker modRegistry = buildEvent.createRegistryWorker(ef_sorcery.MOD_ID);
        DIVERGENT_FIST_1 = modRegistry.build("divergent_fist_skill", DivergentFistLvl1Skill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder());
//        DIVERGENT_FIST_2 = modRegistry.build("divergent_fist_2_skill", DivergentFistLvl2Skill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder());
//        DIVERGENT_FIST_3 = modRegistry.build("divergent_fist_3_skill", DivergentFistLvl3Skill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder());
        SIMPLE_TAIJUTSU_SKILL = modRegistry.build("simple_taijutsu_skill", SimpleTaijutsuSkill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder());
    }

}
