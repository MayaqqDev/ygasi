package dev.mayaqq.ygasi.gui.common;

import dev.mayaqq.ygasi.gui.BranchGui;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;

public class GuiCommon {
    public static void setDoneItem(SkillGui gui, int index, Item item, String nameKey, Boolean glow) {
        GuiElementBuilder builder = new GuiElementBuilder();
        builder.setItem(item);
        builder.hideFlag(ItemStack.TooltipSection.MODIFIERS);
        builder.setName(Text.translatable(nameKey));
        if (glow) {
            builder.glow();
        }
        builder.addLoreLine(Text.translatable(nameKey + ".lore"));
        gui.setSlot(index, builder.build());
    }
    public static void filler(SkillGui gui, int index, Boolean done) {
        if (done) {
            gui.setSlot(index, new GuiElementBuilder()
                    .setItem(Items.LIME_STAINED_GLASS_PANE)
                    .setName(Text.of(" "))
            );
        } else {
            gui.setSlot(index, new GuiElementBuilder()
                    .setItem(Items.RED_STAINED_GLASS_PANE)
                    .setName(Text.of(" "))
            );
        }
    }
    public static void setSkillSlot(SkillGui gui, ServerPlayerEntity player, int itemIndex, Item item, String nameKey, int cost, Class<?> skillClass, Class<?> guiClass) {
        gui.setSlot(itemIndex, new GuiElementBuilder()
                .setItem(item)
                .hideFlag(ItemStack.TooltipSection.MODIFIERS)
                .setName(Text.translatable(nameKey))
                .addLoreLine(Text.translatable(nameKey + ".lore"))
                .addLoreLine(Text.translatable("gui.ygasi.branch.cost", Text.of("§8" + cost)))
                .setCallback((index, clickType, actionType) -> {
                            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) >= cost) {
                                try {
                                    skillClass.getMethod("give", ServerPlayerEntity.class).invoke(null, player);
                                    player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(SKILL_POINTS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(SKILL_POINTS)) - cost);
                                    guiClass.getMethod("gui", ServerPlayerEntity.class).invoke(null, player);
                                    player.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                player.sendMessage(Text.translatable("gui.ygasi.branches.no.skill"), false);
                            }
                        }
                ));
    }
    public static void background(SkillGui gui) {
        for (int x = 0; x <= 53; x++) {
            gui.setSlot(x, new GuiElementBuilder()
                    .setItem(Items.GRAY_STAINED_GLASS_PANE)
                    .setName(Text.of(" "))
            );
        }

        for (int i = 48; i <= 50; i++) {
            gui.setSlot(i, new GuiElementBuilder()
                    .setItem(Items.LIME_STAINED_GLASS_PANE)
                    .setName(Text.of(" "))
            );
        }
        gui.setSlot(45, new GuiElementBuilder()
                .setItem(Items.ARROW)
                .setName(Text.translatable("gui.ygasi.branches.back"))
                .setCallback((index, clickType, actionType) -> {
                    BranchGui.gui(gui.getPlayer());
                })
        );
    }
}
