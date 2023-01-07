package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.util.YgasiUtils;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

public class ExtraGui {
    public static void gui(ServerPlayerEntity player) {
        try {
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/extra")) {
                player.sendMessage(Text.of("§cYou have not unlocked this branch yet!"), false);
                BranchGui.gui(player);
            } else {
                SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X3, player, false) {};

                gui.setTitle(Text.of( "§9§lExtra " + "§3Skill Points: " + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))));

                for (int x = 0; x <= 26; x++) {
                    gui.setSlot(x, new GuiElementBuilder()
                            .setItem(Items.GRAY_STAINED_GLASS_PANE)
                            .setName(Text.of(" "))
                    );
                }
                gui.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}