package com.parkingmate.__CSE.repository;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkRepository extends JpaRepository<ParkingSpace,Long> {
    @Query("SELECT ps.user.id FROM ParkingSpace ps WHERE ps.id = :parkingSpaceId")
    Long findUserIdByParkingSpaceId(@Param("parkingSpaceId") Long parkingSpaceId);
}
