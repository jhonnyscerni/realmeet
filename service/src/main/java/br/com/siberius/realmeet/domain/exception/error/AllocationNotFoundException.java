package br.com.siberius.realmeet.domain.exception.error;

import br.com.siberius.realmeet.domain.exception.EntidadeNaoEncontradaException;

public class AllocationNotFoundException extends EntidadeNaoEncontradaException {

    public AllocationNotFoundException(String message) {
        super(message);
    }

    public AllocationNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de allocation com código %d", id));
    }
}
