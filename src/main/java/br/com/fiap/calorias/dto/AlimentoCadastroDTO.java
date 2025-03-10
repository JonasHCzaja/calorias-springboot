package br.com.fiap.calorias.dto;

public record AlimentoCadastroDTO(
        Long alimentoId,
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGorduras
) {
}
