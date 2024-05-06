package org.javaacademy.dictionaryservice.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Word {
    @NonNull
    private String englishWord;
    @NonNull
    private String russianDescription;
}
