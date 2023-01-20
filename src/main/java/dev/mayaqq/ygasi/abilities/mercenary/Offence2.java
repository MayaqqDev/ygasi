package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offence2 {
    public static Map<String, ArrayList<String>> playerSwords = new HashMap<>();
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/offence2")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/offence2"), "unlocked_offence2");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/offence2")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/offence2");
        }
    }
    public static void summonStand(ServerPlayerEntity player, Integer sword) {
        BlockPos pos = player.getBlockPos();
        ServerWorld world = player.getWorld();
        ArmorStandEntity armorStand = new ArmorStandEntity(world, pos.getX(), pos.getY(), pos.getZ());
        armorStand.setCustomName(Text.of(armorStand.getUuidAsString()));
        armorStand.setCustomNameVisible(true);
        armorStand.setStackInHand(Hand.MAIN_HAND, player.getMainHandStack());
        if (playerSwords.get(player.getUuidAsString()) != null) {
            playerSwords.get(player.getUuidAsString()).add(armorStand.getUuidAsString());
        } else {
            ArrayList<String> swords = new ArrayList<>();
            swords.add(armorStand.getUuidAsString());
            playerSwords.put(player.getUuidAsString(), swords);
        }
        world.spawnEntity(armorStand);
    }
}
