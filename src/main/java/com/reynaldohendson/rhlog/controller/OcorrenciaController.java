package com.reynaldohendson.rhlog.controller;

import com.reynaldohendson.rhlog.dto.OcorrenciaModel;
import com.reynaldohendson.rhlog.mapper.OcorrenciaMapper;
import com.reynaldohendson.rhlog.model.Entrega;
import com.reynaldohendson.rhlog.model.Ocorrencia;
import com.reynaldohendson.rhlog.model.input.OcorrenciaInput;
import com.reynaldohendson.rhlog.service.BuscaEntregaService;
import com.reynaldohendson.rhlog.service.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/rhlog/entrega/{entregaId}/ocorrencias")
public class OcorrenciaController {
    private OcorrenciaService ocorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;
    private BuscaEntregaService buscaEntregaService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
                                     @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
       Ocorrencia ocorrenciaRegistrada = ocorrenciaService
               .registrar(entregaId, ocorrenciaInput.getDescricao());
       return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
    }
    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return ocorrenciaMapper.toCollectionModel(entrega.getOcorrencias());
    }

}
