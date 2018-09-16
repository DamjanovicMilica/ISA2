package com.bioskopPoy.service.guest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bioskopPoy.model.Cinema;
import com.bioskopPoy.model.FriendshipRequest;
import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.Theatres;
import com.bioskopPoy.model.User;
import com.bioskopPoy.model.UserRoles;
import com.bioskopPoy.model.reservation.ReservationCinema;
import com.bioskopPoy.model.reservation.ReservationTheatre;
import com.bioskopPoy.repository.CinemaRepository;
import com.bioskopPoy.repository.FriendshipRequestRepository;
import com.bioskopPoy.repository.ReservationCinemaRepository;
import com.bioskopPoy.repository.ReservationTheatreRepository;
import com.bioskopPoy.repository.TheatresRepository;
import com.bioskopPoy.repository.guest.GuestRepository;
import com.bioskopPoy.rest.guest.dto.AcceptPeopleDTO;
import com.bioskopPoy.rest.guest.dto.AddPeopleDTO;
import com.bioskopPoy.rest.guest.dto.CinemasPageDTO;
import com.bioskopPoy.rest.guest.dto.FriendsPageDTO;
import com.bioskopPoy.rest.guest.dto.HomePageCinemasDTO;
import com.bioskopPoy.rest.guest.dto.HomePageTheatresDTO;
import com.bioskopPoy.rest.guest.dto.LoginDTO;
import com.bioskopPoy.rest.guest.dto.ProfilePageDTO;
import com.bioskopPoy.rest.guest.dto.Reserve1CinemaPageDTO;
import com.bioskopPoy.rest.guest.dto.Reserve1TheatrePageDTO;
import com.bioskopPoy.rest.guest.dto.TheatresPageDTO;
import com.bioskopPoy.service.MailService;


@Service
@Transactional
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;
	/*
	@Autowired
	private FriendshipRequestRepository friendshipRequestRepository; 

*/
	@Autowired
	private ReservationCinemaRepository reservationCinemaRepository;

	@Autowired
	private ReservationTheatreRepository reservationTheatreRepository;
	
	@Autowired
	private TheatresRepository theatresRepository; 
	
	@Autowired
	private CinemaRepository cinemaRepository; 
	
	@Autowired
	private FriendshipRequestRepository friendshipRequestRepository;

	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * Request BCrypt2 encoder
	 * 
	 * @return
	 */
	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Autowired
	private MailService mailManager;
	/*
	@Autowired 
	private ReservationRestaurantTableRepository reservationRestaurantTableRepository;
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private MenuRepository menuRepository;
*/
	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	public Guest findOne(Long id) {
		return guestRepository.findOne(id);
	}

	private Guest save(Guest guest) {
		return guestRepository.save(guest);
	}

	public Guest delete(Long id) {
		Guest guest = guestRepository.findOne(id);

		if (guest != null) {
			guestRepository.delete(guest);
		}

		return guest;
	}
