package dev.mayaqq.ygasi.items;

import dev.mayaqq.ygasi.gui.BranchGui;
import eu.pb4.polymer.api.item.PolymerItemUtils;
import eu.pb4.polymer.api.item.SimplePolymerItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkillBookItem extends SimplePolymerItem {

    public SkillBookItem(Settings settings, Item polymerItem) {
        super(settings, polymerItem);
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, ServerPlayerEntity player) {
        ItemStack out = PolymerItemUtils.createItemStack(itemStack, player);
        out.addEnchantment(Enchantments.LURE, 0);
        out.setCustomName(Text.translatable("item.ygasi.skill_book").formatted(Formatting.GOLD).formatted(Formatting.BOLD));
        return out;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        BranchGui.gui(player);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return 1;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.ygasi.skill_book.tooltip").formatted(Formatting.GRAY));
    }
}