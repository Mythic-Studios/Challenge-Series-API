package org.mythic_goose.challenge_series;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.mythic_goose.challenge_series.creative_tab_v1.ModCreativeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChallengeSeriesAPI implements ModInitializer {
	public static final String MOD_ID = "challenge_series";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ServerLifecycleEvents.SERVER_STARTING.register(server -> ModCreativeTabs.init());
	}
}