package org.mythic_goose.challenge_series.creative_tab_v1;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.mythic_goose.challenge_series.ChallengeSeriesAPI;

import java.util.List;

public class ModCreativeTabs {
    public static CreativeModeTab CORE;

    public static void init() {
        List<Section> sections = ModSections.build();
        TabLayout.build(sections); // populates CACHED_ITEMS and SECTION_ROW

        CORE = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(ChallengeSeriesAPI.MOD_ID, "challenge_series_api"),
                FabricCreativeModeTab.builder()
                        .icon(() -> new ItemStack(Items.DIAMOND)) // change item icon to what ever
                        .title(Component.translatable("itemGroup.challenge_series_api"))
                        .displayItems((params, output) -> {

                            // Intentionally empty — CreativeModeTabMixin overrides buildContents

                        })
                        .build());
    }
}