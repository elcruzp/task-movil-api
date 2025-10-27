package com.trabajomovil.task_movil_api.category;

import jakarta.validation.constraints.NotBlank;

// DTO = Data Transfer Object (para recibir datos del body en el POST)
public class CategoryDTO {
    @NotBlank
    private String name;

    // Constructor vacío (Spring/Jackson lo necesita para deserializar JSON)
    public CategoryDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
