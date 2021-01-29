package com.indra.selecaoJava.controller;

import com.indra.selecaoJava.model.dto.HistoricoPrecoCombustivelDTO;
import com.indra.selecaoJava.mapper.HistoricoPrecoCombustivelMapper;
import com.indra.selecaoJava.model.entidade.HistoricoPrecoCombustivel;
import com.indra.selecaoJava.service.HistoricoPrecoCombustivelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoPrecoCombustivelController {

    @Autowired
    private HistoricoPrecoCombustivelService historicoPrecoCombustivelService;

    @ApiOperation(value = "Cadastra um novo dado no histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Novo dado de histórico inserido"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
    ResponseEntity<?> inserirDado(@RequestBody HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoPrecoCombustivelService.insereDado(HistoricoPrecoCombustivelMapper.fromDTO(historicoPrecoCombustivelDTO)));
    }

    @ApiOperation(value = "Lista os dados do histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada lista de dados do histórico"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> listarDados(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.listaDados(page, size));
    }

    @ApiOperation(value = "Retorna um dado do histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado um dado do histórico"),
            @ApiResponse(code = 400, message = "A informação não foi encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?>  buscarDado(@PathVariable("id") Long id) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.buscaDado(id));
    }

    @ApiOperation(value = "Altera um dado do histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alterado um dado do histórico"),
            @ApiResponse(code = 400, message = "A informação não foi encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<?> atualizarDado(@PathVariable("id") Long id, @RequestBody HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.atualizaDado(HistoricoPrecoCombustivelMapper.fromDTO(historicoPrecoCombustivelDTO), id));
    }

    @ApiOperation(value = "Deleta um dado do histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deletado um dado do histórico"),
            @ApiResponse(code = 400, message = "A informação não foi encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    ResponseEntity<?> deletarDado(@PathVariable("id") Long id) {
        historicoPrecoCombustivelService.deletaDado(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Importa os dados de um csv para o histórico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Importado os dados do csv para o histórico"),
            @ApiResponse(code = 400, message = "Erro ao ler arquivo, favor verificar se o arquivo esta no formato correto"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value = "/csv", method = RequestMethod.POST)
    ResponseEntity<List<HistoricoPrecoCombustivel>> importarCSV(@RequestParam MultipartFile historicoCSV) throws IOException, SQLException {
        return ResponseEntity.ok(historicoPrecoCombustivelService.importaCSV(historicoCSV));
    }

    @ApiOperation(value = "Retorna a média do valor de venda com base em um municipio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada a média do valor de venda baseado em um municipio"),
            @ApiResponse(code = 400, message = "Erro ao calcular media, verifique se há dados no campo valordevenda"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/media-valor-venda/{municipio}/municipios", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> buscarMediaPrecoPorMunicipio(@PathVariable("municipio") String municipio) {
        return ResponseEntity.ok().body(historicoPrecoCombustivelService.buscaMediaPrecoPorMunicipio(municipio));
    }

    @ApiOperation(value = "Retorna todos os dados do histórico com base na sigla de uma região")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornados todos os dados do histórico com base na sigla de uma região"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/regioes/{regiaosigla}", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> listarPorRegiaoSigla(@PathVariable("regiaosigla") String regiaoSigla,
                                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.listaPorRegiaoSigla(regiaoSigla, page, size));
    }

    @ApiOperation(value = "Retorna todos os dados do histórico agrupados por bandeira")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornados todos os dados do histórico agrupados por bandeira"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/bandeiras", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> agruparPorBandeira(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.agrupaPorBandeira(page, size));
    }

    @ApiOperation(value = "Retorna todos os dados do histórico agrupados por data da coleta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornados todos os dados do histórico agrupados por data da coleta"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/data-da-coleta", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> agruparPorDataDaColeta(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.agrupaPorDataDaColeta(page, size));
    }

    @ApiOperation(value = "Retorna a média do valor de venda e de compra com base em um municipio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada a média do valor de venda baseado em um municipio"),
            @ApiResponse(code = 400, message = "Erro ao calcular media, verifique se há dados no campo valordevenda/valordecompra"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/media-valores/{municipio}/municipios", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> buscarMediaCompraVendaPorMunicipio(@PathVariable("municipio") String municipio) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.buscaMediaCompraVendaByMunicipio(municipio));
    }

    @ApiOperation(value = "Retorna a média do valor de venda e de compra com base em uma bandeira")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada a média do valor de venda baseado em uma bandeira"),
            @ApiResponse(code = 400, message = "Erro ao calcular media, verifique se há dados no campo valordevenda/valordecompra"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @RequestMapping(value="/media-valores/{bandeira}/bandeiras", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> buscarMediaCompraVendaPorBandeira(@PathVariable("bandeira") String bandeira) {
        return ResponseEntity.ok(historicoPrecoCombustivelService.buscaMediaCompraVendaBybandeira(bandeira));
    }
}
