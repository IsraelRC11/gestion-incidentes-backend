package com.gestion.incidentes.repository;

import com.gestion.incidentes.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEstado(String estado);
    List<Ticket> findByUsuarioId(Long usuarioId);
}