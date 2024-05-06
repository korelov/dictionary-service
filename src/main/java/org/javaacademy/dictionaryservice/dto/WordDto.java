package org.javaacademy.dictionaryservice.dto;

import lombok.Value;

@Value
public class WordDto {
    private String englishWord;
    private String russianDescription;
}
