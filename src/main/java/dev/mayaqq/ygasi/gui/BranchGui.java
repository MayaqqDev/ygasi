package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.util.YgasiUtils;
import eu.pb4.sgui.api.elements.*;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;
import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS_TOTAL;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import net.minecraft.util.Identifier;

public class BranchGui {
    public static void gui(ServerPlayerEntity player) {
        int skillPoints = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS));

        try {
            SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X3, player, false) {};

            gui.setTitle(Text.of("§3Skill Points: " + skillPoints));

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
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/mercenary")) {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_SWORD)
                        .setCustomModelData(1)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .addLoreLine(Text.literal("Cost: " + ConfigRegistry.CONFIG.branchCost).setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GRAY)))
                        .setName(Text.literal("Mercenary")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)))
                        .setCallback((index, clickType, actionType) -> save(player, "mercenary", "§cMercenary"))
                );
            } else {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_SWORD)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .glow()
                        .setName(Text.literal("Mercenary")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)))
                        .setCallback((index, clickType, actionType) -> MercenaryGui.gui(player))
                );
            }

            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/wizardry")) {
                gui.setSlot(13, new GuiElementBuilder()
                        .setItem(Items.BLAZE_ROD)
                        .addLoreLine(Text.literal("Cost: " + ConfigRegistry.CONFIG.branchCost).setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GRAY)))
                        .setName(Text.literal("Wizardry")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.DARK_PURPLE)))
                        .setCallback((index, clickType, actionType) -> save(player, "wizardry", "§5Wizardry"))
                );
            } else {
                gui.setSlot(13, new GuiElementBuilder()
                        .setItem(Items.BLAZE_ROD)
                        .glow()
                        .setName(Text.literal("Wizardry")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.DARK_PURPLE)))
                        .setCallback((index, clickType, actionType) -> WizardryGui.gui(player))
                );
            }

            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/druidry")) {
                gui.setSlot(15, new GuiElementBuilder()
                        .setItem(Items.OAK_SAPLING)
                        .addLoreLine(Text.literal("Cost: " + ConfigRegistry.CONFIG.branchCost).setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GRAY)))
                        .setName(Text.literal("Druidry")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GREEN)))
                        .setCallback((index, clickType, actionType) -> save(player, "druidry", "§aDruidry"))
                );
            } else {
                gui.setSlot(15, new GuiElementBuilder()
                        .setItem(Items.OAK_SAPLING)
                        .glow()
                        .setName(Text.literal("Druidry")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.GREEN)))
                        .setCallback((index, clickType, actionType) -> DruidryGui.gui(player))
                );
            }

            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/extra")) {
                gui.setSlot(26, new GuiElementBuilder()
                        .setItem(Items.BOOK)
                        .addLoreLine(Text.literal("Cost: " + ConfigRegistry.CONFIG.branchCost / 2).setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GRAY)))
                        .setName(Text.literal("Extra")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.BLUE)))
                        .setCallback((index, clickType, actionType) -> save(player, "extra", "§9Extra Skills"))
                );
            } else {
                gui.setSlot(26, new GuiElementBuilder()
                        .setItem(Items.BOOK)
                        .glow()
                        .setName(Text.literal("Extra Skills")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.BLUE)))
                        .setCallback((index, clickType, actionType) -> ExtraGui.gui(player))
                );
            }

            //info item
            gui.setSlot(18, new GuiElementBuilder()
                    .setItem(Items.PAPER)
                    .setName(Text.literal("Info").formatted(Formatting.GOLD).formatted(Formatting.BOLD))
                    .setCallback((index, clickType, actionType) -> infoMessage(player))
            );
            //reset item
            gui.setSlot(8, new GuiElementBuilder()
                    .setItem(Items.BARRIER)
                    .setName(Text.literal("Reset").formatted(Formatting.RED).formatted(Formatting.BOLD))
                    .setCallback((index, clickType, actionType) -> ResetGui.gui(player))
            );

            YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/root"), "opened_skill_menu");

            gui.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void save(ServerPlayerEntity player, String branch, String branchName) {
        if (branch.equals("extra")) {
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= ConfigRegistry.CONFIG.branchCost / 2) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - ConfigRegistry.CONFIG.branchCost / 2);
                YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/extra"), "unlocked_extra");
                player.sendMessage(Text.of("§aYou have unlocked the " + branchName + " branch!"), false);
                ExtraGui.gui(player);
            } else {
                player.sendMessage(Text.of("§cYou do not have enough skill points to unlock this branch!"), false);
            }
        } else {
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= ConfigRegistry.CONFIG.branchCost) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - ConfigRegistry.CONFIG.branchCost);
                player.sendMessage(Text.of("You have selected the §a" + branchName + " §fbranch!"), false);
                player.closeHandledScreen();
                if (branch.equals("mercenary")) {
                    YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/mercenary"), "unlocked_mercenary");
                    MercenaryGui.gui(player);
                } else if (branch.equals("wizardry")) {
                    YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/wizardry"), "unlocked_wizardry");
                    WizardryGui.gui(player);
                } else if (branch.equals("druidry")) {
                    YgasiUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/druidry"), "unlocked_druidry");
                    DruidryGui.gui(player);
                }

            } else {
                player.sendMessage(Text.translatable("gui.ygasi.branch.no.skill").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)), false);
                player.closeHandledScreen();
            }
        }
    }
    public static void infoMessage(ServerPlayerEntity player) {
        player.closeHandledScreen();
        player.sendMessage(Text.translatable("gui.ygasi.branch.info.main"), false);
    }
}