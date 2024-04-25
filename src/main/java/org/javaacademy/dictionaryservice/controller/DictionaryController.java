package org.javaacademy.dictionaryservice.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.WorldDto;
import org.javaacademy.dictionaryservice.dto.WorldDtoRq;
import org.javaacademy.dictionaryservice.dto.WorldDtoRs;
import org.javaacademy.dictionaryservice.entity.World;
import org.javaacademy.dictionaryservice.service.DictionaryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "getWorlds")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping
    public List<WorldDtoRs> getAllWorlds() {
        return dictionaryService.getAll();
    }

    @GetMapping("/{engWorld}")
    public World getWorldByEnglishWorld(@PathVariable String engWorld) {
        return dictionaryService.getByEnglishWorld(engWorld);
    }

    @PostMapping
    @CacheEvict(cacheNames = "findLimit", allEntries = true)
    public ResponseEntity<WorldDtoRs> createWorld(@RequestBody WorldDtoRq dto) {
        return ResponseEntity.status(CREATED).body(dictionaryService.create(dto));
    }

    @PutMapping("/{engWorld}")
    public ResponseEntity<?> updateWorldRussianTranslation(@PathVariable String engWorld,
                                                           @RequestBody WorldDtoRq dto) {
        dictionaryService.updateWorldRussianTranslation(engWorld, dto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @DeleteMapping("/{engWorld}")
    public ResponseEntity<?> deleteWorld(@PathVariable String engWorld) {
        boolean result = dictionaryService.deleteByEnglishWorld(engWorld);
        return result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/world")
    @Cacheable(cacheNames = "getWorlds")
    @CachePut(cacheNames = "getWorlds", condition = "#refresh==true")
    public WorldDto<List<WorldDtoRs>> getWorlds(@RequestParam Integer startElement,
                                                @RequestParam Integer pageSize,
                                                @RequestParam(required = false) boolean refresh) {
        return dictionaryService.getWorlds(startElement, pageSize);
    }
}
