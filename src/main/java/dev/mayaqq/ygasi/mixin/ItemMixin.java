package dev.mayaqq.ygasi.mixin;

import dev.mayaqq.ygasi.events.TickEvent;
import dev.mayaqq.ygasi.util.Multithreading;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static dev.mayaqq.ygasi.abilities.mercenary.Offence2.playerSwords;
import static dev.mayaqq.ygasi.abilities.mercenary.Offence2.playerSwordsBlacklist;

@Mixin(Item.class)
public class ItemMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "use", at = @At("HEAD"))
    private void injectRightClickBehavior(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (user.getMainHandStack().getItem() instanceof SwordItem && TickEvent.swords.getOrDefault(user.getUuidAsString(), 0) >= 1) {
            LOGGER.info("Player " + user.getEntityName() + " launched " + TickEvent.swords.get(user.getUuidAsString()) + " swords!");
            ArrayList<String> swords = new ArrayList<>();
            playerSwords.get(user.getUuidAsString()).forEach(uuid -> {
                ServerPlayerEntity player = (ServerPlayerEntity) user;
                ArmorStandEntity sword = (ArmorStandEntity) player.getWorld().getEntity(UUID.fromString(uuid));
                swords.add(uuid);
                float yaw = player.getYaw(1);
                float pitch = player.getPitch(1);
                yaw = (float) (yaw - Math.PI);
                pitch = (float) (pitch + Math.PI / 2);
                sword.setRightArmRotation(new EulerAngle(pitch, yaw, sword.getRightArmRotation().getRoll()));
                Multithreading.schedule(() -> {
                    playerSwordsBlacklist.put(player.getUuidAsString(), swords);
                    ServerWorld serverWorld = (ServerWorld) world;
                    boolean isAir = true;
                    while (isAir) {
                        Vec3d rotation = player.getRotationVec(1.0f);
                        sword.setPos(sword.getX() + rotation.x, sword.getY() + rotation.y, sword.getZ() + rotation.z);
                        BlockPos[] relativeBlockPositions = {new BlockPos(1, 1.5F, 0), new BlockPos(-1, 1.5F, 0), new BlockPos(0, 1.5F, 1), new BlockPos(0, 1.5F, -1)};
                        BlockPos pos = sword.getBlockPos();
                        boolean hitTarget = true;
                        for (BlockPos relativeBlockPos : relativeBlockPositions) {
                            BlockPos blockPos = pos.add(relativeBlockPos);
                            Block block = sword.world.getBlockState(blockPos).getBlock();
                            if(block != Blocks.AIR && hitTarget) {
                                LOGGER.info("Sword hit a block!");
                                hitTarget = onHit(serverWorld, player, sword, hitTarget);
                                isAir = false;
                            }
                            double entityCheckRadius = 1;
                            for (PlayerEntity pl : world.getPlayers()) {
                                if (pl.distanceTo(sword) <= entityCheckRadius && pl != player && pl.getScoreboardTeam() != player.getScoreboardTeam() && hitTarget) {
                                    LOGGER.info("Sword hit a player!");
                                    //create an explosion at the position of the sword
                                    hitTarget = onHit(serverWorld, player, sword, hitTarget);
                                    isAir = false;
                                }
                            }
                        }
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }, 1, TimeUnit.SECONDS);
            });
            Multithreading.schedule(() -> {
                playerSwords.remove(user.getUuidAsString());
                TickEvent.swords.remove(user.getUuidAsString());
                playerSwordsBlacklist.remove(user.getUuidAsString());
            }, 2, TimeUnit.SECONDS);
        }
    }
    public boolean onHit(ServerWorld world, ServerPlayerEntity player, ArmorStandEntity sword, Boolean hitTraget) {
        if (hitTraget) {
            LOGGER.info("Sword hit a target!");
            world.createExplosion(sword, sword.getX(), sword.getY(), sword.getZ(), 1, World.ExplosionSourceType.NONE);
            sword.remove(Entity.RemovalReason.DISCARDED);
            return false;
        }
        return true;
    }
}
