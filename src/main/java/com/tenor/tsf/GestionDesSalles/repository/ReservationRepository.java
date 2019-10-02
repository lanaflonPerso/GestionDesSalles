package com.tenor.tsf.GestionDesSalles.repository;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tenor.tsf.GestionDesSalles.entity.Reservation;
import com.tenor.tsf.GestionDesSalles.entity.Room;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	@Query(value="SELECT * FROM `room` WHERE `id` = (SELECT `room_id` from `reservation` "
			+ "WHERE (reservation.start_date BETWEEN ':startDate' AND ':endDate' "
			+ "OR reservation.end_date BETWEEN :startDate AND :endDate) "
			+ "AND reservation.room_id= :roomId )",nativeQuery=true)
	public Room checkIfRoomIsReserved(@Param("roomId") Long roomId,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
	
}
