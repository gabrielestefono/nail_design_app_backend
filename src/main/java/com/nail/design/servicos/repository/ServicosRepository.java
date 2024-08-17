package com.nail.design.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nail.design.servicos.model.Servico;

public interface ServicosRepository extends JpaRepository<Servico, Long> {
}
