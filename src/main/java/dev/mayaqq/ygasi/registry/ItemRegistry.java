package dev.mayaqq.ygasi.registry;

import dev.mayaqq.ygasi.items.SkillBookItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemRegistry {
    public static final SkillBookItem SKILL_BOOK = new SkillBookItem(new Item.Settings().maxCount(1), Items.BOOK);
    public static void register() {
        PolymerItemGroupUtils.builder(new Identifier("ygasi", "ygasi"))
                .displayName(Text.translatable("creative.ygasi.group"))
                .icon(() -> new ItemStack(Items.BOOK))
                .entries((enabledFeatures, entries, operatorEnabled) -> {
                    entries.add(SKILL_BOOK);
                })
    .build();
        PolymerResourcePackUtils.addModAssets("ygasi");
        PolymerModelData skillBookModelData = PolymerResourcePackUtils.requestModel(Items.BOOK, new Identifier("ygasi", "item/skill_book"));

        Registry.register(Registries.ITEM, new Identifier("ygasi", "skill_book"), SKILL_BOOK);
    }
}
