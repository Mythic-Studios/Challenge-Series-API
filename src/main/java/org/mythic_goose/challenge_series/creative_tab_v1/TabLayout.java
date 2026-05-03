package org.mythic_goose.challenge_series.creative_tab_v1;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabLayout {

    public static final Map<String, Integer> SECTION_ROW = new HashMap<>();

    // Stored at init time, items are holders - stacks built later
    public static List<Item> CACHED_ITEMS = List.of();

    public static List<Item> build(List<Section> sections) {
        SECTION_ROW.clear();
        List<Item> result = new ArrayList<>();
        int row = 0;

        for (Section section : sections) {
            SECTION_ROW.put(section.id(), row);
            for (int i = 0; i < 9; i++) {
                result.add(null); // null = empty slot placeholder
            }
            row++;

            List<Item> items = section.items();
            result.addAll(items);

            int itemCount = items.size();
            int usedInLastRow = itemCount % 9;
            if (usedInLastRow != 0) {
                int padding = 9 - usedInLastRow;
                for (int i = 0; i < padding; i++) {
                    result.add(null);
                }
                row += (itemCount / 9) + 1;
            } else {
                row += itemCount / 9;
            }
        }

        CACHED_ITEMS = result;
        return result;
    }

    // Call this from buildContents, not from onInitialize
    public static List<ItemStack> buildStacks() {
        return CACHED_ITEMS.stream()
                .map(item -> item == null ? ItemStack.EMPTY : new ItemStack(item))
                .toList();
    }
}