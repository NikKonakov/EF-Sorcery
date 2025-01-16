package net.niksol841.ef_sorcery.gameasset;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.main.EFSorcery;
import net.niksol841.ef_sorcery.skill.DivergentFistSkill;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

@Mod.EventBusSubscriber(
        modid = EFSorcery.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class EFSorcerySkills {
    public static Skill DIVERGENT_FIST;
    public static Skill DIVERGENT_FIST_BUFF;
    public static Skill DIVERGENT_FIST_BLACK_FLASH;

    public EFSorcerySkills(){

    }

    @SubscribeEvent
    public static void  buildSkillEvent(SkillBuildEvent buildEvent) {
        SkillBuildEvent.ModRegistryWorker modRegistry = buildEvent.createRegistryWorker(EFSorcery.MOD_ID);
        DIVERGENT_FIST = modRegistry.build("divergent_fist", DivergentFistSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
    }

}
