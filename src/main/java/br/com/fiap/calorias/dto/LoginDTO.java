package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "O e-mail do usuario e onrigatorio!")
        @Email(message = "O e-mail do usuario nao e valido!")
        String email,
        @NotBlank(message = "A senha do usuario e obrigatoria!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha

) {
}
