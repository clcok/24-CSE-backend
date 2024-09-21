package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.repository.ParkRepository;
import com.parkingmate.__CSE.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkService {

    @Autowired
    private ParkRepository parkRepository;
    @Autowired
    private UserRepository userRepository;


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




}
