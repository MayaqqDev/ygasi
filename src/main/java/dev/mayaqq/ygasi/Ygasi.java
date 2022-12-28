package dev.mayaqq.ygasi;

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
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
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
		StatRegistry.skillRegister();
		CommandRegistry.RegisterCommands();
		ConfigRegistry.load();

		PolymerRPUtils.addAssetSource("ygasi");
		PolymerModelData modelData = PolymerRPUtils.requestModel(Items.BOOK, new Identifier("ygasi", "item/skill_book"));

		Registry.register(Registry.ITEM, "ygasi:skill_book", SKILL_BOOK);

		LOGGER.info("You've got a skill issue!");

		if (ConfigRegistry.CONFIG.enableSkillBook) {
			ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
				if (!handler.player.getScoreboardTags().contains("skill_book_unlocked")) {
					handler.player.addScoreboardTag("skill_book_unlocked");
					handler.player.getInventory().offerOrDrop(new ItemStack(SKILL_BOOK));
					YgasiUtils.grantAdvancementCriterion(handler.player, new Identifier("ygasi", "recipes/minecraft_ygasi/skill_book"), "opened_skill_menu");
				}
			});
		}
	}
}