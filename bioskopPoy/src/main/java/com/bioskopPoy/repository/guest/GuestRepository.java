package com.bioskopPoy.repository.guest;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.UserRoles;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	
	List<Guest> findByAddress(String address);
	Guest findByEmail(String email);
	List<Guest> findByName(String name);
	Guest findByEmailAndPassword(String username, String password);
	List<Guest> findByPassword(String password);

	List<Guest> findByRole(UserRoles role);
	List<Guest> findBySurname(String surname);
	
	
}
