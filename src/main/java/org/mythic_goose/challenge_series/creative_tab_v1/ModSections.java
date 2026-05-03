package org.mythic_goose.challenge_series.creative_tab_v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central registry for creative tab sections.
 *
 * <p>Other mods register their sections by calling {@link #register(Section)}
 * inside their {@link net.fabricmc.api.ModInitializer#onInitialize()} method.
 * The API will call {@link #build()} automatically — you do not need to call it yourself.
 *
 * <h2>Quick start</h2>
 * <pre>{@code
 * // In your ModInitializer:
 * public void onInitialize() {
 *     ModSections.register(new SectionColored(
 *         "my_mod.gems",
 *         Component.translatable("section.my_mod.gems"),
 *         0xFF4A90D9,   // banner color (ARGB)
 *         0xFFFFFFFF,   // text color (ARGB)
 *         List.of(Items.DIAMOND, Items.EMERALD)
 *     ));
 * }
 * }</pre>
 *
 * <h2>Colored vs textured sections</h2>
 * <p>Use {@link SectionColored} for a flat color banner, or {@link SectionTextured}
 * for a custom texture:
 * <pre>{@code
 * // Flat color:
 * new SectionColored("my_mod.tools", title, 0xFF8B0000, 0xFFFFFFFF, items)
 *
 * // Custom texture (place PNG at: resources/assets/my_mod/textures/gui/tab_overlay/weapons.png)
 * SectionTextured.of("my_mod", "weapons", title, 0xFFFFFFFF, items)
 * }</pre>
 *
 * <h2>ARGB color format</h2>
 * <p>Colors are in {@code 0xAARRGGBB} format. Always use {@code 0xFF} as the
 * alpha prefix for fully opaque colors. For example:
 * <ul>
 *   <li>{@code 0xFF1a1a2e} — dark navy</li>
 *   <li>{@code 0xFFcc6600} — orange</li>
 *   <li>{@code 0xFFFFFFFF} — white (recommended for text)</li>
 * </ul>
 *
 * <h2>Section IDs</h2>
 * <p>Always prefix your section ID with your mod ID to avoid conflicts with
 * other mods using this API:
 * <pre>{@code
 * // Good:
 * "my_mod.weapons"
 *
 * // Bad — may conflict with another mod:
 * "weapons"
 * }</pre>
 */
public final class ModSections {

    private ModSections() {}

    private static final List<Section> REGISTRY = new ArrayList<>();

    /** All registered sections. Empty until {@link #build()} is called by the API. */
    public static List<Section> ALL = Collections.emptyList();

    /**
     * Registers a section to appear in the creative tab.
     *
     * <p>Call this in your mod's {@code onInitialize()}. The order sections are
     * registered determines the order they appear in the tab.
     *
     * @param section the section to register — use {@link SectionColored} or
     *                {@link SectionTextured}
     * @throws IllegalStateException if called after the API has already initialised
     */
    public static void register(Section section) {
        if (ALL != Collections.EMPTY_LIST) {
            throw new IllegalStateException(
                    "ModSections.register() called after init() — " +
                            "call register() in onInitialize(), before the API initialises.");
        }
        REGISTRY.add(section);
    }

    /**
     * Freezes the registry and returns the full ordered section list.
     * Called internally by the API — do not call this yourself.
     */
    public static List<Section> build() {
        ALL = Collections.unmodifiableList(new ArrayList<>(REGISTRY));
        return ALL;
    }
}