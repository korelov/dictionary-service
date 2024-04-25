package org.javaacademy.dictionaryservice.repository;

import lombok.SneakyThrows;
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
    @SneakyThrows
    public void add(World world) {
        Thread.sleep(3000);
        worlds.add(world);
    }

    /**
     * Get all (READ)
     */
    @SneakyThrows
    public TreeSet<World> getAll() {
        Thread.sleep(3000);
        return worlds;
    }

    /**
     * Find unique world (READ)
     */
    @SneakyThrows
    public World findEnglishWorld(String engWorld) {
        Thread.sleep(3000);
        return findByEnglishWorld(engWorld);
    }

    /**
     * UPDATE
     */
    @SneakyThrows
    public void updateWorldRussianTranslation(String engWorld, String russianTranslation) {
        Thread.sleep(3000);
        findByEnglishWorld(engWorld).setRussianDescription(russianTranslation);
    }

    /**
     * Delete (DELETE)
     */
    @SneakyThrows
    public boolean deleteByEnglishWorld(String engWorld) {
        Thread.sleep(3000);
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
