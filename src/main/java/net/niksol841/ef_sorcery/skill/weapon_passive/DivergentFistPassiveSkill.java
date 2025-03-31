package net.niksol841.ef_sorcery.skill.weapon_passive;

import net.minecraft.client.gui.GuiGraphics;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class DivergentFistPassiveSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f3f6b3b3-4b3b-4b3b-4b3b-4b3b4b3b4b3b");
    public DivergentFistPassiveSkill(Builder<? extends Skill> builder) {
        super(builder);
        builder.setActivateType(ActivateType.DURATION_INFINITE);
        builder.setCategory(SkillCategories.WEAPON_PASSIVE);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID,(event)->{});
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID,(event)->{});
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SERVER_ITEM_USE_EVENT, EVENT_UUID,(event)->{});
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID,(event)->{});
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.SERVER_ITEM_USE_EVENT, EVENT_UUID);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
    }

    @Override
    public boolean shouldDraw(SkillContainer container) {
        return true;
    }

    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics guiGraphics, float x, float y) {
        super.drawOnGui(gui, container, guiGraphics, x, y);
    }
}
