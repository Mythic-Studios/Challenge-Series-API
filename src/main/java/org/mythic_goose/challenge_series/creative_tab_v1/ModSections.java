package org.mythic_goose.challenge_series.creative_tab_v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central registry for creative tab sections.
 *
 * <p>Register your sections from anywhere in {@code onInitialize()} —
 * no ordering constraint with the API mod is required.
 * The registry is locked automatically the first time the tab builds
 * its contents, which always happens after all mods have initialised.
 *
 * <h2>Quick start</h2>
 * <pre>{@code
 * // In your ModInitializer:
 * public void onInitialize() {
 *     ModSections.register(new SectionColored(
 *         "my_mod.gems",
 *         Component.translatable("section.my_mod.gems"),
 *         0xFF4A90D9,
 *         0xFFFFFFFF,
 *         List.of(Items.DIAMOND, Items.EMERALD)
 *     ));
 * }
 * }</pre>
 */
public final class ModSections {

    private ModSections() {}

    private static final List<Section> REGISTRY = new ArrayList<>();
    private static boolean locked = false;

    /** All registered sections. Empty until the tab first builds its contents. */
    public static List<Section> ALL = Collections.emptyList();

    /**
     * Registers a section to appear in the creative tab.
     * Call this any time during {@code onInitialize()}.
     *
     * @param section the section to add — use {@link SectionColored} or {@link SectionTextured}
     * @throws IllegalStateException if called after the tab has already built its contents
     */
    public static void register(Section section) {
        if (locked) {
            throw new IllegalStateException(
                    "ModSections.register() called after the creative tab already built its contents. " +
                            "Call register() during onInitialize().");
        }
        REGISTRY.add(section);
    }

    public static List<Section> build() {
        if (!locked) {
            ALL = Collections.unmodifiableList(new ArrayList<>(REGISTRY));
            locked = true;
        }
        return ALL;
    }
}