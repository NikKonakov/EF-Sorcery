package net.niksol841.ef_sorcery.gameasset;

import net.minecraftforge.fml.common.Mod;
import net.niksol841.ef_sorcery.main.ef_sorcery;
import yesman.epicfight.skill.Skill;

@Mod.EventBusSubscriber(
        modid = ef_sorcery.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class EFSorcerySkills {
    public static Skill DIVERGENT_FIST_PUNCH;
    public static Skill DIVERGENT_FIST_BUFF;
    public static Skill DIVERGENT_FIST_BLACK_FLASH;

}
