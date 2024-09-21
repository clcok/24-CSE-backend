package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.repository.ParkRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkService {

    @Autowired
    public ParkRepository parkRepository;

    @Transactional
    public void enrollParkingSpace(EnrollRequest enrollRequest, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.enrollParkingSpace(enrollRequest);
    }
}
