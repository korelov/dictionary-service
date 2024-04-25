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
        World world = new World(dto.getWorld(), dto.getDescription());
        return dictionaryRepository.add(world);
    }

    public TreeSet<World> getAll() {
        return dictionaryRepository.getAll();
    }

    public World getByName(String name) {
        return dictionaryRepository.findByName(name);

    }

    public void updateWorldDescription(String name, WorldDto dto) {
        dictionaryRepository.updateWorldDescription(name, dto.getDescription());
    }

    public boolean deleteByName(String name) {
        return dictionaryRepository.deleteByName(name);
    }
}
