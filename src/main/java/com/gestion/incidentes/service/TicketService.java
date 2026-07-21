package com.gestion.incidentes.service;

import com.gestion.incidentes.model.AuditoriaTicket;
import com.gestion.incidentes.model.Ticket;
import com.gestion.incidentes.model.Usuario;
import com.gestion.incidentes.repository.AuditoriaTicketRepository;
import com.gestion.incidentes.repository.TicketRepository;
import com.gestion.incidentes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AuditoriaTicketRepository auditoriaTicketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, 
                         AuditoriaTicketRepository auditoriaTicketRepository,
                         UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.auditoriaTicketRepository = auditoriaTicketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Ticket> obtenerTodos() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> obtenerPorId(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket guardarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerPorEstado(String estado) {
        return ticketRepository.findByEstado(estado);
    }

    // Cambiar estado e insertar historial en Auditoría
    public Ticket cambiarEstado(Long ticketId, String nuevoEstado, Long usuarioAccionId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new com.gestion.incidentes.exception.ResourceNotFoundException("Ticket no encontrado con el ID: " + ticketId));

        Usuario usuarioAccion = usuarioRepository.findById(usuarioAccionId)
                .orElseThrow(() -> new com.gestion.incidentes.exception.ResourceNotFoundException("Usuario no encontrado con el ID: " + usuarioAccionId));

        String estadoAnterior = ticket.getEstado();
        ticket.setEstado(nuevoEstado);
        Ticket ticketGuardado = ticketRepository.save(ticket);

        AuditoriaTicket auditoria = new AuditoriaTicket();
        auditoria.setTicket(ticketGuardado);
        auditoria.setUsuarioAccion(usuarioAccion);
        auditoria.setAccion("CAMBIO_ESTADO");
        auditoria.setDetalles("Estado cambiado de " + estadoAnterior + " a " + nuevoEstado);
        
        auditoriaTicketRepository.save(auditoria);

        return ticketGuardado;
    }
}