/*
	
*/
	public Guest registerGuest(User user) {
		user.setRole(UserRoles.GUEST);
		org.springframework.security.crypto.password.PasswordEncoder encoder
		   = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

		user.setPassword(encoder.encode(user.getPassword()));
		Guest guest = new Guest(user);
		guest.setActive(false);

		Guest saved = save(guest);

		System.out.println((User) saved);

		if (saved != null) {
			System.out.println("MAIL SENT TO " + guest.getEmail());
			mailManager.sendMail(guest);

			return saved;
		}
		return null;
	}
	
	public boolean verifyGuest(long id) {
		Guest guest = guestRepository.findOne(id);
		System.out.println("********************************");
		System.out.println(id);
		System.out.println("********************************");
		
		if (guest == null) {
			return false;
		}
		guest.setActive(true);
		guestRepository.save(guest);
		return true;
	}

	
	public Guest login(LoginDTO dto) {
		org.springframework.security.crypto.password.PasswordEncoder encoder
		   = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

		System.out.println("PASSWORD: " + encoder.encode(dto.getPassword()));
		
		Guest guest = guestRepository.findByEmail(dto.getUsername()); 
		
		

		if(encoder.matches(dto.getPassword(), guest.getPassword())) {
			System.out.println("LOGGED USER: " + guest.getEmail());
		}
		
		return guest; 
		
	}

	public List<HomePageCinemasDTO> getHomePageInfoCinemas(Long id) {
	{
		List<HomePageCinemasDTO> homePageData = new ArrayList<HomePageCinemasDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}

		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		List<ReservationCinema> reservations = reservationCinemaRepository.findByGuest(guest);
		for (ReservationCinema reservation : reservations) {
			HomePageCinemasDTO dto = new HomePageCinemasDTO();
			String cinemaName = reservation.getCinema().getName();
			dto.setCinemaName(cinemaName);
			
			// Get the date today using Calendar object.
			Date date = reservation.getTerminOd();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			if(date != null) {
				String reportDate = df.format(date);
				dto.setDate(reportDate);
			}
			

			
	
			homePageData.add(dto);
		}

		return homePageData;
	}
}

	public List<HomePageTheatresDTO> getHomePageInfoTheatres(Long id) {
		List<HomePageTheatresDTO> homePageData = new ArrayList<HomePageTheatresDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}

		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		List<ReservationTheatre> reservations = reservationTheatreRepository.findByGuest(guest);
		for (ReservationTheatre reservation : reservations) {
			HomePageTheatresDTO dto = new HomePageTheatresDTO();
			String theatreName = reservation.getTheatres().getName();
			dto.setTheatresName(theatreName);
			
			// Get the date today using Calendar object.
			Date date = reservation.getTerminOd();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			if(date != null) {
				String reportDate = df.format(date);
				dto.setDate(reportDate);
			}
			

			
	
			homePageData.add(dto);
		}

		return homePageData;
	}

	public List<TheatresPageDTO> getTheatresPageInfo(Long id) {
		List<TheatresPageDTO> theatrePageData = new ArrayList<TheatresPageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return theatrePageData;
		}

		List<Theatres> theatres = theatresRepository.findAll();
		for (Theatres theatre : theatres) {
			TheatresPageDTO dto = new TheatresPageDTO();
			dto.setName(theatre.getName());
			dto.setDistance(theatre.getDistance());
			dto.setId(theatre.getId());
			theatrePageData.add(dto);
		}

		return theatrePageData;
	}

	public List<CinemasPageDTO> getCinemasPageInfo(Long id) {

		List<CinemasPageDTO> cinemaPageData = new ArrayList<CinemasPageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return cinemaPageData;
		}

		List<Cinema> cinemas = cinemaRepository.findAll();
		for (Cinema cinema : cinemas) {
			CinemasPageDTO dto = new CinemasPageDTO();
			dto.setName(cinema.getName());
			dto.setDistance(cinema.getDistance());
			dto.setId(cinema.getId());
			cinemaPageData.add(dto);
		}

		return cinemaPageData;
	}

	public List<FriendsPageDTO> getFriendsPageInfo(Long id) {
		List<FriendsPageDTO> friendPageData = new ArrayList<FriendsPageDTO>();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}

		List<Guest> friends = guest.getFriends();
		for (Guest friend : friends) {
			FriendsPageDTO dto = new FriendsPageDTO();
			String friendName = friend.getName();

			StringBuilder builder = new StringBuilder();
			builder.append(friend.getName()).append(" ").append(friend.getSurname());

			dto.setNameAndSurname(builder.toString());

			long numberOfVisits = 0;
			
			dto.setNumberOfVisits(numberOfVisits);
			dto.setId(friend.getId());
			friendPageData.add(dto);
		}

		return friendPageData;
	}

	public List<AddPeopleDTO> addPeople(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<Guest> people = guestRepository.findAll(); 
		List<AddPeopleDTO> addPeopleDto = new ArrayList<>(); 
		
		for(Guest p : people) {	
				if(guest.getId() != p.getId() && !isFriend(guest.getFriends(), p)) {
					AddPeopleDTO dto = new AddPeopleDTO(); 
					dto.setId(p.getId());
					dto.setName(p.getName());
					dto.setSurname(p.getSurname());
					dto.setFriend(false);
					addPeopleDto.add(dto); 
				}
			}	
		return addPeopleDto ;
	}

	private boolean isFriend(List<Guest> friends, Guest p) {
		for(Guest friend : friends) {
			if(friend.getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	public List<AcceptPeopleDTO> acceptPeople(Long id) {
		Guest guest = guestRepository.findOne(id); 
		List<FriendshipRequest> requests = friendshipRequestRepository.findByToGuest(guest); 
		List<AcceptPeopleDTO> acceptPeopleDTO = new ArrayList<>(); 
		for(FriendshipRequest request : requests) {
			if(!request.isConfirmed()) {
				AcceptPeopleDTO dto = new AcceptPeopleDTO();
				dto.setId(request.getFrom().getId());
				dto.setConfirmed(false);
				dto.setName(request.getFrom().getName());
				dto.setSurname(request.getFrom().getSurname());
				if(!contains(acceptPeopleDTO, dto)) {
					acceptPeopleDTO.add(dto);
				}
				 
			}
		}
		return acceptPeopleDTO;
	}
	
	private boolean contains(List<AcceptPeopleDTO> acceptPeopleDTO, AcceptPeopleDTO dto) {
		for(AcceptPeopleDTO accept : acceptPeopleDTO) {
			if(accept.getId() == dto.getId()) {
				return true; 
			}
		}
		return false;
	}

	public void sendFriendshipRequest(Long friendId, Long guestId) {
		FriendshipRequest request = new FriendshipRequest();
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		request.setFrom(guest);
		request.setTo(friend);
		friendshipRequestRepository.save(request); 
		
	}

	public void acceptFriendshipRequest(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		List<FriendshipRequest> invited = friendshipRequestRepository.findByFromGuest(friend); 
		
		for(FriendshipRequest i : invited) {
			if(i.getTo().getId() == guest.getId()) {
				i.setConfirmed(true);
				friendshipRequestRepository.save(i); 
				guest.getFriends().add(friend); 
				guestRepository.save(friend); 
				friend.getFriends().add(guest); 
				guestRepository.save(guest); 
			}
		}		
		
	}

	public void rejectFriendshipRequest(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		Guest friend = guestRepository.findOne(friendId); 
		List<FriendshipRequest> invited = friendshipRequestRepository.findByFromGuest(friend); 
		
		for(FriendshipRequest i : invited) {
			if(i.getTo().getId() == guest.getId()) {
				i.setConfirmed(false);
				friendshipRequestRepository.delete(i); 
			}
		}		
		
	}

	public void deleteFriend(Long friendId, Long guestId) {
		Guest guest = guestRepository.findOne(guestId); 
		List<Guest> friends = guest.getFriends(); 
		for(int i = 0; i < friends.size(); i++) {
			if(friends.get(i).getId() == friendId) {
				
				for(int j = 0; j < friends.get(i).getFriends().size(); j++) {
					if(friends.get(i).getFriends().get(j).getId() == guest.getId()) {
						friends.get(i).getFriends().remove(j);
						guestRepository.save(friends.get(i)); 
					}
				}
				friends.remove(i); 
			}
		}
		guest.setFriends(friends);
		guestRepository.save(guest); 
		
	}

	public ProfilePageDTO getProfilePageInfo(Long id) {
		ProfilePageDTO dto = new ProfilePageDTO();

		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return null;
		}
		dto.setName(guest.getName());
		dto.setSurname(guest.getSurname());
		dto.setAddress(guest.getAddress());
		long numberOfVisits = 0;
		
		dto.setNumberOfVisits(numberOfVisits);
		List<Guest> friends = guest.getFriends();
		List<ProfilePageDTO> friendsDTO = new ArrayList<>();
		for (Guest friend : friends) {
			ProfilePageDTO friendDTO = new ProfilePageDTO();
			friendDTO.setId(friend.getId());
			friendDTO.setName(friend.getName());
			friendDTO.setSurname(friend.getSurname());
			friendsDTO.add(friendDTO);

		}
		dto.setFriends(friendsDTO);
		return dto;

	}

	public boolean updateProfileInfo(Long id, String name, String surname, String address) {
		Guest guest = guestRepository.findOne(id);
		if (guest == null) {
			return false;
		}

		guest.setName(name);
		guest.setSurname(surname);
		guest.setAddress(address);
		guestRepository.save(guest);
		return true;
	}
	
	
	public Reserve1TheatrePageDTO getReserve1TheatrePageInfo(Long id) {
		Reserve1TheatrePageDTO reserve1 = new Reserve1TheatrePageDTO(); 
		Theatres theatre = theatresRepository.findById(id);
		reserve1.setTheatreName(theatre.getName());
		return reserve1;
	}

	public Reserve1CinemaPageDTO getReserve1CinemaPageInfo(Long id) {
		Reserve1CinemaPageDTO reserve1 = new Reserve1CinemaPageDTO(); 
		Cinema cinema = cinemaRepository.findOne(id);
		reserve1.setCinemaName(cinema.getName());
		return reserve1;
	}
	
}
