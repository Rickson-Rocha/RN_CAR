package com.br.rncar.project.repositories;

import com.br.rncar.project.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
