package com.joilson.gof.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    @Size(min = 1, max = 250, message = "O nome não pode conter mais do que 250 caracteres.")
    @NotNull
    private String name;
    @Size(min = 8, max = 8, message = "O cep deve conter apenas 8 caracteres.")
    @NotNull
    private String cep;
}
