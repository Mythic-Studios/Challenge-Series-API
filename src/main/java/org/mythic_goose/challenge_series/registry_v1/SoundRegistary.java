package org.mythic_goose.challenge_series.registry_v1;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

/**
 * Registry helper for custom sound events.
 *
 * <p>Extend this class, declare your sounds as static fields, then call
 * {@link #init()} in your {@code onInitialize()} to trigger class-loading
 * and register them all.
 *
 * <h2>Quick start</h2>
 * <pre>{@code
 * public class MySoundsRegistary extends SoundRegistary {
 *
 *     public static final SoundEvent RUBY_CHIME = register("my_mod", "ruby_chime");
 *
 *     public static void init() {} // triggers static field loading
 * }
 * }</pre>
 *
 * <h2>Required asset files</h2>
 * <p>Registering the sound event is only the code side. You also need:
 * <ol>
 *   <li>An entry in {@code assets/my_mod/sounds.json}:
 *   <pre>{@code
 * {
 *   "ruby_chime": {
 *     "sounds": ["my_mod:ruby_chime"]
 *   }
 * }
 *   }</pre></li>
 *   <li>The audio file at {@code assets/my_mod/sounds/ruby_chime.ogg}</li>
 * </ol>
 *
 * <h2>Playing a sound</h2>
 * <pre>{@code
 * level.playSound(null, pos, MySoundsRegistary.RUBY_CHIME, SoundSource.BLOCKS, 1.0f, 1.0f);
 * }</pre>
 *
 * <h2>Pitch and volume</h2>
 * <ul>
 *   <li>Volume: {@code 1.0f} = normal, higher values carry further</li>
 *   <li>Pitch: {@code 1.0f} = normal, {@code 0.5f} = lower, {@code 2.0f} = higher</li>
 * </ul>
 */
public abstract class SoundRegistary {

    /**
     * Registers a sound event. The sound file and {@code sounds.json} entry
     * must be added manually to your mod's assets.
     *
     * @param namespace your mod ID
     * @param path      sound name in snake_case, matching your sounds.json key
     * @return the registered SoundEvent
     */
    protected static SoundEvent register(String namespace, String path) {
        SoundEvent event = SoundEvent.createVariableRangeEvent(
                Identifier.fromNamespaceAndPath(namespace, path)
        );
        return ModRegistry.register(BuiltInRegistries.SOUND_EVENT, namespace, path, event);
    }
}