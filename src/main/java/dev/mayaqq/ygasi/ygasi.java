package dev.mayaqq.ygasi;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ygasi implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ygasi");

	@Override
	public void onInitialize() {
		StatRegistry.skillRegister();
		LOGGER.info("You've got a skill issue!");
	}
}
