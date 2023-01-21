package dev.mayaqq.ygasi.events;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import java.util.UUID;

import static dev.mayaqq.ygasi.abilities.mercenary.Offence2.playerSwords;
import static dev.mayaqq.ygasi.events.TickEvent.swords;
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
    public static void onPlayerDisconnect() {
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            if (playerSwords.get(handler.player.getUuidAsString()) != null) {
                playerSwords.get(handler.player.getUuidAsString()).forEach(uuid -> {
                    Entity sword = handler.player.getWorld().getEntity(UUID.fromString(uuid));
                    sword.remove(Entity.RemovalReason.DISCARDED);
                });
                playerSwords.remove(handler.player.getUuidAsString());
                swords.remove(handler.player.getUuidAsString());
            }
        });
    }
}