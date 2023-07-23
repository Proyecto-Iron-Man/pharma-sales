package com.ironman.pharmasales.shared.enums;

import lombok.Getter;

@Getter
public enum State {
    ACTIVE("A"),
    DISABLE("E");

    private final String value;
    State(String value) {
        this.value = value;
    }
}
