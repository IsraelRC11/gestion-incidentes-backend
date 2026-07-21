package com.gestion.incidentes.repository;

import com.gestion.incidentes.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}