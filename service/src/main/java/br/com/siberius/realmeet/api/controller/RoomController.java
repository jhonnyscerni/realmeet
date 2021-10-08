package br.com.siberius.realmeet.api.controller;

import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.service.RoomService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/rooms")
public class RoomController {

    private final Executor controllersExecutor;
    private final RoomService roomService;

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<RoomDTO>> buscar(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() ->
            roomService.findById(id), controllersExecutor).thenApply(ResponseEntity::ok);
    }


}
