package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.util.YgasiUtils;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

public class MercenaryGui {
    public static void gui(ServerPlayerEntity player) {
        String title = Text.translatable("gui.ygasi.branch.mercenary.title").getString() + " " + Text.translatable("gui.ygasi.branch.title", player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))).formatted(Formatting.DARK_AQUA).getString();
        try {
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/mercenary")) {
                player.sendMessage(Text.translatable("gui.ygasi.branches.fail"), false);
                BranchGui.gui(player);
            } else {
                SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X6, player, false) {};

                gui.setTitle(Text.of(title));
                //background items
                for (int x = 0; x <= 53; x++) {
                    gui.setSlot(x, new GuiElementBuilder()
                            .setItem(Items.GRAY_STAINED_GLASS_PANE)
                            .setName(Text.of(" "))
                    );
                }

                //skill items
                gui.setSlot(49, new GuiElementBuilder()
                        .setItem(Items.DIAMOND_BLOCK)
                        .setName(Text.of("§c§lMercenary Unlocked!"))
                        .addLoreLine(Text.of("§3Skill Points: " + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))))
                );

                if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
                    gui.setSlot(10, new GuiElementBuilder()
                            .setItem(Items.DIAMOND_SWORD)
                            .setName(Text.translatable("gui.ygasi.mercenary.offence1"))
                            .addLoreLine(Text.translatable("gui.ygasi.mercenary.offence1.lore"))
                            .addLoreLine(Text.translatable("gui.ygasi.mercenary.offence1.lore.cost"))
                    );
                }

                gui.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}