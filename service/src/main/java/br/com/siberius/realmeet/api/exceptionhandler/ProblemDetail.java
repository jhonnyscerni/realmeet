package br.com.siberius.realmeet.api.exceptionhandler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProblemDetail {

    @Schema(example = "preco")
    private String name;

    @Schema(example = "O preço é obrigatório")
    private String userMessage;
}
