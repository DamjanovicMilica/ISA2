package com.bioskopPoy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bioskopPoy.model.reservation.ReservationCinema;
import com.bioskopPoy.model.reservation.ReservationTheatre;

@Entity
@Table(name = "guest")
public class Guest extends User implements Serializable {

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 @JoinTable(
	   name = "guestfriends", 
	   joinColumns = @JoinColumn(name = "guest_id"), 
	   inverseJoinColumns = @JoinColumn(name = "friend_id")
	 )
	private List<Guest> friends = new ArrayList<>();

	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 private List<ReservationCinema> reservationsCinema = new ArrayList<>();

	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 private List<ReservationTheatre> reservationsTheatres = new ArrayList<>();

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	//private List<Reservation> reservations = new ArrayList<>();


	public Guest() {
	}

	public Guest(User u) {
		super(u);
	}

	public List<Guest> getFriends() {
		return friends;
	}

	public void setFriends(List<Guest> friends) {
		this.friends = friends;
	}

	public List<ReservationCinema> getReservationsCinema() {
		return reservationsCinema;
	}

	public void setReservationsCinema(List<ReservationCinema> reservationsCinema) {
		this.reservationsCinema = reservationsCinema;
	}

	public List<ReservationTheatre> getReservationsTheatres() {
		return reservationsTheatres;
	}

	public void setReservationsTheatres(List<ReservationTheatre> reservationsTheatres) {
		this.reservationsTheatres = reservationsTheatres;
	}

}

