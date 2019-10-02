package com.tenor.tsf.GestionDesSalles.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.Reservation;
import com.tenor.tsf.GestionDesSalles.exeption.*;
import com.tenor.tsf.GestionDesSalles.repository.ReservationRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ReservationService123 {

	@Autowired
	ReservationRepository repo;

	private LocalDateTime currentDateTime = LocalDateTime.now();

	/*
	 * Method to check if date is valid return GSExeptionRoomIsReserved
	 * exception if stared date or end date entered are before current date
	 * return GSExeptionRoomIsReserved exception if stared date if before end
	 * date return true if stared date or end date entered are after Current
	 * date
	 */

	public Boolean CheckValideDate(Reservation reservation) throws GSExeptionReservation {
		if (reservation.getStartDate().isBefore(currentDateTime)) {
			throw new GSExeptionReservation("Stared date must be after current date");
		} else if (reservation.getEndDate().isBefore(currentDateTime)) {
			throw new GSExeptionReservation("End date must be after current date");
		} else if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
			throw new GSExeptionReservation("End date must be after stared date");
		} else
			return true;
	}

	public Reservation create(Reservation reservation) throws GSExeptionReservation, GSExeptionRoomIsReserved {
		Validate.notNull(reservation, "Reservation is null");
		Validate.notNull(reservation.getStartDate(), "Start date is null");
		Validate.notNull(reservation.getEndDate(), "end date is null");
		Validate.notNull(reservation.getRoom(), "room is null");
		Validate.notNull(reservation.getUser(), "User is null");
		Validate.isTrue(CheckValideDate(reservation)); // Check if reservation
														// date is after current
														// date

		// @checkIfRoomIsReserved return null if room wonted is not reserved .
		if (repo.checkIfRoomIsReserved(reservation.getRoom().getId(), reservation.getStartDate(),
				reservation.getEndDate()) == null) {

			log.info("Room is reservation from " + reservation.getStartDate() + " to " + reservation.getEndDate());
			return repo.save(reservation);

		} else {
			throw new GSExeptionRoomIsReserved("Room with id " + reservation.getId());
		}

	}

	public void delete(Long id) throws GSExeptionNotFound {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			log.info("Reservation with ID " + getById(id) + "is canceled");
		} else {
			throw new GSExeptionNotFound("Reservation");
		}
	}

	public void update(Reservation res) throws GSExeptionIsNull, GSExeptionNotFound {
		Validate.notNull(res, "object can't be null");
		Boolean trouve = false;
		if (res.getStartDate() == null || res.getEndDate() == null || res.getId() == null || res.getRoom() == null
				|| res.getUser() == null || res.getStartDate() == null && res.getEndDate() == null
						&& res.getId() == null && res.getRoom() == null && res.getUser() == null) {
			throw new GSExeptionIsNull("");
		} else if (res.getStartDate().isBefore(currentDateTime) || res.getEndDate().isBefore(res.getStartDate())) {
			throw new GSExeptionIsNull("");
		} else {
			for (Reservation item : repo.findAll()) {
				if (item.getId() == res.getId()) {
					repo.save(res);
					log.info("Reservation is updated :" + res);
					trouve = true;
				}

			}
		}
		if (trouve == false) {
			throw new GSExeptionNotFound("Resevation ");
		}
	}

	public Boolean CheckRoomAvialable(Long id, LocalDateTime dateStart, LocalDateTime dateEnd)
			throws GSExeptionAlreadyExist {
		for (Reservation res : repo.findAll()) {
			if (res.getRoom().getId() == id) {
				if (dateStart.isBefore(res.getStartDate()) && dateStart.isAfter(res.getStartDate())
						|| dateEnd.isAfter(res.getStartDate()) && dateEnd.isBefore(res.getEndDate())
						|| dateStart.isEqual(res.getStartDate()) || dateEnd.isEqual(res.getEndDate())
						|| dateStart.isBefore(res.getStartDate()) && dateEnd.isAfter(res.getEndDate())) {
					throw new GSExeptionAlreadyExist("Reservation ");
				}
			}
		}
		log.info(
				"Room number " + id + " is avilable in the time between --" + dateStart + "-- and --" + dateEnd + "--");
		return true;

	}

	public List<Reservation> getAllReservation() {
		return (List<Reservation>) repo.findAll();
	}

	public Optional<Reservation> getById(Long id) {
		return repo.findById(id);
	}
}
