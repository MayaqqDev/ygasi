package dev.mayaqq.ygasi.registry;

import dev.mayaqq.ygasi.items.SkillBookItem;
import eu.pb4.polymer.api.item.PolymerItemGroup;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final PolymerItemGroup YGASI_GROUP = PolymerItemGroup.create(new Identifier("ygasi"), Text.translatable("creative.ygasi.group"), () -> new ItemStack(Items.BOOK));

    public static final SkillBookItem SKILL_BOOK = new SkillBookItem(new Item.Settings().group(YGASI_GROUP).maxCount(1), Items.BOOK);
    public static void register() {
        PolymerRPUtils.addAssetSource("ygasi");

        PolymerModelData skillBookModelData = PolymerRPUtils.requestModel(Items.BOOK, new Identifier("ygasi", "item/skill_book"));

        Registry.register(Registry.ITEM, "ygasi:skill_book", SKILL_BOOK);
    }
}
