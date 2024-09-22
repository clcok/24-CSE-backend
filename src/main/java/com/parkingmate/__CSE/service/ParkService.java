package com.parkingmate.__CSE.service;

import com.parkingmate.__CSE.domain.ParkingSpace;
import com.parkingmate.__CSE.domain.User;
import com.parkingmate.__CSE.dto.request.EnrollRequest;
import com.parkingmate.__CSE.dto.response.AvailableSpaceResponse;
import com.parkingmate.__CSE.repository.ParkRepository;
import com.parkingmate.__CSE.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


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


        //카카오API발급
        String address = enrollRequest.getAddress();
        String apiKey = "c9f2fd9af00f3087ba03d12194240964"; // 발급받은 API 키
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONObject document = jsonResponse.getJSONArray("documents").getJSONObject(0);
        double latitude = document.getDouble("y");  // 위도
        double longitude = document.getDouble("x"); // 경도
        System.out.println("!!!!좌표변환:"+latitude+" "+longitude);

        // User 객체에 좌표 저장
        parkingSpace.setLatitude(latitude);
        parkingSpace.setLongitude(longitude);

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
                space.getIsAvailable(),
                space.getLatitude(),
                space.getLongitude()
        );
    }
}
