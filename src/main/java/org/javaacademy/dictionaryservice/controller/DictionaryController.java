package org.javaacademy.dictionaryservice.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.WorldDto;
import org.javaacademy.dictionaryservice.entity.World;
import org.javaacademy.dictionaryservice.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeSet;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping
    public TreeSet<World> getAllWorlds() {
        return dictionaryService.getAll();
    }

    @GetMapping("/{engWorld}")
    public World getWorldByEnglishWorld(@PathVariable String engWorld){
        return dictionaryService.getByEnglishWorld(engWorld);
    }

    @PostMapping
    public  ResponseEntity<World> addWorld(@RequestBody WorldDto dto) {
        return ResponseEntity.status(CREATED).body(dictionaryService.create(dto));
    }

    @PutMapping("/{engWorld}")
    public ResponseEntity<?> updateWorldRussianTranslation(@PathVariable String engWorld,
                                                           @RequestBody WorldDto dto){
        dictionaryService.updateWorldRussianTranslation(engWorld, dto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @DeleteMapping("/{engWorld}")
    public ResponseEntity<?> deleteWorld(@PathVariable String engWorld){
        boolean result = dictionaryService.deleteByEnglishWorld(engWorld);
        return  result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }
}
