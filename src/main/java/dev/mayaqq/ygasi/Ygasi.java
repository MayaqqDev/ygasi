package dev.mayaqq.ygasi;

import dev.mayaqq.ygasi.registry.*;
import dev.mayaqq.ygasi.secrete.Frog;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ygasi implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ygasi");

	@Override
	public void onInitialize() {
		//load the config data and register everything that needs to be registered on startup
		ConfigRegistry.load();
		ItemRegistry.register();
		StatRegistry.register();
		CommandRegistry.register();
		EventRegistry.register();

		Frog.blender();

		LOGGER.info("You've Got A Skill Issue (YGASI has been initialized!)");
	}
}