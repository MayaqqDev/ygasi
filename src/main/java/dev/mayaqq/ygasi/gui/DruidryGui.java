package dev.mayaqq.ygasi.gui;

import dev.mayaqq.ygasi.registry.PlayerDataRegistry;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import java.util.UUID;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

public class DruidryGui {
    public static void gui(ServerPlayerEntity player) {
        try {
            UUID playerUUID = player.getUuid();
            PlayerDataRegistry.load(playerUUID);
            if (PlayerDataRegistry.PLAYERDATA.branches.get("druidry") == null || !PlayerDataRegistry.PLAYERDATA.branches.get("druidry")) {
                player.sendMessage(Text.of("§cYou have not unlocked this branch yet!"), false);
                BranchGui.gui(player);
            } else {
                SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X6, player, false) {};

                gui.setTitle(Text.of( "§a§lDruidry " + "§3Skill Points: " + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS))));

                for (int x = 0; x <= 53; x++) {
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
