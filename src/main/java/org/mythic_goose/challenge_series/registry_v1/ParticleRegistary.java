package org.mythic_goose.challenge_series.registry_v1;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Registry helper for custom particle types.
 *
 * <p>Extend this class, declare your particles as static fields, then call
 * {@link #init()} in your {@code onInitialize()} to trigger class-loading
 * and register them all.
 *
 * <h2>Quick start — simple particle</h2>
 * <pre>{@code
 * public class MyParticlesRegistary extends ParticleRegistary {
 *
 *     public static final SimpleParticleType RUBY_SPARKLE = registerSimple("my_mod", "ruby_sparkle");
 *
 *     public static void init() {} // triggers static field loading
 * }
 * }</pre>
 *
 * <h2>Required asset files</h2>
 * <p>Add a particle description file at:
 * {@code assets/my_mod/particles/ruby_sparkle.json}
 * <pre>{@code
 * {
 *   "textures": ["my_mod:ruby_sparkle"]
 * }
 * }</pre>
 * And the sprite at: {@code assets/my_mod/textures/particle/ruby_sparkle.png}
 *
 * <h2>Required client-side factory</h2>
 * <p>Particle rendering is client-only. Register a factory in your
 * {@code ClientModInitializer}:
 * <pre>{@code
 * ParticleFactories.register(MyParticlesRegistary.RUBY_SPARKLE, RubySparkleParticle.Factory::new);
 * }</pre>
 *
 * <h2>Spawning a particle server-side</h2>
 * <pre>{@code
 * ((ServerLevel) level).sendParticles(
 *     MyParticlesRegistary.RUBY_SPARKLE,
 *     x, y, z,        // position
 *     10,             // count
 *     0.2, 0.2, 0.2,  // spread XYZ
 *     0.05            // speed
 * );
 * }</pre>
 *
 * <h2>Custom particle data</h2>
 * <p>If your particle needs extra data (e.g. a color or radius), extend
 * {@link ParticleType} with a custom codec and use {@link #register} directly
 * instead of {@link #registerSimple}.
 */
public abstract class ParticleRegistary {

    /**
     * Registers a simple particle type with no extra data payload.
     * Suitable for most visual-only particles.
     *
     * @param namespace your mod ID
     * @param path      particle name in snake_case
     * @return the registered SimpleParticleType
     */
    @SuppressWarnings("unchecked")
    protected static SimpleParticleType registerSimple(String namespace, String path) {
        return (SimpleParticleType) ModRegistry.register(
                BuiltInRegistries.PARTICLE_TYPE,
                namespace, path,
                FabricParticleTypes.simple()
        );
    }

    /**
     * Registers a custom particle type with its own data codec.
     * Use this when your particle carries extra information (e.g. RGB color, radius).
     *
     * @param namespace your mod ID
     * @param path      particle name in snake_case
     * @param type      your custom ParticleType instance
     * @return the registered ParticleType
     */
    @SuppressWarnings("unchecked")
    protected static <T extends ParticleType<?>> T register(String namespace, String path, T type) {
        return (T) ModRegistry.register(BuiltInRegistries.PARTICLE_TYPE, namespace, path, type);
    }
}