package com.bioskopPoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskopPoy.model.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {


}
