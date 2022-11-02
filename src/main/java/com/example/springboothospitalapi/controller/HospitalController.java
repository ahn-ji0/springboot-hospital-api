package com.example.springboothospitalapi.controller;

import com.example.springboothospitalapi.dao.HospitalDao;
import com.example.springboothospitalapi.domain.Hospital;
import com.example.springboothospitalapi.domain.dto.HospitalRequestDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @ApiOperation(value="hospital add",notes="hospital 정보 db에 추가")
    @PostMapping(value="/hospital")
    public ResponseEntity<Integer> add(@RequestBody HospitalRequestDto hospitalRequestDto){
        Hospital hospital = new Hospital(
                hospitalRequestDto.getId(),
                hospitalRequestDto.getOpenServiceName(),
                hospitalRequestDto.getOpenLocalGovernmentCode(),
                hospitalRequestDto.getManagementNumber(),
                hospitalRequestDto.getLicenseDate(),
                hospitalRequestDto.getBusinessStatus(),
                hospitalRequestDto.getBusinessStatusCode(),
                hospitalRequestDto.getPhone(),
                hospitalRequestDto.getFullAddress(),
                hospitalRequestDto.getRoadNameAddress(),
                hospitalRequestDto.getHospitalName(),
                hospitalRequestDto.getBusinessTypeName(),
                hospitalRequestDto.getHealthcareProviderCount(),
                hospitalRequestDto.getPatientRoomCount(),
                hospitalRequestDto.getTotalNumberOfBeds(),
                hospitalRequestDto.getTotalAreaSize());
        return ResponseEntity.ok().body(hospitalDao.add(hospital));
    }

    @ApiOperation(value="hospital deleteAll",notes="hospital 정보 모두 삭제")
    @DeleteMapping(value="/hospital/all")
    public ResponseEntity<Integer> deleteAll(){
        return ResponseEntity.ok().body(hospitalDao.deleteAll());
    }

    @ApiOperation(value="hospital count",notes="hospital 개수 세기")
    @GetMapping(value="/hospital/count")
    public ResponseEntity<Integer> getCount(){
        return ResponseEntity.ok().body(hospitalDao.getCount());
    }

    @ApiOperation(value="hospital get by Id",notes="특정 id의 hospital 불러오기")
    @GetMapping(value="/hospital/{id}")
    public ResponseEntity<Hospital> getId(@PathVariable int id){
        Hospital hospital = hospitalDao.getById(id);
        Optional<Hospital> opt = Optional.of(hospital);
        if(!opt.isEmpty()){
            return ResponseEntity.ok().body(hospital);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }
}
