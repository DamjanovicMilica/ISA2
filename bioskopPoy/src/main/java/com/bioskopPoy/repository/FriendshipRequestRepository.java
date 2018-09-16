package com.bioskopPoy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bioskopPoy.model.FriendshipRequest;
import com.bioskopPoy.model.Guest;


public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

	List<FriendshipRequest> findByFromGuest(Guest from);
	FriendshipRequest findById(Long id);
	List<FriendshipRequest> findByToGuest(Guest to);

}