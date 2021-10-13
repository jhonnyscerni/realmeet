package br.com.siberius.realmeet.api.controller;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.api.openapi.RoomOpenApi;
import br.com.siberius.realmeet.domain.service.RoomService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/rooms")
public class RoomController implements RoomOpenApi {

    private final Executor controllersExecutor;
    private final RoomService roomService;

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<List<RoomDTO>> listar() {
        return CompletableFuture.supplyAsync(roomService::findAll);
    }

    @Override
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<RoomDTO> buscar(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() ->
            roomService.findByIdActive(id), controllersExecutor);
    }

    @Override
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<RoomDTO> adicionar(
        @RequestBody @Valid InputRoomDTO inputRoomDTO) {
        return CompletableFuture.supplyAsync(() ->
            roomService.create(inputRoomDTO), controllersExecutor);
    }

    @Override
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<RoomDTO> atualizar(
        @PathVariable Long id,
        @RequestBody @Valid InputRoomDTO inputRoomDTO) {
        return CompletableFuture.supplyAsync(() ->
            roomService.update(id, inputRoomDTO), controllersExecutor);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> excluir(@PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.excluir(id), controllersExecutor);
    }

    @Override
    @PutMapping(value = "/{id}/ativar", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> ativar(
        @Parameter(description = "ID de uma Room", example = "1", required = true)
        @PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.ativar(id), controllersExecutor);
    }

    @Override
    @DeleteMapping(value = "/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> desativar(
        @Parameter(description = "ID de uma Room", example = "1", required = true)
        @PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.desativar(id), controllersExecutor);
    }
}
