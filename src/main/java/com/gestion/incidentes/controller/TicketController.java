package com.gestion.incidentes.controller;

import com.gestion.incidentes.exception.ResourceNotFoundException;
import com.gestion.incidentes.model.Ticket;
import com.gestion.incidentes.service.TicketService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET: Listar todos los tickets -> http://localhost:8080/api/tickets
    @GetMapping
    public List<Ticket> listarTodos() {
        return ticketService.obtenerTodos();
    }

    // GET: Obtener ticket por ID -> http://localhost:8080/api/tickets/1
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorId(@PathVariable Long id) {
        Ticket ticket = ticketService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con el ID: " + id));
        return ResponseEntity.ok(ticket);
    }

    // POST: Crear un nuevo ticket
    @PostMapping
    public Ticket crearTicket(@Valid @RequestBody Ticket ticket) {
        return ticketService.guardarTicket(ticket);
    }

    // PUT: Cambiar estado -> http://localhost:8080/api/tickets/1/estado?nuevoEstado=EN_PROCESO&usuarioAccionId=2
    @PutMapping("/{id}/estado")
    public ResponseEntity<Ticket> actualizarEstado(
            @PathVariable Long id, 
            @RequestParam String nuevoEstado,
            @RequestParam Long usuarioAccionId) {
        Ticket ticketActualizado = ticketService.cambiarEstado(id, nuevoEstado, usuarioAccionId);
        return ResponseEntity.ok(ticketActualizado);
    }
}