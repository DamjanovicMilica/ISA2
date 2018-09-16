package com.bioskopPoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bioskopPoy.model.User;
import com.bioskopPoy.repository.UserRepository;

/**
 * 
 * General operations that can be performed on any {@link User}.
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
/*	
	
	public void setVariableAttributes(User u, UserDTO uDTO) {
		u.setName(uDTO.getName());
		u.setSurname(uDTO.getSurname());
		u.setAddress(uDTO.getAddress());
	}
	
	
	public void copyData(User u, UserDTO uDTO) {
		u.setEmail(uDTO.getEmail());
		u.setPassword(passwordEncoder.encode(uDTO.getPassword()));
		setVariableAttributes(u, uDTO);
	}
	*/
}
