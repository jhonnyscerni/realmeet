package br.com.siberius.realmeet.domain.exception.error;

import br.com.siberius.realmeet.domain.exception.EntidadeNaoEncontradaException;

public class RoomNotFoundException extends EntidadeNaoEncontradaException {

    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de room com código %d", id));
    }

}
