package org.javaacademy.dictionaryservice.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.PageDto;
import org.javaacademy.dictionaryservice.dto.WordDto;
import org.javaacademy.dictionaryservice.exception.NotFoundException;
import org.javaacademy.dictionaryservice.service.WordService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/dictionary")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "getAll")
public class WordController {
    private final WordService wordService;

    @PostMapping
    public ResponseEntity<?> createWorld(@RequestBody WordDto wordDto) {
        wordService.create(wordDto);
        return accepted().build();
    }

    @GetMapping
    public PageDto<List<WordDto>> getWorlds(@RequestParam Integer startPosition,
                                            @RequestParam Integer portionSize) {
        return wordService.findAll(startPosition, portionSize);
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> getWordByKey(@PathVariable String key) {
        try {
            return ResponseEntity.ok(wordService.getWordByKey(key));
        } catch (NotFoundException e) {
            return notFound().build();
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<?> deleteByKey(@PathVariable String key) {
        if (!wordService.deleteByKey(key)) {
            return notFound().build();
        }
        return accepted().build();
    }
}

