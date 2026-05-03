package org.mythic_goose.challenge_series.registry_v1;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Registry helper for blocks.
 *
 * <p>Extend this class, declare your blocks as static fields, then call
 * {@link #init()} in your {@code onInitialize()} to trigger class-loading
 * and register them all.
 *
 * <h2>Quick start</h2>
 * <pre>{@code
 * public class MyBlocksRegistary extends BlockRegistary {
 *
 *     public static final Block RUBY_ORE = register(
 *         "my_mod", "ruby_ore",
 *         () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE))
 *     );
 *
 *     public static void init() {} // triggers static field loading
 * }
 * }</pre>
 *
 * Then in your {@code onInitialize()}:
 * <pre>{@code
 * MyBlocksRegistary.init();
 * }</pre>
 *
 * <h2>Block IDs</h2>
 * <p>Always prefix with your mod ID — e.g. namespace {@code "my_mod"}, path {@code "ruby_ore"}.
 * The resulting in-game ID will be {@code my_mod:ruby_ore}.
 *
 * @see ItemRegistary for registering the block's item form via registerBlockItem
 */
public abstract class BlockRegistary {

    private static final List<Block> REGISTERED = new ArrayList<>();

    /**
     * Registers a block and tracks it internally.
     *
     * @param namespace your mod ID
     * @param path      block name in snake_case
     * @param factory   supplier that constructs the block
     * @return the registered block instance
     */
    protected static Block register(String namespace, String path, Supplier<Block> factory) {
        Block block = ModRegistry.register(BuiltInRegistries.BLOCK, namespace, path, factory.get());
        REGISTERED.add(block);
        return block;
    }

    /**
     * All blocks registered through this helper, in registration order.
     * Useful for iterating blocks to register block items in bulk.
     */
    public static List<Block> all() {
        return Collections.unmodifiableList(REGISTERED);
    }
}