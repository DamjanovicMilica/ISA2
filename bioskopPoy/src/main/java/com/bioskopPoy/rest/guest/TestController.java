package com.bioskopPoy.rest.guest;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bioskopPoy.model.Cinema;
import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.Theatres;
import com.bioskopPoy.model.UserRoles;
import com.bioskopPoy.model.reservation.ReservationCinema;
import com.bioskopPoy.model.reservation.ReservationTheatre;
import com.bioskopPoy.repository.CinemaRepository;
import com.bioskopPoy.repository.ReservationCinemaRepository;
import com.bioskopPoy.repository.ReservationTheatreRepository;
import com.bioskopPoy.repository.TheatresRepository;
import com.bioskopPoy.repository.guest.GuestRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private GuestRepository guestRepository;
		
	@Autowired
	private CinemaRepository cinemaRepository; 
	
	@Autowired
	private TheatresRepository theatresRepository; 
	
	@Autowired
	private ReservationCinemaRepository reservationCinemaRepository; 
	
	@Autowired
	private ReservationTheatreRepository reservationTheatreRepository; 
	
	@RequestMapping(method = RequestMethod.GET, value = "/fill-database")
	public ResponseEntity<?> getProfilePageInfo() {
		
		Cinema c1 = new Cinema(); 
		c1.setDistance("1.333");
		c1.setName("Bioskop1");
		c1.setTown("Grad1");
		c1 = cinemaRepository.save(c1); 
		
		Cinema c2 = new Cinema(); 
		c2.setDistance("3.234");
		c2.setName("Bioskop2");
		c2.setTown("Grad2");
		c2 = cinemaRepository.save(c2); 
		
		Cinema c3 = new Cinema(); 
		c3.setDistance("5.500");
		c3.setName("Bioskop3");
		c3.setTown("Grad3");
		c3 = cinemaRepository.save(c3);
		
		Theatres t1 = new Theatres(); 
		t1.setDistance("7.321");
		t1.setName("Pozoriste1");
		t1.setTown("Grad1");
		t1 = theatresRepository.save(t1); 
		
		Theatres t2 = new Theatres(); 
		t2.setDistance("2.000");
		t2.setName("Pozoriste2");
		t2.setTown("Grad2");
		t2 = theatresRepository.save(t2); 
		
		Theatres t3 = new Theatres(); 
		t3.setDistance("1.199");
		t3.setName("Pozoriste3");
		t3.setTown("Grad3");
		t3 = theatresRepository.save(t3); 
		
		Guest g1 = new Guest(); 
		g1.setActive(true);
		g1.setAddress("Adresa");
		g1.setEmail("g1@g1.com");
		g1.setName("Gost1");
		g1.setPassword("Password1");
		g1.setRole(UserRoles.GUEST);
		g1.setSurname("Gost1");
		g1 = guestRepository.save(g1); 
		
		Guest g2 = new Guest(); 
		g2.setActive(true);
		g2.setAddress("Adresa");
		g2.setEmail("g2@g2.com");
		g2.setName("Gost2");
		g2.setPassword("Password2");
		g2.setRole(UserRoles.GUEST);
		g2.setSurname("Gost2");
		g2 = guestRepository.save(g2); 
		
		Guest g3 = new Guest(); 
		g3.setActive(true);
		g3.setAddress("Adresa");
		g3.setEmail("g3@g3.com");
		g3.setName("Gost3");
		g3.setPassword("Password3");
		g3.setRole(UserRoles.GUEST);
		g3.setSurname("Gost3");
		g3 = guestRepository.save(g3); 
		
		Guest g4 = new Guest(); 
		g4.setActive(true);
		g4.setAddress("Adresa");
		g4.setEmail("g4@g4.com");
		g4.setName("Gost4");
		g4.setPassword("Password4");
		g4.setRole(UserRoles.GUEST);
		g4.setSurname("Gost4");
		g4 = guestRepository.save(g4); 
		
		ReservationCinema rc = new ReservationCinema(); 
		rc.setCinema(c1);
		rc.setGuest(g1);
		rc.setTerminDo(new Date());
		rc.setTerminOd(new Date());
		rc = reservationCinemaRepository.save(rc); 
		
		g1.getReservationsCinema().add(rc); 
		g1 = guestRepository.save(g1); 
		
		ReservationCinema rc1 = new ReservationCinema(); 
		rc1.setCinema(c2);
		rc1.setGuest(g1);
		rc1.setTerminDo(new Date());
		rc1.setTerminOd(new Date());
		rc1 = reservationCinemaRepository.save(rc1); 
		
		g1.getReservationsCinema().add(rc1); 
		g1 = guestRepository.save(g1); 
		
		ReservationTheatre rt = new ReservationTheatre(); 
		rt.setGuest(g1);
		rt.setTerminDo(new Date());
		rt.setTerminOd(new Date());
		rt.setTheatres(t1);
		rt = reservationTheatreRepository.save(rt); 
		
		g1.getReservationsTheatres().add(rt); 
		g1 = guestRepository.save(g1); 		
		
		ReservationTheatre rt1 = new ReservationTheatre(); 
		rt1.setGuest(g1);
		rt1.setTerminDo(new Date());
		rt1.setTerminOd(new Date());
		rt1.setTheatres(t2);
		rt1 = reservationTheatreRepository.save(rt1); 
		
		g1.getReservationsTheatres().add(rt1); 
		g1 = guestRepository.save(g1); 
		
		System.out.println("DATABASE FILLED");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
