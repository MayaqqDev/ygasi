package dev.mayaqq.ygasi.events;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.ItemStack;

import static dev.mayaqq.ygasi.registry.ItemRegistry.SKILL_BOOK;

public class PlayerConnectEvent {
    public static void onPlayerConnect() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            //if the player has not joined yet, they will get a skill book if the option is turned on
            if (!handler.player.getScoreboardTags().contains("skill_book_unlocked") && ConfigRegistry.CONFIG.enableSkillBook) {
                handler.player.addScoreboardTag("skill_book_unlocked");
                handler.player.getInventory().offerOrDrop(new ItemStack(SKILL_BOOK));
            }
        });
    }
}