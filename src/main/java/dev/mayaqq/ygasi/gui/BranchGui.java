package dev.mayaqq.ygasi.gui;

import eu.pb4.sgui.api.elements.*;
import eu.pb4.sgui.api.gui.SimpleGui;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.UUID;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.registry.PlayerDataRegistry;

public class BranchGui {
    public static void gui(ServerPlayerEntity player) {
        try {
            UUID playerUUID = player.getUuid();
            PlayerDataRegistry.load(playerUUID);
            SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X3, player, false) {};

            gui.setTitle(Text.of("§3Skill Points: " + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))));

            //background items
            for (int x = 0; x <= 17; x++) {
                gui.setSlot(x, new GuiElementBuilder()
                        .setItem(Items.GRAY_STAINED_GLASS_PANE)
                        .setName(Text.of(" "))
                );
            }
            for (int x = 18; x <= 26; x++) {
                gui.setSlot(x, new GuiElementBuilder()
                        .setItem(Items.RED_STAINED_GLASS_PANE)
                        .setName(Text.of(" "))
                );
            }

            //close button
            gui.setSlot(22, new GuiElementBuilder()
                    .setItem(Items.BARRIER)
                    .setName(Text.literal("Close")
                            .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.DARK_RED)))
                    .setCallback((index, clickType, actionType) -> gui.close())
            );

            //branch items
            if (PlayerDataRegistry.PLAYERDATA.branches.get("mercenary") == null || !PlayerDataRegistry.PLAYERDATA.branches.get("mercenary")) {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.IRON_SWORD)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .addLoreLine(Text.literal("Cost: " + ConfigRegistry.CONFIG.branchCost).setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GRAY)))
                        .setName(Text.literal("Mercenary")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)))
                        .setCallback((index, clickType, actionType) -> save(player, "mercenary", "§cMercenary"))
                );
            } else {
                gui.setSlot(11, new GuiElementBuilder()
                        .setItem(Items.IRON_SWORD)
                        .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                        .glow()
                        .setName(Text.literal("Mercenary")
                                .setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)))
                        .setCallback((index, clickType, actionType) -> MercenaryGui.gui(player))
                );
            }

            if (PlayerDataRegistry.PLAYERDATA.branches.get("wizardry") == null || !PlayerDataRegistry.PLAYERDATA.branches.get("wizardry")) {
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

            if (PlayerDataRegistry.PLAYERDATA.branches.get("druidry") == null || !PlayerDataRegistry.PLAYERDATA.branches.get("druidry")) {
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
            gui.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void save(ServerPlayerEntity player, String branch, String branchName) {
        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= ConfigRegistry.CONFIG.branchCost) {
            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - ConfigRegistry.CONFIG.branchCost);
            player.sendMessage(Text.of("You have selected the §a" + branchName + " §fbranch!"), false);
            PlayerDataRegistry.PLAYERDATA.branches.put(branch, true);
            PlayerDataRegistry.save(player.getUuid());
            player.closeHandledScreen();
            if (branch.equals("mercenary")) {
                MercenaryGui.gui(player);
            } else if (branch.equals("wizardry")) {
                WizardryGui.gui(player);
            } else if (branch.equals("druidry")) {
                DruidryGui.gui(player);
            }

        } else {
            player.sendMessage(Text.literal("You don't have enough skill points to unlock this branch!").setStyle(Style.EMPTY.withBold(true).withFormatting(Formatting.RED)), false);
            player.closeHandledScreen();
        }
    }
}
