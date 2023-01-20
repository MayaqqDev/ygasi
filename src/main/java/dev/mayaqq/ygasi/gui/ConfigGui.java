package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.Ygasi;
import dev.mayaqq.ygasi.registry.ConfigRegistry;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SignGui;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;

import java.lang.reflect.Field;

import static dev.mayaqq.ygasi.Ygasi.click;

public class ConfigGui {

    public static void gui(ServerPlayerEntity player, Boolean fromBranch) {
        SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X6, player, false) {
            @Override
            public void onClose() {
                super.onClose();
                ConfigRegistry.save();
                if (fromBranch) {
                    BranchGui.gui(player);
                    player.playSound(SoundEvent.of(Ygasi.click), SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        };

        gui.setTitle(Text.translatable("config.ygasi.title"));

        gui.setSlot(0, new GuiElementBuilder()
                .setItem(Items.ENDER_EYE)
                .setName(Text.translatable("config.ygasi.pointsRewarded.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.pointsRewarded))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "pointsRewarded");
                })
        );

        gui.setSlot(1, new GuiElementBuilder()
                .setItem(Items.OAK_SAPLING)
                .setName(Text.translatable("config.ygasi.branchCost.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.branchCost))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "branchCost");
                })
        );

        gui.setSlot(2, new GuiElementBuilder()
                .setItem(Items.BOOK)
                .setName(Text.translatable("config.ygasi.enableSkillBook.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.enableSkillBook))))
                .setCallback((index, clickType, actionType) -> {
                    ConfigRegistry.CONFIG.enableSkillBook = !ConfigRegistry.CONFIG.enableSkillBook;
                    player.playSound(SoundEvent.of(click), SoundCategory.PLAYERS, 1.0F, 1.0F);
                    gui(player, fromBranch);
                })
        );

        gui.setSlot(3, new GuiElementBuilder()
                .setItem(Items.RED_TERRACOTTA)
                .setName(Text.translatable("config.ygasi.resetCost.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.resetCost))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "resetCost");
                })
        );

        gui.setSlot(4, new GuiElementBuilder()
                .setItem(Items.STICK)
                .setName(Text.translatable("config.ygasi.T1Cost.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.T1Cost))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "T1Cost");
                })
        );

        gui.setSlot(5, new GuiElementBuilder()
                .setItem(Items.STICK)
                .setCount(2)
                .setName(Text.translatable("config.ygasi.T2Cost.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.T2Cost))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "T2Cost");
                })
        );

        gui.setSlot(6, new GuiElementBuilder()
                .setItem(Items.STICK)
                .setCount(3)
                .setName(Text.translatable("config.ygasi.T3Cost.title"))
                .addLoreLine(Text.translatable("config.ygasi.current").append(Text.of(String.valueOf(ConfigRegistry.CONFIG.T3Cost))))
                .setCallback((index, clickType, actionType) -> {
                    textInput(player, "T3Cost");
                })
        );

        gui.open();
    }

    private static void textInput(ServerPlayerEntity player, String option) {
        player.playSound(SoundEvent.of(click), SoundCategory.PLAYERS, 1.0F, 1.0F);
        try {
            SignGui gui = new SignGui(player) {
                @Override
                public void onClose() {
                    try {
                        Field field = ConfigRegistry.CONFIG.getClass().getDeclaredField(option);
                        field.setAccessible(true);
                        field.set(ConfigRegistry.CONFIG, Integer.parseInt(this.getLine(0).getString()));
                    } catch (Exception e) {
                        player.sendMessage(Text.translatable("config.ygasi.invalid.number"), true);
                    }
                    gui(player, false);
                }
            };
            gui.open();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}