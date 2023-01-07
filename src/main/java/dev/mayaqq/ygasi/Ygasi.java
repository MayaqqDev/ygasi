package dev.mayaqq.ygasi;

import dev.mayaqq.ygasi.events.RegisterEvents;
import dev.mayaqq.ygasi.items.SkillBookItem;
import dev.mayaqq.ygasi.registry.CommandRegistry;
import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.registry.StatRegistry;
import dev.mayaqq.ygasi.util.YgasiUtils;
import eu.pb4.polymer.api.item.PolymerItemGroup;
import eu.pb4.polymer.api.item.PolymerItemUtils;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ygasi implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ygasi");

	public static final PolymerItemGroup YGASI_GROUP = PolymerItemGroup.create(new Identifier("ygasi"), Text.translatable("creative.ygasi.group"), () -> new ItemStack(Items.BOOK));

	public static final SkillBookItem SKILL_BOOK = new SkillBookItem(new Item.Settings().group(YGASI_GROUP).maxCount(1), Items.BOOK);

	@Override
	public void onInitialize() {
		ConfigRegistry.load();
		StatRegistry.register();
		CommandRegistry.register();
		RegisterEvents.register();

		PolymerRPUtils.addAssetSource("ygasi");
		PolymerModelData modelData = PolymerRPUtils.requestModel(Items.BOOK, new Identifier("ygasi", "item/skill_book"));

		Registry.register(Registry.ITEM, "ygasi:skill_book", SKILL_BOOK);

		LOGGER.info("You've got a skill issue!");
	}
}