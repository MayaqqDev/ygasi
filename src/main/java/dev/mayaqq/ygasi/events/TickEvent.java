package dev.mayaqq.ygasi.events;

import dev.mayaqq.ygasi.abilities.mercenary.Offence2;
import dev.mayaqq.ygasi.util.AdvUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;
import static dev.mayaqq.ygasi.abilities.mercenary.Offence2.playerSwords;
import static dev.mayaqq.ygasi.abilities.mercenary.Offence2.playerSwordsBlacklist;

public class TickEvent {
    public static Map<String, Integer> playerCharge = new HashMap<>();
    public static Map<String, Integer> swords = new HashMap<>();
    private static Integer tick = 0;
    public static void onTick() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tick++;
            server.getPlayerManager().getPlayerList().forEach(player -> {
                if (playerSwords.get(player.getUuidAsString()) != null && playerSwordsBlacklist.get(player.getUuidAsString()) == null) {
                    playerSwords.get(player.getUuidAsString()).forEach(uuid -> {
                        player.getWorld().getEntity(UUID.fromString(uuid)).setPos(player.getX(), player.getY() + 2, player.getZ());
                    });
                }
                if (tick == 20) {
                    if (player.isSneaking() && player.getMainHandStack().getItem() instanceof SwordItem && AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/offence2")) {
                        if (swords.getOrDefault(player.getUuidAsString(), 0) >= 3) {
                            return;
                        }
                        if (playerCharge.getOrDefault(player.getUuidAsString(), 0) == 5) {
                            playerCharge.remove(player.getUuidAsString());

                            swords.put(player.getUuidAsString(), swords.getOrDefault(player.getUuidAsString(), 0) + 1);
                            Offence2.summonStand(player, swords.get(player.getUuidAsString()));
                            player.playSound(SoundEvent.of(new Identifier("entity.experience_orb.pickup")), SoundCategory.PLAYERS, 1.0F, 1.0F);
                            LOGGER.info("Sword charge: " + swords.get(player.getUuidAsString()));
                        } else {
                            playerCharge.put(player.getUuidAsString(), playerCharge.getOrDefault(player.getUuidAsString(), 0) + 1);
                            player.playSound(SoundEvent.of(new Identifier("block.note_block.harp")), SoundCategory.PLAYERS, 1.0F, playerCharge.get(player.getUuidAsString()).floatValue() / 3);
                            LOGGER.info("Player charge: " + playerCharge.get(player.getUuidAsString()));
                        }
                    } else {
                        playerCharge.remove(player.getUuidAsString());
                    }
                }
            });
            if (tick == 20) {
                tick = 0;
            }
        });
    }
}
