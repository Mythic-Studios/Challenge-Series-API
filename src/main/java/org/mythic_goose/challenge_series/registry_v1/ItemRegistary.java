package org.mythic_goose.challenge_series.registry_v1;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Registry helper for items.
 *
 * <p>Extend this class, declare your items as static fields, then call
 * {@link #init()} in your {@code onInitialize()} to trigger class-loading
 * and register them all.
 *
 * <h2>Quick start — standalone item</h2>
 * <pre>{@code
 * public class MyItemsRegistary extends ItemRegistary {
 *
 *     public static final Item RUBY = register(
 *         "my_mod", "ruby",
 *         () -> new Item(new Item.Properties())
 *     );
 *
 *     public static void init() {} // triggers static field loading
 * }
 * }</pre>
 *
 * <h2>Quick start — block item</h2>
 * <p>To give a block an item form (so it appears in inventories), use
 * {@link #registerBlockItem}. Call this after the block is registered:
 * <pre>{@code
 * public static final Item RUBY_ORE_ITEM = registerBlockItem(
 *     "my_mod", "ruby_ore", MyBlocksRegistary.RUBY_ORE
 * );
 * }</pre>
 *
 * <h2>Keeping blocks and items in sync</h2>
 * <p>A common pattern is to declare both in the same class:
 * <pre>{@code
 * public static final Block RUBY_ORE      = BlockRegistary.register(...);
 * public static final Item  RUBY_ORE_ITEM = registerBlockItem("my_mod", "ruby_ore", RUBY_ORE);
 * }</pre>
 */
public abstract class ItemRegistary {

    /**
     * Registers a custom item.
     *
     * @param namespace your mod ID
     * @param path      item name in snake_case
     * @param factory   supplier that constructs the item
     * @return the registered item instance
     */
    protected static Item register(String namespace, String path, Function<Item.Properties, Item> factory) {
        var key = net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                Identifier.fromNamespaceAndPath(namespace, path)
        );
        Item item = factory.apply(new Item.Properties().setId(key));
        return ModRegistry.register(BuiltInRegistries.ITEM, namespace, path, item);
    }

    /**
     * Registers a {@link BlockItem} so a block can be held and placed from inventory.
     *
     * <p>The {@code path} must match the block's registered path exactly,
     * otherwise the item will have a different ID to the block.
     *
     * @param namespace your mod ID
     * @param path      must match the block's registered path exactly
     * @param block     the block this item represents
     * @return the registered BlockItem instance
     */
    protected static Item registerBlockItem(String namespace, String path, Block block) {
        var key = net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                Identifier.fromNamespaceAndPath(namespace, path)
        );
        return ModRegistry.register(
                BuiltInRegistries.ITEM,
                namespace, path,
                new BlockItem(block, new Item.Properties().setId(key))
        );
    }
}