package ru.gb.restgb.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    ACTIVE("Доступно"), DISABLE("Недоступно");

    private final String title;
}
