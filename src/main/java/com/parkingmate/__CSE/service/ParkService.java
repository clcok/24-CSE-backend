package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.dto.response.AvailableSpaceResponse;
import com.parkingmate.__CSE.repository.ParkRepository;
import com.parkingmate.__CSE.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ParkService {

    @Autowired
    public ParkRepository parkRepository;
    @Autowired
    public UserRepository userRepository;

    @Transactional
    public void enrollParkingSpace(EnrollRequest enrollRequest, HttpSession session){
        if(enrollRequest.getName().isBlank()){
            throw new RuntimeException("정보를 다시 입력해주세요.");
        }
        User sessionUser = (User) session.getAttribute("user");

        // 영속성 컨텍스트에서 User를 조회
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        ParkingSpace parkingSpace = new ParkingSpace(user, enrollRequest);

        //관계 설정
        parkingSpace.assignUser(user);

        parkRepository.save(parkingSpace);
    }

    public List<AvailableSpaceResponse> availableSpace(){
        List<ParkingSpace> space = parkRepository.findAll();
        return space.stream().map(this::dtoChange).toList();
    }

    public AvailableSpaceResponse dtoChange(ParkingSpace space){
        Long id = parkRepository.findUserIdByParkingSpaceId(space.getId());
        User user = userRepository.findUserById(id);
        return new AvailableSpaceResponse(
                space.getId(),
                user.getName(),
                space.getName(),
                space.getMaxCar(),
                space.getUseCar(),
                space.getPrice(),
                space.getExplain(),
                space.getIsAvailable()
        );
    }
}
