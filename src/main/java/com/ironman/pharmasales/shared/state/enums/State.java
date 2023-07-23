package com.ironman.pharmasales.shared.state.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironman.pharmasales.shared.state.serializer.StateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(type = "object", oneOf = {State.class})
@JsonSerialize(using = StateSerializer.class)
@Getter
public enum State {
    ACTIVE("A", true) {
        @Override
        public String toString() {
            return "Activo";
        }
    },
    DISABLE("E", false) {
        @Override
        public String toString() {
            return "Eliminado";
        }
    };

    private final String value;
    private final boolean active;
    private final String name;

    State(String value, boolean active) {
        this.value = value;
        this.active = active;
        this.name = this.toString();
    }
}
