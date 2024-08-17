package com.nail.design.servicos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nail.design.servicos.dto.CreateEditServicoDto;
import com.nail.design.servicos.model.Servico;
import com.nail.design.servicos.services.ServicosServiceImpl;

@RestController
@RequestMapping(path = "/servicos")
public class ServicosControllerImpl implements ServicosController {

    private final ServicosServiceImpl servicosService;

    public ServicosControllerImpl(ServicosServiceImpl servicosService) {
        this.servicosService = servicosService;
    }

    @Override
    @GetMapping(path = "", produces = "application/json")
    public List<Servico> listarServicos() {
        return this.servicosService.listarServicos();
    }

    @Override
    @PostMapping(path = "", produces = "application/json")
    public Servico criarServico(@RequestBody CreateEditServicoDto body) {
        return this.servicosService.criarServico(body);
    }

    @Override
    @GetMapping(path = "/{id}", produces = "application/json")
    public Servico verServico(@PathVariable Long id) {
        return this.servicosService.verServico(id);
    }

    @Override
    @PutMapping(path = "/{id}", produces = "application/json")
    public Servico editarServico(@RequestBody CreateEditServicoDto body, @PathVariable Long id) {
        return this.servicosService.editarServico(body, id);
    }

    @Override
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deletarServico(@PathVariable Long id) {
        this.servicosService.deletarServico(id);
    }
}
