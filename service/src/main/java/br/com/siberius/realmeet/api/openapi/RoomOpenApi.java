package br.com.siberius.realmeet.api.openapi;

import br.com.siberius.realmeet.api.exceptionhandler.Problem;
import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Room", description = "Endpoint que trata das rooms")
public interface RoomOpenApi {

    @Operation(description = "Listar as Rooms", summary = "Listar as Rooms")
    CompletableFuture<List<RoomDTO>> listar();

    @Operation(description = "Busca uma Room por ID", summary = "Busca uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Busca Realizada com Sucesso"),
        @ApiResponse(responseCode = "400", description = "ID da Room inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "404", description = "Room não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<RoomDTO> buscar(@PathVariable Long id);

    @Operation(description = "Cadastra uma Room", summary = "Cadastra uma Room")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Room cadastrado"),
    })
    CompletableFuture<RoomDTO> adicionar(
        @RequestBody @Valid InputRoomDTO inputRoomDTO);

    @Operation(description = "Atualiza uma Room por ID", summary = "Atualiza uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Room atualizado"),
        @ApiResponse(responseCode = "404", description = "Room não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<RoomDTO> atualizar(
        @PathVariable Long id,
        @RequestBody @Valid InputRoomDTO inputRoomDTO);

    @Operation(method = "Exclui uma Room por ID", summary = "Exclui uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Room excluído"),
        @ApiResponse(responseCode = "404", description = "Room não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<Void> excluir(@PathVariable Long id);

    @Operation(description = "Ativa uma Room por ID", summary = "Ativa uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Room ativado"),
        @ApiResponse(responseCode = "404", description = "Room não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<Void> ativar(
        @PathVariable Long id);

    @Operation(description = "Inativa uma Room por ID", summary = "Inativa uma Room por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Room desativado"),
        @ApiResponse(responseCode = "404", description = "Room não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CompletableFuture<Void> desativar(
        @PathVariable Long id);
}
