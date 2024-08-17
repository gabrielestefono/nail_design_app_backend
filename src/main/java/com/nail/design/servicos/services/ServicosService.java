package com.nail.design.servicos.services;

import java.util.List;

import com.nail.design.servicos.dto.CreateEditServicoDto;
import com.nail.design.servicos.model.Servico;

public interface ServicosService {

    List<Servico> listarServicos();

    Servico criarServico(CreateEditServicoDto createEditServicoDto);

    Servico verServico(Long id);

    Servico editarServico(CreateEditServicoDto createEditServicoDto, Long id);

    void deletarServico(Long id);
}
