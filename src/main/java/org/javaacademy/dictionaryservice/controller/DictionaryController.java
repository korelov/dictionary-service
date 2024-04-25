package org.javaacademy.dictionaryservice.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.WorldDto;
import org.javaacademy.dictionaryservice.dto.WorldDtoRq;
import org.javaacademy.dictionaryservice.dto.WorldDtoRs;
import org.javaacademy.dictionaryservice.entity.World;
import org.javaacademy.dictionaryservice.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
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
    public WorldDto<List<WorldDtoRs>> getWorlds(@RequestParam Integer startElement,
                                                @RequestParam Integer pageSize) {
        return dictionaryService.getWorlds(startElement, pageSize);
    }
}
