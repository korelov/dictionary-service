package org.javaacademy.dictionaryservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class WorldDto<T> {
    @NonNull
    private Integer size;
    @NonNull
    private Integer totalSize;
    @NonNull
    private Integer startElementIndex;
    @NonNull
    private Integer endElementIndex;
    @NonNull
    private T content;
}
