package org.javaacademy.dictionaryservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PageDto<T> {
    @NonNull
    private Integer startPosition;
    @NonNull
    private Integer endElementIndex;
    @NonNull
    private Integer totalSize;
    @NonNull
    private Integer portionSize;
    @NonNull
    private T content;
}
