package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.Reservation;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.DeleteReservationRequest;
import com.parkingmate.__CSE.dto.request.ReservationRequest;
import com.parkingmate.__CSE.dto.response.ReservationResponse;
import com.parkingmate.__CSE.repository.ParkRepository;
import com.parkingmate.__CSE.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkRepository parkRepository;

    @Transactional
    public Reservation createReservation(
            User user,
            Long parkingspaceId,
            ReservationRequest reservationRequest
    ) {
        //ParkingSpace 조회
        ParkingSpace parkingSpace = parkRepository.findById(parkingspaceId)
                .orElseThrow(() -> new RuntimeException("주차 공간을 찾을 수 없습니다."));

        // 예약 가능 여부 확인 (예: 중복 예약 방지)
        if (!parkingSpace.getIsAvailable()) { //이걸 array해서 파악해야하는데 일단은 팓단하기위해서 이렇게만
            throw new RuntimeException("해당 시간에 예약이 불가능합니다.");
        }

        // Reservation 객체 생성
        Reservation reservation = new Reservation(
                user,
                parkingSpace,
                reservationRequest.date(),
                reservationRequest.startTime(),
                reservationRequest.endTime()
        );

        //차량 대수 조정.
        parkingSpace.addUseCar();

        //관계 설정
        //reservation.assignUserAndParkingSpace(user, parkingSpace);
        //예약 저장
        return reservationRepository.save(reservation);

    }

    public List<ReservationResponse> loadReservation(User user){
        List<Reservation> reservationList = reservationRepository.findByUserId(user.getId());
        return reservationList.stream().map(ReservationResponse::from).toList();
    }

    public void returnParkingSpace(DeleteReservationRequest deleteReservationRequest){
        reservationRepository.deleteById(deleteReservationRequest.getReservationId());
    }
}
