package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.abilities.mercenary.*;
import dev.mayaqq.ygasi.gui.common.GuiCommon;
import dev.mayaqq.ygasi.gui.common.SkillGui;
import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.util.AdvUtils;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

public class MercenaryGui {
    public static void gui(ServerPlayerEntity player) {
        String title = Text.translatable("gui.ygasi.branch.mercenary.title").getString() + " " + Text.translatable("gui.ygasi.branch.title", Text.of("§3" + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)))).getString();
        try {
            if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/mercenary")) {
                player.sendMessage(Text.translatable("gui.ygasi.branches.fail"), true);
                BranchGui.gui(player);
            } else {
                SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X6, player, false) {
                    @Override
                    public void onClose() {
                        super.onClose();
                        BranchGui.gui(player);
                    }
                };

                gui.setTitle(Text.of(title));
                //background items
                GuiCommon.background(gui);

                gui.setSlot(49, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_BLOCK)
                        .setName(Text.translatable("gui.ygasi.branch.mercenary.title"))
                        .addLoreLine(Text.translatable("gui.ygasi.branch.title", Text.of((String.valueOf(player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)))).formatted(Formatting.DARK_GRAY)))
                ));

                int[] positions = {38, 19, 1, 40, 22, 4, 42, 25, 7};
                String[] advancementNames = {"offence1", "offence2", "offence3", "ninja1", "ninja2", "ninja3", "defence1", "defence2", "defence3"};
                int t1cost = ConfigRegistry.CONFIG.T1Cost;
                int t2cost = ConfigRegistry.CONFIG.T2Cost;
                int t3cost = ConfigRegistry.CONFIG.T3Cost;
                int[] costs = {t1cost, t2cost, t3cost, t1cost, t2cost, t3cost, t1cost, t2cost, t3cost};
                String[] nameKeys = {
                        "gui.ygasi.mercenary.offence1", "gui.ygasi.mercenary.offence2", "gui.ygasi.mercenary.offence3",
                        "gui.ygasi.mercenary.ninja1", "gui.ygasi.mercenary.ninja2", "gui.ygasi.mercenary.ninja3",
                        "gui.ygasi.mercenary.defence1", "gui.ygasi.mercenary.defence2", "gui.ygasi.mercenary.defence3"
                };
                Item[] items = {
                        Items.IRON_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD,
                        Items.STICK, Items.BAMBOO, Items.BLAZE_ROD,
                        Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.NETHERITE_CHESTPLATE
                };
                Class<?>[] classes = {Offence1.class, Offence2.class, Offence3.class, Ninja1.class, Ninja2.class, Ninja3.class, Defence1.class, Defence2.class, Defence3.class};
                for (int i = 0; i < positions.length; i++) {
                    int position = positions[i];
                    String advancementName = advancementNames[i];
                    String nameKey = nameKeys[i];
                    if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/" + advancementName)) {
                        if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1") && (advancementName.startsWith("ninja") || advancementName.startsWith("defence"))) {
                            GuiCommon.setDoneItem(gui, position, Items.BARRIER, nameKey, false);
                            GuiCommon.filler(gui, 50, false);
                        } else if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/ninja1") && (advancementName.startsWith("defence") || advancementName.startsWith("offence"))) {
                            GuiCommon.setDoneItem(gui, position, Items.BARRIER, nameKey, false);
                            GuiCommon.filler(gui, 50, false);
                            GuiCommon.filler(gui, 48, false);
                        } else if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/defence1") && (advancementName.startsWith("offence") || advancementName.startsWith("ninja"))) {
                            GuiCommon.setDoneItem(gui, position, Items.BARRIER, nameKey, false);
                            GuiCommon.filler(gui, 48, false);
                        } else {
                            GuiCommon.setSkillSlot(gui, player, position, items[i], nameKey, costs[i], classes[i], MercenaryGui.class);
                        }
                        if (position - 9 >= 0) {
                            GuiCommon.filler(gui, position - 9, false);
                        }
                    } else {
                        GuiCommon.setDoneItem(gui, position, items[i], nameKey, true);
                        try {
                            if (position - 9 >= 0) {
                                GuiCommon.filler(gui, position - 9, true);
                            }
                        } catch (Exception e) {
                            return;
                        }
                    }
                }

                gui.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}