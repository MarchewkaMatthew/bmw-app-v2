package com.bmwapp.flat.repository;

import com.bmwapp.flat.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
