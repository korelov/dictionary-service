package org.javaacademy.dictionaryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.javaacademy.dictionaryservice.dto.WorldDto;
import org.javaacademy.dictionaryservice.dto.WorldDtoRq;
import org.javaacademy.dictionaryservice.dto.WorldDtoRs;
import org.javaacademy.dictionaryservice.entity.World;
import org.javaacademy.dictionaryservice.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public WorldDtoRs create(WorldDtoRq dto) {
        World world = new World(dto.getEnglishWord(), dto.getRussianDescription());
        dictionaryRepository.add(world);
        return convertToDtoRs(world);
    }

    public List<WorldDtoRs> getAll() {
        return dictionaryRepository.getAll().stream()
                .map(this::convertToDtoRs).toList();
    }

    public World getByEnglishWorld(String engWorld) {
        return dictionaryRepository.findEnglishWorld(engWorld);

    }

    public void updateWorldRussianTranslation(String engWorld, WorldDtoRq dto) {
        dictionaryRepository.updateWorldRussianTranslation(engWorld,
                dto.getRussianDescription());
    }

    public boolean deleteByEnglishWorld(String engWorld) {
        return dictionaryRepository.deleteByEnglishWorld(engWorld);
    }

    @SneakyThrows
    public WorldDto<List<WorldDtoRs>> getWorlds(Integer startElement, Integer pageSize) {
        Thread.sleep(3000);
        int totalSize = dictionaryRepository.getAll().size();
        List<WorldDtoRs> worldDtoRs = dictionaryRepository.getAll()
                .stream()
                .skip(startElement)
                .limit(pageSize)
                .map(this::convertToDtoRs)
                .toList();
        return new WorldDto<>(worldDtoRs.size(),
                totalSize,
                startElement,
                startElement + worldDtoRs.size() - 1,
                worldDtoRs);
    }

    private WorldDtoRs convertToDtoRs(World world) {
        return new WorldDtoRs(world.getEnglishWord(), world.getRussianDescription());
    }
}
