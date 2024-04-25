package org.javaacademy.dictionaryservice.repository;

import org.javaacademy.dictionaryservice.entity.World;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

@Component
public class DictionaryRepository {
    Comparator<World> worldComparator = Comparator.comparing(World::getEnglishWord);
    private final TreeSet<World> worlds = new TreeSet<>(worldComparator);

    /**
     * Create
     */
    public void add(World world) {
        worlds.add(world);
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
    public World findEnglishWorld(String engWorld) {
        return findByEnglishWorld(engWorld);
    }

    /**
     * UPDATE
     */
    public void updateWorldRussianTranslation(String engWorld, String russianTranslation) {
        findByEnglishWorld(engWorld).setRussianDescription(russianTranslation);
    }

    /**
     * Delete (DELETE)
     */
    public boolean deleteByEnglishWorld(String engWorld) {
        World byEnglishWorld = worlds.stream()
                .filter(world -> world.getEnglishWord().equals(engWorld))
                .findFirst()
                .orElse(null);
        return worlds.remove(byEnglishWorld);
    }

    private World findByEnglishWorld(String engWorld) {
        return worlds.stream()
                .filter(world -> world.getEnglishWord().equals(engWorld))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Такого слова нет"));
    }
}
