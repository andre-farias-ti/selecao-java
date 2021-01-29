package com.indra.selecaoJava.service;

import com.indra.selecaoJava.model.dto.ListaHistoricoDTO;
import com.indra.selecaoJava.model.entidade.HistoricoPrecoCombustivel;
import com.indra.selecaoJava.repository.HistoricoPrecoCombustivelRepository;
import com.indra.selecaoJava.mapper.HistoricoPrecoCombustivelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static com.indra.selecaoJava.exception.ExceptionDefault.checkThrow;
import static com.indra.selecaoJava.exception.ExceptionsMessagesEnum.*;

@Service
public class HistoricoPrecoCombustivelService {

    @Autowired
    HistoricoPrecoCombustivelRepository historicoPrecoCombustivelRepository;

    public HistoricoPrecoCombustivel insereDado(HistoricoPrecoCombustivel historicoPrecoCombustivel){
        return historicoPrecoCombustivelRepository.save(historicoPrecoCombustivel);
    }

    public Page<HistoricoPrecoCombustivel> listaDados(int page, int size){
        return historicoPrecoCombustivelRepository.findAll(PageRequest.of(page, size));
    }

    public HistoricoPrecoCombustivel buscaDado(Long id){
        Optional<HistoricoPrecoCombustivel> optionalHistoricoPrecoCombustivel = historicoPrecoCombustivelRepository.findById(id);
        checkThrow(!optionalHistoricoPrecoCombustivel.isPresent(), INFO_NAO_ENCONTRADA);

        HistoricoPrecoCombustivel historicoPrecoCombustivel = optionalHistoricoPrecoCombustivel.get();

        return historicoPrecoCombustivelRepository.save(historicoPrecoCombustivel);
    }

    public HistoricoPrecoCombustivel atualizaDado(HistoricoPrecoCombustivel novoDado, Long id){
        Optional<HistoricoPrecoCombustivel> optionalHistoricoPrecoCombustivel = historicoPrecoCombustivelRepository.findById(id);
        checkThrow(!optionalHistoricoPrecoCombustivel.isPresent(), INFO_NAO_ENCONTRADA);

        HistoricoPrecoCombustivel historicoPrecoCombustivel = optionalHistoricoPrecoCombustivel.get();

        historicoPrecoCombustivel.setRegiaoSigla(novoDado.getRegiaoSigla());
        historicoPrecoCombustivel.setEstadoSigla(novoDado.getEstadoSigla());
        historicoPrecoCombustivel.setMunicipio(novoDado.getMunicipio());
        historicoPrecoCombustivel.setRevenda(novoDado.getRevenda());
        historicoPrecoCombustivel.setCnpj(novoDado.getCnpj());
        historicoPrecoCombustivel.setProduto(novoDado.getProduto());
        historicoPrecoCombustivel.setDataDaColeta(novoDado.getDataDaColeta());
        historicoPrecoCombustivel.setValorDeCompra(novoDado.getValorDeCompra());
        historicoPrecoCombustivel.setValorDeVenda(novoDado.getValorDeVenda());
        historicoPrecoCombustivel.setUnidadeDeMedida(novoDado.getUnidadeDeMedida());
        historicoPrecoCombustivel.setBandeira(novoDado.getBandeira());

        return historicoPrecoCombustivelRepository.save(historicoPrecoCombustivel);
    }

    public void deletaDado(Long id) {
        Optional<HistoricoPrecoCombustivel> optionalHistoricoPrecoCombustivel = historicoPrecoCombustivelRepository.findById(id);
        checkThrow(!optionalHistoricoPrecoCombustivel.isPresent(), INFO_NAO_ENCONTRADA);
        historicoPrecoCombustivelRepository.deleteById(id);
    }

    public List<HistoricoPrecoCombustivel> importaCSV(MultipartFile historicoCSV) throws IOException {
        List<HistoricoPrecoCombustivel> historico = csvParaHistorico(historicoCSV);
        return  historicoPrecoCombustivelRepository.saveAll(historico);
    }

    private List<HistoricoPrecoCombustivel> csvParaHistorico(MultipartFile csv) throws IOException {
        BufferedReader br = null;
        try{
            InputStream is = csv.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
        }
        catch (IOException e){
            ERRO_LEITURA_ARQUIVO.raise();
        }
        checkThrow(br == null, ERRO_LEITURA_ARQUIVO);

        List<HistoricoPrecoCombustivel> historico = new ArrayList<>();
        String linha = br.readLine();
        while ((linha = br.readLine()) != null) {
            if(linha.contains("  ")){
                linha = linha.replaceAll(",", ".");
                linha = linha.replaceAll("  ", ",");
                linha = linha.replaceAll("\u0000", "");
            } else if(linha.contains("\t")){
                linha = linha.replaceAll(",", ".");
                linha = linha.replaceAll("\t", ",");
                linha = linha.replaceAll("\u0000", "");
            }
            String[] linhasplit = linha.split(",");
            if (linhasplit.length == 11) {
                historico.add(HistoricoPrecoCombustivelMapper.stringToHistorico(linha));
            }
        }
        return historico;
    }

