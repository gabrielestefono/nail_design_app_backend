package com.nail.design.servicos.controller;

import java.util.List;

import com.nail.design.servicos.dto.CreateEditServicoDto;
import com.nail.design.servicos.model.Servico;

public interface ServicosController {

    List<Servico> listarServicos();

    Servico criarServico(CreateEditServicoDto body);

    Servico verServico(Long id);

    Servico editarServico(CreateEditServicoDto body, Long id);

    void deletarServico(Long id);
}
