package com.iroman.pharmasales.shared.enums;

import lombok.Getter;

@Getter
public enum State {
    ACTIVE("A"),
    DESACTIVE("E");

    private final String value;
    State(String value){
        this.value = value;
    }
}
