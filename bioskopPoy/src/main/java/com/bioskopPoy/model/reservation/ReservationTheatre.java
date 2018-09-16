package com.bioskopPoy.model.reservation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bioskopPoy.model.Guest;
import com.bioskopPoy.model.Theatres;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ReservationTheatre")
public class ReservationTheatre implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_theatre_id")
	private Long id; 
	
	@ManyToOne
	private Theatres theatres;
	
	@Column(name="reservation_start")
	private Date terminOd; 
	
	@Column(name="reservation_end")
	private Date terminDo; 
	
	@JsonIgnore
	@ManyToOne
	private Guest guest;
		
	public ReservationTheatre() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Theatres getTheatres() {
		return theatres;
	}

	public void setTheatres(Theatres theatres) {
		this.theatres = theatres;
	}

	public Date getTerminOd() {
		return terminOd;
	}

	public void setTerminOd(Date terminOd) {
		this.terminOd = terminOd;
	}

	public Date getTerminDo() {
		return terminDo;
	}

	public void setTerminDo(Date terminDo) {
		this.terminDo = terminDo;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}
