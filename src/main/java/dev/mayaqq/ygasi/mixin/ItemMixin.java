package dev.mayaqq.ygasi.mixin;

import dev.mayaqq.ygasi.events.ClickEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void injectRightClickBehavior(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (user.getMainHandStack().getItem() instanceof SwordItem && ClickEvent.swords.getOrDefault(user.getUuidAsString(), 0) >= 1) {
            LOGGER.info("Player " + user.getEntityName() + " launched " + ClickEvent.swords.get(user.getUuidAsString()) + " swords!");
            ClickEvent.swords.remove(user.getUuidAsString());
        }
    }
}
