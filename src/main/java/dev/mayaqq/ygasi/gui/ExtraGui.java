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
        String title = Text.translatable("gui.ygasi.branch.extra.title").getString() + " " + Text.translatable("gui.ygasi.branch.title", player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))).getString();
        try {
            if (!YgasiUtils.getAdvancementProgress(player, "minecraft", "ygasi/extra")) {
                player.sendMessage(Text.translatable("gui.ygasi.branches.fail"), false);
                BranchGui.gui(player);
            } else {
                SkillGui gui = new SkillGui(ScreenHandlerType.GENERIC_9X3, player, false) {};

                gui.setTitle(Text.of( title));

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