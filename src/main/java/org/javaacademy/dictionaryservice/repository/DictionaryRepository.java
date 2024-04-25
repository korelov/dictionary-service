package org.javaacademy.dictionaryservice.repository;

import org.javaacademy.dictionaryservice.entity.World;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

@Component
public class DictionaryRepository {
    Comparator<World> worldComparator = Comparator.comparing(World::getWorld);
    private final TreeSet<World> worlds = new TreeSet<>(worldComparator);

    /**
     * Create
     */
    public World add(World world) {
        worlds.add(world);
        return world;
    }

    /**
     * Get all (READ)
     */
    public TreeSet<World> getAll() {
        return worlds;
    }

    /**
     * Find unique world (READ)
     */
    public World findByName(String name) {
        return findByWorldName(name);
    }

    /**
     * UPDATE
     */
    public void updateWorldDescription(String name, String description) {
        findByWorldName(name).setDescription(description);
    }

    /**
     * Delete (DELETE)
     */
    public boolean deleteByName(String name) {
        World byName = findByWorldName(name);
        return worlds.remove(byName);
    }

    private World findByWorldName(String name) {
        return worlds.stream()
                .filter(world -> world.getWorld().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Такого слова нет"));
    }
}
