package dev.mayaqq.ygasi.events;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.util.AdvUtils;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static dev.mayaqq.ygasi.registry.ItemRegistry.SKILL_BOOK;

public class PlayerConnectEvent {
    public static void onPlayerConnect() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (!handler.player.getScoreboardTags().contains("skill_book_unlocked") && ConfigRegistry.CONFIG.enableSkillBook) {
                handler.player.addScoreboardTag("skill_book_unlocked");
                handler.player.getInventory().offerOrDrop(new ItemStack(SKILL_BOOK));
                AdvUtils.grantAdvancementCriterion(handler.player, new Identifier("ygasi", "recipes/minecraft_ygasi/skill_book"), "opened_skill_menu");
            }
        });
    }
}