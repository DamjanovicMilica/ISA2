package com.bioskopPoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskopPoy.model.Theatres;

@Repository
public interface TheatresRepository extends JpaRepository<Theatres, Long> {

	Theatres findById(Long id);


}
