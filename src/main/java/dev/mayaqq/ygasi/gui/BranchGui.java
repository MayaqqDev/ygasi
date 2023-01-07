package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.util.YgasiUtils;
import eu.pb4.sgui.api.elements.*;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import net.minecraft.util.Identifier;

public class BranchGui {
    public static void gui(ServerPlayerEntity player) {
        try {
            SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X3, player, false) {};

            //the title of the gui
            gui.setTitle(Text.translatable("gui.ygasi.branch.title", player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))).formatted(Formatting.DARK_AQUA));

            //background items
            for (int x = 0; x <= 26; x++) {
                gui.setSlot(x, new GuiElementBuilder()
                        .setItem(Items.GRAY_STAINED_GLASS_PANE)
                        .setName(Text.of(" "))
                );
            }
            for (int x = 10; x <= 16; x++) {
                gui.setSlot(x, new GuiElementBuilder()
                        .setItem(Items.LIGHT_BLUE_STAINED_GLASS_PANE)
                        .setName(Text.of(" "))
                );
            }
            for (int x = 0; x <= 2; x++) {
                for (int y = 2; y <= 6; y+=2) {
                    gui.setSlot(x * 9 + y, new GuiElementBuilder()
                            .setItem(Items.LIGHT_BLUE_STAINED_GLASS_PANE)
                            .setName(Text.of(" "))
                    );
                }
            }

            //branch items
            //mercenary gui button
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/mercenary")) {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_SWORD)
                        .setCustomModelData(1)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .addLoreLine(Text.translatable("gui.ygasi.branch.cost", ConfigRegistry.CONFIG.branchCost))
                        .setName(Text.translatable("gui.ygasi.branch.mercenary.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> save(player, "mercenary", "gui.ygasi.branch.mercenary.title"))
                );
            } else {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_SWORD)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .glow()
                        .setName(Text.translatable("gui.ygasi.branch.mercenary.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> MercenaryGui.gui(player))
                );
            }

            //wizardry gui button
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/wizardry")) {
                gui.setSlot(13, new GuiElementBuilder()
                        .setItem(Items.BLAZE_ROD)
                        .addLoreLine(Text.translatable("gui.ygasi.branch.cost", ConfigRegistry.CONFIG.branchCost))
                        .setName(Text.translatable("gui.ygasi.branch.wizardry.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> save(player, "wizardry", "gui.ygasi.branch.wizardry.title"))
                );
            } else {
                gui.setSlot(13, new GuiElementBuilder()
                        .setItem(Items.BLAZE_ROD)
                        .glow()
                        .setName(Text.translatable("gui.ygasi.branch.wizardry.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> WizardryGui.gui(player))
                );
            }

            //druidry gui button
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/druidry")) {
                gui.setSlot(15, new GuiElementBuilder()
                        .setItem(Items.OAK_SAPLING)
                        .addLoreLine(Text.translatable("gui.ygasi.branch.cost", ConfigRegistry.CONFIG.branchCost))
                        .setName(Text.translatable("gui.ygasi.branch.druidry.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> save(player, "druidry", "gui.ygasi.branch.druidry.title"))
                );
            } else {
                gui.setSlot(15, new GuiElementBuilder()
                        .setItem(Items.OAK_SAPLING)
                        .glow()
                        .setName(Text.translatable("gui.ygasi.branch.druidry.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> DruidryGui.gui(player))
                );
            }

            //extra gui button
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/extra")) {
                gui.setSlot(26, new GuiElementBuilder()
                        .setItem(Items.BOOK)
                        .addLoreLine(Text.translatable("gui.ygasi.branch.cost", ConfigRegistry.CONFIG.branchCost / 2))
                        .setName(Text.translatable("gui.ygasi.branch.extra.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> save(player, "extra", "gui.ygasi.branch.extra.title"))
                );
            } else {
                gui.setSlot(26, new GuiElementBuilder()
                        .setItem(Items.BOOK)
                        .glow()
                        .setName(Text.translatable("gui.ygasi.branch.extra.title").formatted(Formatting.BOLD))
                        .setCallback((index, clickType, actionType) -> ExtraGui.gui(player))
                );
            }

            //info item button
            gui.setSlot(18, new GuiElementBuilder()
                    .setItem(Items.PAPER)
                    .setName(Text.translatable("gui.ygasi.branch.info.title"))
                    .setCallback((index, clickType, actionType) -> {
                        player.closeHandledScreen();
                        player.sendMessage(Text.translatable("gui.ygasi.branch.info.main"), false);
                    })
            );
            //reset item button
            gui.setSlot(8, new GuiElementBuilder()
                    .setItem(Items.BARRIER)
                    .setName(Text.translatable("gui.ygasi.branch.reset.title"))
                    .addLoreLine(Text.translatable("gui.ygasi.branch.reset.lore"))
                    .addLoreLine(Text.translatable("gui.ygasi.branch.reset.lore2"))
                    .setCallback((index, clickType, actionType) -> ResetGui.gui(player))
            );

            //grant the player the root advancement of ygasi
            YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/root"), "opened_skill_menu");

            gui.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //this happens when you try to unlock a branch
    public static void save(ServerPlayerEntity player, String branch, String branchName) {
        //special category for extra branch because it's half the price
        if (branch.equals("extra")) {
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= ConfigRegistry.CONFIG.branchCost / 2) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - ConfigRegistry.CONFIG.branchCost / 2);
                YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/extra"), "unlocked_extra");
                player.sendMessage(Text.translatable("gui.ygasi.branch.unlock", Text.translatable(branchName)), false);
                ExtraGui.gui(player);
            } else {
                player.sendMessage(Text.translatable("gui.ygasi.branch.no.skill"), false);
            }
            //the rest of the branches
        } else {
            //same code as in extra
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= ConfigRegistry.CONFIG.branchCost) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - ConfigRegistry.CONFIG.branchCost);
                player.sendMessage(Text.translatable("gui.ygasi.branch.unlock", Text.translatable(branchName)), false);
                player.closeHandledScreen();
                //grant the player the advancement of the branch they unlocked
                switch (branch) {
                    case "mercenary" -> {
                        YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/mercenary"), "unlocked_mercenary");
                        MercenaryGui.gui(player);
                    }
                    case "wizardry" -> {
                        YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/wizardry"), "unlocked_wizardry");
                        WizardryGui.gui(player);
                    }
                    case "druidry" -> {
                        YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/druidry"), "unlocked_druidry");
                        DruidryGui.gui(player);
                    }
                }
                //if the player doesn't have enough skill points
            } else {
                player.sendMessage(Text.translatable("gui.ygasi.branch.no.skill"), false);
                player.closeHandledScreen();
            }
        }
    }
}