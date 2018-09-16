package com.bioskopPoy.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.bioskopPoy.model.Cinema;
import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.reservation.ReservationCinema;


@Repository
public interface ReservationCinemaRepository extends JpaRepository<ReservationCinema, Long> {

	List<ReservationCinema> findByGuest(Guest guest);
	List<ReservationCinema> findById(Long id);
	List<ReservationCinema> findByCinema(Cinema cinema);
	List<ReservationCinema> findByCinema_Id(long id);
	List<ReservationCinema> findByTerminDo(Date termindo);
	List<ReservationCinema> findByTerminOd(Date terminod);
	Long countByGuest(Guest guest);
}
