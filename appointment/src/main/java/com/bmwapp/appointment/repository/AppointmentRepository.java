package com.bmwapp.appointment.repository;

import com.bmwapp.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> getAllByCustomerId(String customerId);

    void deleteByIdAndCustomerId(Integer id, String customerId);
    Optional<Appointment> findOneByIdAndCustomerId(Integer id, String customerId);

}
