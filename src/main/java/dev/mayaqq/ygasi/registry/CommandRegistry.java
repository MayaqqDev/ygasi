package dev.mayaqq.ygasi.registry;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.ygasi.gui.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.File;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;
import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS_TOTAL;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistry {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("skilltree")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    BranchGui.gui(player);
                    return 1;
                })
                    .then(literal("Mercenary")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();
                                MercenaryGui.gui(player);
                                return 1;
                            })
                    )
                    .then(literal("Wizardry")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();
                                WizardryGui.gui(player);
                                return 1;
                            })
                )
                .then(literal("Druidry")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            DruidryGui.gui(player);
                            return 1;
                        })
                )
        ));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("ygasi")
                .requires(source -> source.hasPermissionLevel(4))
                .then(literal("skillpoints")
                    .then(literal("reset")
                            .then(CommandManager.argument("target", EntityArgumentType.player())
                                    .executes(context -> {
                                        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                        player.resetStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS));
                                        player.resetStat(Stats.CUSTOM.getOrCreateStat(StatRegistry.SKILL_POINTS_TOTAL));
                                        context.getSource().sendMessage(Text.translatable("commands.ygasi.skillpoints.reset", player.getEntityName()).formatted(Formatting.GREEN));
                                        return 1;
                                    })))
                    .then(literal("add")
                            .then(CommandManager.argument("target", EntityArgumentType.player())
                                    .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                int amount = IntegerArgumentType.getInteger(context, "amount");
                                                ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                                player.increaseStat(SKILL_POINTS, amount);
                                                player.increaseStat(SKILL_POINTS_TOTAL, amount);
                                                player.sendMessage(Text.translatable("commands.ygasi.skillpoints.add", amount, player.getName()).formatted(Formatting.GREEN));
                                                return 1;
                                            }))))
                    .then(literal("get")
                            .then(CommandManager.argument("target", EntityArgumentType.player())
                                    .executes(context -> {
                                        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "target");
                                        context.getSource().sendMessage(Text.translatable("commands.ygasi.skillpoints.get", player.getEntityName(), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS_TOTAL))).formatted(Formatting.GREEN));
                                        return 1;
                                    }))))
                .then(literal("config")
                        .executes(context -> {
                            ConfigGui.gui(context.getSource().getPlayer());
                            return 1;
                        })
                        .then(literal("reload")
                                .executes(context -> {
                                    ConfigRegistry.load();
                                    context.getSource().sendMessage(Text.translatable("commands.ygasi.config.reload").formatted(Formatting.GREEN));
                                    return 1;
                                })))

                )
        );
    }
}