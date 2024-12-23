package com.br.rncar.project.repositories;

import com.br.rncar.project.domain.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
