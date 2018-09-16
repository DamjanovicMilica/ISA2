package com.bioskopPoy.rest.guest;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.User;
import com.bioskopPoy.model.UserRoles;
import com.bioskopPoy.rest.guest.dto.AcceptPeopleDTO;
import com.bioskopPoy.rest.guest.dto.AddPeopleDTO;
import com.bioskopPoy.rest.guest.dto.CinemasPageDTO;
import com.bioskopPoy.rest.guest.dto.FriendsPageDTO;
import com.bioskopPoy.rest.guest.dto.HomePageCinemasDTO;
import com.bioskopPoy.rest.guest.dto.HomePageTheatresDTO;
import com.bioskopPoy.rest.guest.dto.LoginDTO;
import com.bioskopPoy.rest.guest.dto.LoginResponseDTO;
import com.bioskopPoy.rest.guest.dto.ProfilePageDTO;
import com.bioskopPoy.rest.guest.dto.Reserve1CinemaPageDTO;
import com.bioskopPoy.rest.guest.dto.Reserve1TheatrePageDTO;
import com.bioskopPoy.rest.guest.dto.TheatresPageDTO;
import com.bioskopPoy.rest.guest.dto.UpdateProfileDTO;
import com.bioskopPoy.service.UserService;
import com.bioskopPoy.service.guest.GuestService;


@RequestMapping("/rest/guest")
@RestController
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/register-guest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		Guest guest = guestService.registerGuest(user);
		if (guest != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/confirm-registration/{user-id}", method = RequestMethod.GET)
	public ResponseEntity<?> confirmGuestRegistration(@PathVariable(value = "user-id") long id) {

		boolean isValid = guestService.verifyGuest(id);

		if (isValid) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO dto, Device device) throws AuthenticationException {
    	
       Guest guest = guestService.login(dto); 
        // Return the token
       if(guest == null) {
    	   return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
       }
        return ResponseEntity.ok(new LoginResponseDTO(guest.getId(), UserRoles.GUEST));
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-home-page-info-cinemas/{guest-id}")
	public List<HomePageCinemasDTO> getHomePageInfoCinemas(@PathVariable(value = "guest-id") Long id) {
		return guestService.getHomePageInfoCinemas(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-home-page-info-theatres/{guest-id}")
	public List<HomePageTheatresDTO> getHomePageInfoTheatres(@PathVariable(value = "guest-id") Long id) {
		return guestService.getHomePageInfoTheatres(id);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/get-theatres-page-info/{guest-id}")
	public List<TheatresPageDTO> getTheatresPageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getTheatresPageInfo(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-cinemas-page-info/{guest-id}")
	public List<CinemasPageDTO> getCinemasPageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getCinemasPageInfo(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-friends-page-info/{guest-id}")
	public List<FriendsPageDTO> getFriendsPageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getFriendsPageInfo(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/add-people/{guest-id}")
	public ResponseEntity<?> addPeople(@PathVariable(value = "guest-id") Long id) {
		List<AddPeopleDTO> add = guestService.addPeople(id);

		return new ResponseEntity<>(add, HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/accept-people/{guest-id}")
	public ResponseEntity<?> acceptPeople(@PathVariable(value = "guest-id") Long id) {
		List<AcceptPeopleDTO> requests = guestService.acceptPeople(id);

		return new ResponseEntity<>(requests, HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/send-friendship-request/{friend-id}/{guest-id}")
	public ResponseEntity<?> sendFriendshipRequest(@PathVariable(value = "friend-id") Long friendId, @PathVariable(value = "guest-id") Long guestId) {
		guestService.sendFriendshipRequest(friendId, guestId);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/accept-friendship-request/{friend-id}/{guest-id}")
	public ResponseEntity<?> acceptFriendshipRequest(@PathVariable(value = "friend-id") Long friendId, @PathVariable(value = "guest-id") Long guestId) {
		guestService.acceptFriendshipRequest(friendId, guestId);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reject-friendship-request/{friend-id}/{guest-id}")
	public ResponseEntity<?> rejectFriendshipRequest(@PathVariable(value = "friend-id") Long friendId, @PathVariable(value = "guest-id") Long guestId) {
		guestService.rejectFriendshipRequest(friendId, guestId);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete-friend/{friend-id}/{guest-id}")
	public ResponseEntity<?> deleteFriend(@PathVariable(value = "friend-id") Long friendId, @PathVariable(value = "guest-id") Long guestId) {
		guestService.deleteFriend(friendId, guestId);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-profile-page-info/{guest-id}")
	public ProfilePageDTO getProfilePageInfo(@PathVariable(value = "guest-id") Long id) {
		return guestService.getProfilePageInfo(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update-profile-info/{guest-id}")
	public ResponseEntity<?> updateProfileInfo(@PathVariable(value = "guest-id") Long id,
			@RequestBody UpdateProfileDTO dto) {
		boolean isValid = guestService.updateProfileInfo(id, dto.getName(), dto.getSurname(), dto.getAddress());

		if (isValid) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Any authenticated user can access profile-page-info
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/get-reserve1Theatre-page-info/{theatre-id}")
	public Reserve1TheatrePageDTO getReserve1TheatrePageInfo(@PathVariable(value = "theatre-id") Long id) {
		return guestService.getReserve1TheatrePageInfo(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-reserve1Cinema-page-info/{cinema-id}")
	public Reserve1CinemaPageDTO getReserve1CinemaPageInfo(@PathVariable(value = "cinema-id") Long id) {
		return guestService.getReserve1CinemaPageInfo(id);
	}
	
/*
	@RequestMapping(value = "/get-reserve2-page-info/{restaurant-id}", method = RequestMethod.GET)
	public ResponseEntity getForRestaurant(@PathVariable(value = "restaurant-id") Long id) {

		return new ResponseEntity<>(restaurantTableService.findAllByRestaurantId(id), HttpStatus.OK);
	}*/

}
