package com.bmwapp.flat;

import com.bmwapp.flat.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Integer> {
}
