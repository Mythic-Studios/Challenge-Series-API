package org.mythic_goose.challenge_series.registry_v1;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public final class ModRegistry {

    private ModRegistry() {}

    public static <T> T register(Registry<T> registry, String namespace, String path, T entry) {
        return Registry.register(registry, Identifier.fromNamespaceAndPath(namespace, path), entry);
    }
}