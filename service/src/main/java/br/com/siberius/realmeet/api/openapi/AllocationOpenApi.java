package br.com.siberius.realmeet.api.openapi;

import br.com.siberius.realmeet.api.exceptionhandler.Problem;
import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.api.model.filter.AllocationFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Allocation", description = "Endpoint que trata das allocation")
public interface AllocationOpenApi {

    @Operation(description = "Pesquisar Allocations", summary = "Pesquisar Allocations")
    Page<AllocationDTO> pesquisar(@ParameterObject AllocationFilter filter, @ParameterObject Pageable pageable);

    @Operation(description = "Listar as Allocation", summary = "Listar as Allocation" )
    CompletableFuture<List<AllocationDTO>> listar();

    @Operation(description = "Busca uma Allocation por ID", summary = "Busca uma Allocation por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Busca Realizada com Sucesso"),
        @ApiResponse(responseCode = "400", description = "ID da Allocation inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description = "Allocation não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<AllocationDTO> buscar(@PathVariable Long id);

    @Operation(description = "Cadastra uma Allocation", summary = "Cadastra uma Allocation")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Allocation cadastrado"),
    })
    CompletableFuture<AllocationDTO> adicionar(@RequestBody @Valid InputAllocationDTO inputAllocationDTO);

    @Operation(description = "Atualiza uma Allocation por ID", summary = "Atualiza uma Allocation por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Allocation atualizado"),
        @ApiResponse(responseCode = "404", description = "Allocation não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<AllocationDTO> atualizar(@PathVariable Long id,
        @RequestBody @Valid InputAllocationDTO inputAllocationDTO);

    @Operation(method = "Exclui uma Room por ID", summary = "Exclui uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Room excluído"),
        @ApiResponse(responseCode = "404", description = "Room não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<Void> excluir(@PathVariable Long id);

}
