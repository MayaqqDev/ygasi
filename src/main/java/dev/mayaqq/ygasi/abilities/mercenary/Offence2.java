package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Offence2 {
    public static Map<String, ArrayList<String>> playerSwords = new HashMap<>();
    public static Map<String, ArrayList<String>> playerSwordsBlacklist = new HashMap<>();

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
        ArmorStandEntity armorStand = new ArmorStandEntity(world, pos.getX(), pos.getY() - 1, pos.getZ());
        armorStand.setCustomName(Text.of(armorStand.getUuidAsString()));
        armorStand.setStackInHand(Hand.MAIN_HAND, player.getMainHandStack());
        armorStand.setInvisible(true);
        armorStand.setNoGravity(true);
        armorStand.setNoDrag(true);
        armorStand.setInvulnerable(true);
        armorStand.setRightArmRotation(new EulerAngle(0F, 270F, 270F));
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
