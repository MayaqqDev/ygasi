package dev.mayaqq.ygasi.registry;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.ygasi.gui.BranchGui;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistry {
    public static void RegisterCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("skilltree")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    BranchGui.gui(player);
                    return 1;
                })
                    .then(literal("Mercenary")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();
                                BranchGui.gui(player);
                                return 1;
                            })
                    )
                    .then(literal("Wizardry")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();
                                BranchGui.gui(player);
                                return 1;
                            })
                )
                .then(literal("Druidry")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            BranchGui.gui(player);
                            return 1;
                        })
                )
        ));
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
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    ConfigRegistry.load();
                                    context.getSource().sendMessage(Text.literal("§aConfig reloaded!"));
                                    return 1;
                                })))

                )
        );
    }
}