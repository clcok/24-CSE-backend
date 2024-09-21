package com.parkingmate.__CSE.repository;

import com.parkingmate.__CSE.domain.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<ParkingSpace,Long> {
}
