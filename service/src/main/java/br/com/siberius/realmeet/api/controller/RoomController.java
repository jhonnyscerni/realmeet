package br.com.siberius.realmeet.api.controller;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.service.RoomService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class RoomController {

    private final Executor controllersExecutor;
    private final RoomService roomService;


    @GetMapping
    public CompletableFuture<List<RoomDTO>> listar() {
        return CompletableFuture.supplyAsync(roomService::findAll);
    }

    @GetMapping("/{id}")
    public CompletableFuture<RoomDTO> buscar(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() ->
            roomService.findByIdActive(id), controllersExecutor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<RoomDTO> adicionar(@RequestBody @Valid InputRoomDTO inputRoomDTO) {
        return CompletableFuture.supplyAsync(() ->
            roomService.create(inputRoomDTO), controllersExecutor);
    }

    @PutMapping("/{id}")
    public CompletableFuture<RoomDTO> atualizar(@PathVariable Long id, @RequestBody @Valid InputRoomDTO inputRoomDTO) {
        return CompletableFuture.supplyAsync(() ->
            roomService.update(id, inputRoomDTO), controllersExecutor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> excluir(@PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.excluir(id), controllersExecutor);
    }

    @PutMapping("/{id}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> ativar(@PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.ativar(id), controllersExecutor);
    }


    @DeleteMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> desativar(@PathVariable Long id) {
        return CompletableFuture.runAsync(() ->
            roomService.desativar(id), controllersExecutor);
    }


}
