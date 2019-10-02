package com.tenor.tsf.GestionDesSalles.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.Reservation;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionIsNull;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionReservation;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionRoomIsReserved;
import com.tenor.tsf.GestionDesSalles.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	
	LocalDateTime currentDateTime= LocalDateTime.now();
	

	@Transactional
	public List<Reservation> getAllReservations() {
		return (List<Reservation>) reservationRepository.findAll();
	}

	@Transactional
	public Reservation getById(Long id) throws GSExeptionNotFound {
		Optional<Reservation> optional = reservationRepository.findById(id);

		if (!optional.isPresent()) {
			throw new GSExeptionNotFound("Reservation with id" + id );
		}
		return optional.get();
	}

	@Transactional
	public void deleteReservation(Long ReservationId) throws GSExeptionNotFound {
		Validate.notNull(ReservationId, "object given is null");
		Reservation reservation = getById(ReservationId);
		reservationRepository.delete(reservation);
	}

	@Transactional
	public Reservation addReservation(Reservation reservation) throws GSExeptionRoomIsReserved, GSExeptionIsNull, GSExeptionReservation {
		Validate.notNull(reservation, "object given is null");
		if (reservation.getRoom() == null)
			throw new GSExeptionIsNull("room given is null");
		if (reservation.getUser() == null)
			throw new GSExeptionIsNull("usr given is null");
		if (reservation.getStartDate() == null)
			throw new GSExeptionIsNull("debut date is null ");
		if (reservation.getEndDate() == null)
			throw new GSExeptionIsNull("fin date is null");
		if (reservation.getStartDate().isBefore(currentDateTime) || reservation.getEndDate().isBefore(currentDateTime))
			throw new GSExeptionReservation("Date entrered must be after current date ");
		if (reservation.getEndDate().isBefore(reservation.getStartDate()))
			throw new GSExeptionReservation("fin date must be after stard time");
		

		if (reservationRepository.checkIfRoomIsReserved(reservation.getRoom().getId(), reservation.getStartDate(),
				reservation.getEndDate()) != null) {
			throw new GSExeptionRoomIsReserved("this reservation wanted is unavailable");
			}
		return reservationRepository.save(reservation);
	}

	@Transactional
	public Reservation updateReservation(Reservation reservation) {
		Validate.notNull(reservation, "object given is null");
		return reservationRepository.save(reservation);
	}

	@Transactional
	public void deleteAll() {
		reservationRepository.deleteAll();
	}

}

