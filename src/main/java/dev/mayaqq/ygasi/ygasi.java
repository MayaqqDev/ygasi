package dev.mayaqq.ygasi;

import dev.mayaqq.ygasi.registry.CommandRegistry;
import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.registry.StatRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ygasi implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("ygasi");

	@Override
	public void onInitialize() {
		StatRegistry.skillRegister();
		CommandRegistry.RegisterCommands();
		ConfigRegistry.load();
		LOGGER.info("You've got a skill issue!");
	}
}
