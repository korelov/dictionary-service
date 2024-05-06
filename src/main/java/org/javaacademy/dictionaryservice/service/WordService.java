package org.javaacademy.dictionaryservice.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryservice.dto.PageDto;
import org.javaacademy.dictionaryservice.dto.WordDto;
import org.javaacademy.dictionaryservice.entity.Word;
import org.javaacademy.dictionaryservice.exception.NotFoundException;
import org.javaacademy.dictionaryservice.repository.WordRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;

    /**
     * Создание
     * @param wordDto
     */
    @CacheEvict(cacheNames = "worlds",allEntries = true)
    public void create(WordDto wordDto) {
        Word word = new Word(wordDto.getEnglishWord(), wordDto.getRussianDescription());
        wordRepository.add(word);
    }

    /**
     * Пагинация
     */
    @Cacheable(value = "worlds")
    public PageDto<List<WordDto>> findAll(int startIndex, int portionSize) {
        List<Word> words = wordRepository.findAll(startIndex, portionSize);
        List<WordDto> wordDtos = words.stream().map(this::convert).toList();
        return new PageDto<>(
                startIndex,
                startIndex + portionSize - 1,
                wordRepository.getCountEntities(),
                portionSize,
                wordDtos
        );
    }

    @Cacheable(cacheNames = "wordByKey")
    public WordDto getWordByKey(String key) {
        return wordRepository.getWord(key)
                .map(this::convert)
                .orElseThrow(() -> new NotFoundException("Нет такого слова"));
    }

    @CacheEvict(cacheNames = {"wordByKey", "words"}, allEntries = true)
    public boolean deleteByKey(String key) {
        return wordRepository.deleteByKey(key);
    }

    @CacheEvict(cacheNames = {"wordByKey", "words"}, allEntries = true)
    public void clearCache() {
    }

    private WordDto convert(Word word) {
        return new WordDto(word.getEnglishWord(), word.getRussianDescription());
    }
}
