package com.bmwapp.flat.repository;

import com.bmwapp.flat.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat, Integer> {

    @Query("FROM Flat f JOIN Address a on f.address.id = a.id WHERE " +
            "lower(f.flatName) LIKE CONCAT('%',:searchValue, '%') OR " +
            "lower(a.street) LIKE CONCAT('%',:searchValue, '%') OR " +
            "lower(a.city) LIKE CONCAT('%',:searchValue, '%') OR " +
            "lower(a.district) LIKE CONCAT('%',:searchValue, '%') OR " +
            "lower(a.postal_code) LIKE CONCAT('%',:searchValue, '%')")
    List<Flat> getSearchFlat(@Param("searchValue") String searchValue);
}
