package com.gestion.incidentes.repository;

import com.gestion.incidentes.model.AuditoriaTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditoriaTicketRepository extends JpaRepository<AuditoriaTicket, Long> {
    List<AuditoriaTicket> findByTicketId(Long ticketId);
}