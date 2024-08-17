package com.nail.design.servicos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nail.design.servicos.dto.CreateEditServicoDto;
import com.nail.design.servicos.model.Servico;
import com.nail.design.servicos.repository.ServicosRepository;

@Service
public class ServicosServiceImpl implements ServicosService {

    private final ServicosRepository servicosRepository;

    public ServicosServiceImpl(ServicosRepository servicosRepository) {
        this.servicosRepository = servicosRepository;
    }

    @Override
    public List<Servico> listarServicos() {
        return this.servicosRepository.findAll();
    }

    @Override
    public Servico criarServico(CreateEditServicoDto createEditServicoDto) {
        Servico servico = new Servico();
        servico.setNome(createEditServicoDto.nome);
        servico.setImagem(createEditServicoDto.imagem);
        return this.servicosRepository.save(servico);
    }

    @Override
    public Servico verServico(Long id) {
        return this.servicosRepository.findById(id).orElseThrow();
    }

    @Override
    public Servico editarServico(CreateEditServicoDto createEditServicoDto, Long id) {
        Servico servico = verServico(id);
        servico.setImagem(createEditServicoDto.imagem);
        servico.setNome(createEditServicoDto.nome);
        return this.servicosRepository.save(servico);
    }

    @Override
    public void deletarServico(Long id) {
        Servico servico = verServico(id);
        this.servicosRepository.delete(servico);
    }
}
