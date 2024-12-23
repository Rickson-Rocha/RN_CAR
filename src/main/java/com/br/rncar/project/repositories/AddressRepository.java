package com.br.rncar.project.repositories;

import com.br.rncar.project.domain.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository <Address, Long>   {
}