    public Optional<Double> buscaMediaPrecoPorMunicipio(String municipio) {
        Optional<Double> media = historicoPrecoCombustivelRepository.findAvgValorDeVendaByMunicipio(municipio);
        checkThrow(!media.isPresent(), ERRO_BUSCAR_AVGVALORVENDA);
        return media;
    }

    public Page<HistoricoPrecoCombustivel> listaPorRegiaoSigla(String regiaoSigla, int page, int size) {
        return historicoPrecoCombustivelRepository.findAllByRegiaoSiglaContainingIgnoreCase(regiaoSigla, PageRequest.of(page, size));
    }

    public List<ListaHistoricoDTO> agrupaPorBandeira(int page, int size) {
        List<String> bandeiras = historicoPrecoCombustivelRepository.findDistinctBandeira();

        List<ListaHistoricoDTO> listaHistoricoDTOS = new ArrayList<>();
        for (String bandeira: bandeiras) {
            List<HistoricoPrecoCombustivel> historicoPrecoCombustivels  = historicoPrecoCombustivelRepository
                    .findAllByBandeira(bandeira, PageRequest.of(page, size));

            ListaHistoricoDTO listaHistoricoDTO = ListaHistoricoDTO.builder()
                    .chave(bandeira)
                    .historico(historicoPrecoCombustivels)
                    .build();
            listaHistoricoDTOS.add(listaHistoricoDTO);
        }
        return listaHistoricoDTOS;
    }

    public List<ListaHistoricoDTO> agrupaPorDataDaColeta(int page, int size) {

        List<Date> datas = historicoPrecoCombustivelRepository.findDistinctDataDaColeta();

        List<ListaHistoricoDTO> listaHistoricoDTOS = new ArrayList<>();
        for (Date data: datas) {
            List<HistoricoPrecoCombustivel> historicoPrecoCombustivels =
                    historicoPrecoCombustivelRepository.findAllByDataDaColeta(
                            data.toLocalDate(), PageRequest.of(page, size));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

            ListaHistoricoDTO listaHistoricoDTO = ListaHistoricoDTO.builder()
                    .chave(formato.format(calendar.getTime()))
                    .historico(historicoPrecoCombustivels)
                    .build();
            listaHistoricoDTOS.add(listaHistoricoDTO);
        }


        return listaHistoricoDTOS;
    }

    public Map<String, Optional<Double>> buscaMediaCompraVendaByMunicipio(String municipio) {
        Map<String, Optional<Double>> mapResponse = new HashMap<>();

        Optional<Double> mediaValorCompra = historicoPrecoCombustivelRepository.findAvgValorDeCompraByMunicipio(municipio);
        checkThrow(!mediaValorCompra.isPresent(), ERRO_BUSCAR_AVGVALORCOMPRA);

        Optional<Double> mediaValorVenda = historicoPrecoCombustivelRepository.findAvgValorDeVendaByMunicipio(municipio);
        checkThrow(!mediaValorVenda.isPresent(), ERRO_BUSCAR_AVGVALORVENDA);

        mapResponse.put("valorDeCompra", mediaValorCompra);
        mapResponse.put("valorDeVenda", mediaValorVenda);

        return mapResponse;
    }

    public Map<String, Optional<Double>> buscaMediaCompraVendaBybandeira(String bandeira) {
        Map<String, Optional<Double>> mapResponse = new HashMap<>();

        Optional<Double> mediaValorCompra = historicoPrecoCombustivelRepository.findAvgValorDeCompraByBandeira(bandeira);
        checkThrow(!mediaValorCompra.isPresent(), ERRO_BUSCAR_AVGVALORCOMPRA);

        Optional<Double> mediaValorVenda = historicoPrecoCombustivelRepository.findAvgValorDeVendaByBandeira(bandeira);
        checkThrow(!mediaValorVenda.isPresent(), ERRO_BUSCAR_AVGVALORVENDA);

        mapResponse.put("valorDeCompra", mediaValorCompra);
        mapResponse.put("valorDeVenda", mediaValorVenda);

        return mapResponse;
    }
}
