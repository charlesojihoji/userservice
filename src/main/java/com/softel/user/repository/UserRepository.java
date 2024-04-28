package com.softel.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softel.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(nativeQuery = true, value = "SELECT ea.id, ea.about, ea.emailid, ea.name FROM softeldb.micro_users ea join softeldb.micro_hotel e on e.id = ea.hotel_id where ea.hotel_id=:hotelId")
	Optional<User> findUserByHotelId(@Param("hotelId") String hotelId);

}
