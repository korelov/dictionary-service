package org.javaacademy.dictionaryservice.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.WorldDto;
import org.javaacademy.dictionaryservice.entity.World;
import org.javaacademy.dictionaryservice.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public World create(WorldDto dto) {
        World world = new World(dto.getEnglishWord(), dto.getRussianTranslation());
        return dictionaryRepository.add(world);
    }

    public TreeSet<World> getAll() {
        return dictionaryRepository.getAll();
    }

    public World getByEnglishWorld(String engWorld) {
        return dictionaryRepository.findEnglishWorld(engWorld);

    }

    public void updateWorldRussianTranslation(String engWorld, WorldDto dto) {
        dictionaryRepository.updateWorldRussianTranslation(engWorld, dto.getRussianTranslation());
    }

    public boolean deleteByEnglishWorld(String engWorld) {
        return dictionaryRepository.deleteByEnglishWorld(engWorld);
    }
}
