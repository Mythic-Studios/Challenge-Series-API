package org.mythic_goose.challenge_series.registry_v1;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Registry helper for block entity types.
 *
 * <p>Extend this class, declare your block entity types as static fields, then
 * call {@link #init()} in your {@code onInitialize()} to trigger class-loading
 * and register them all.
 *
 * <p>A block entity stores per-block data such as inventories, progress
 * counters, and custom NBT. You need three things to set one up:
 * <ol>
 *   <li>A class extending {@link BlockEntity}</li>
 *   <li>A registered {@link BlockEntityType} (this helper)</li>
 *   <li>Your block overriding {@code newBlockEntity()} to return an instance</li>
 * </ol>
 *
 * <h2>Quick start</h2>
 *
 * 1. Create your block entity class:
 * <pre>{@code
 * public class RubyFurnaceBlockEntity extends BlockEntity {
 *
 *     public RubyFurnaceBlockEntity(BlockPos pos, BlockState state) {
 *         super(MyBlockEntityRegistary.RUBY_FURNACE, pos, state);
 *     }
 * }
 * }</pre>
 *
 * 2. Register the type, passing every block it is valid for:
 * <pre>{@code
 * public class MyBlockEntityRegistary extends BlockEntityRegistary {
 *
 *     public static final BlockEntityType<RubyFurnaceBlockEntity> RUBY_FURNACE =
 *         register(
 *             "my_mod", "ruby_furnace",
 *             RubyFurnaceBlockEntity::new,
 *             MyBlocksRegistary.RUBY_FURNACE
 *         );
 *
 *     public static void init() {} // triggers static field loading
 * }
 * }</pre>
 *
 * 3. Override {@code newBlockEntity()} in your block class:
 * <pre>{@code
 * @Override
 * public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
 *     return new RubyFurnaceBlockEntity(pos, state);
 * }
 * }</pre>
 *
 * <h2>Multiple valid blocks</h2>
 * <p>If several blocks share the same block entity type, pass them all as
 * additional arguments:
 * <pre>{@code
 * register(
 *     "my_mod", "gem_storage",
 *     GemStorageBlockEntity::new,
 *     MyBlocksRegistary.RUBY_CRATE,
 *     MyBlocksRegistary.SAPPHIRE_CRATE,
 *     MyBlocksRegistary.EMERALD_CRATE
 * );
 * }</pre>
 */
public abstract class BlockEntityRegistary {

    /**
     * Registers a block entity type.
     *
     * @param namespace your mod ID
     * @param path      block entity name in snake_case
     * @param factory   constructor reference — usually {@code MyBlockEntity::new}
     * @param blocks    one or more blocks that use this block entity type;
     *                  Minecraft will warn if a block entity is placed inside
     *                  a block not listed here
     * @return the registered BlockEntityType
     */
    /**
     * Registers a block entity type.
     *
     * <p>The {@code factory} is a constructor reference matching
     * {@code (BlockPos, BlockState) -> T}, so you can always pass
     * {@code MyBlockEntity::new} as long as your block entity has the standard
     * two-argument constructor. Internally uses {@link FabricBlockEntityTypeBuilder}.
     *
     * @param namespace your mod ID
     * @param path      block entity name in snake_case
     * @param factory   constructor reference — {@code MyBlockEntity::new}
     * @param blocks    one or more blocks that use this block entity type;
     *                  Minecraft will warn if a block entity is placed inside
     *                  a block not listed here
     * @return the registered BlockEntityType
     */
    @SuppressWarnings("unchecked")
    protected static <T extends BlockEntity> BlockEntityType<T> register(
            String namespace,
            String path,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks
    ) {
        return (BlockEntityType<T>) ModRegistry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                namespace, path,
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }
}