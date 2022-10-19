package dev.mayaqq.ygasi.registry;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.mayaqq.ygasi.CreatePlayerData;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import java.io.IOException;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistry {
    public static void RegisterCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("skilltree")
                .executes(context -> {
                    try {
                        CreatePlayerData.createPlayerData(context.getSource().getPlayerOrThrow().getUuid());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    context.getSource().sendMessage(Text.literal("§aSkillData Registered!"));
                    return 1;
                })));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("ygasi")
                .requires(source -> source.hasPermissionLevel(4))
                .then(literal("reset")
                        .then(CommandManager.argument("target", EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                    player.resetStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS));
                                    context.getSource().sendMessage(Text.literal("§aSkill Points reset to 0 for " + player.getEntityName() + "."));
                                    return 1;
                                })))
                .then(literal("add")
                        .then(CommandManager.argument("target", EntityArgumentType.player())
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                            player.increaseStat(SKILL_POINTS, IntegerArgumentType.getInteger(context, "amount"));
                                            context.getSource().sendMessage(Text.literal("§aSkill Points increased by " + IntegerArgumentType.getInteger(context, "amount") + " for " + player.getEntityName() + "."));
                                            return 1;
                                        }))))
                .then(literal("get")
                        .then(CommandManager.argument("target", EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                    context.getSource().sendMessage(Text.literal("§a" + player.getEntityName() + " has " + player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) + " Unspent Skill Points."));
                                    return 1;
                                })))
        ));
    }
}
