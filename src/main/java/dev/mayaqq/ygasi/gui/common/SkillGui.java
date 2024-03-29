package dev.mayaqq.ygasi.gui.common;

import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class SkillGui extends SimpleGui {
    // I'm doing the onOpen and onClose methods here because I want to play a sound when the gui is opened and closed for all of the guis so I don't have to do it in every gui class
    public SkillGui(ScreenHandlerType<?> type, ServerPlayerEntity player, boolean manipulatePlayerSlots) {
        super(type, player, manipulatePlayerSlots);
    }

    @Override
    public void onOpen() {
        this.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
        super.onOpen();
    }

    @Override
    public void onClose() {
        player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
        super.onClose();
    }
}