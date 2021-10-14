package br.com.siberius.realmeet.api.controller;

import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.api.model.filter.AllocationFilter;
import br.com.siberius.realmeet.api.openapi.AllocationOpenApi;
import br.com.siberius.realmeet.domain.service.AllocationService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value = "/allocation")
public class AllocationController implements AllocationOpenApi {

    private final Executor controllersExecutor;
    private final AllocationService allocationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<AllocationDTO> pesquisar(AllocationFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        return allocationService.buscarAllocation(filter, pageable);
    }

    @GetMapping(value = "listar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<List<AllocationDTO>> listar() {
        return CompletableFuture.supplyAsync(allocationService::buscarTodos, controllersExecutor);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<AllocationDTO> buscar(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> allocationService.buscarPorIdDTO(id), controllersExecutor);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<AllocationDTO> adicionar(@RequestBody @Valid InputAllocationDTO inputAllocationDTO) {
        return CompletableFuture.supplyAsync(
            () -> allocationService.create(inputAllocationDTO), controllersExecutor);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<AllocationDTO> atualizar(@PathVariable Long id,
        @RequestBody @Valid InputAllocationDTO inputAllocationDTO) {
        return CompletableFuture.supplyAsync(() ->
            allocationService.update(id, inputAllocationDTO), controllersExecutor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<Void> excluir(@PathVariable Long id) {
        return CompletableFuture.runAsync(() -> allocationService.excluir(id), controllersExecutor);
    }

}
