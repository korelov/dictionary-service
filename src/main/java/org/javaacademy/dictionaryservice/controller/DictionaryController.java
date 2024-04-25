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

    @GetMapping("/{name}")
    public World getWorldByName(@PathVariable String name){
        return dictionaryService.getByName(name);
    }

    @PostMapping
    public  ResponseEntity<World> addWorld(@RequestBody WorldDto dto) {
        return ResponseEntity.status(CREATED).body(dictionaryService.create(dto));
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateWorldDescription(@PathVariable String name,@RequestBody WorldDto dto){
        dictionaryService.updateWorldDescription(name, dto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteWorld(@PathVariable String name){
        boolean result = dictionaryService.deleteByName(name);
        return  result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();

    }

}
