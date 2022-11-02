package com.example.springboothospitalapi.service;

import com.example.springboothospitalapi.dao.HospitalDao;
import com.example.springboothospitalapi.domain.Hospital;
import com.example.springboothospitalapi.parser.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

//    @Autowired
//    HospitalDao hospitalDao;
//
//    @Autowired
//    Read<Hospital> hospitalRead;

    private final Read<Hospital> hospitalRead;
    private final HospitalDao hospitalDao;

    public HospitalService(Read<Hospital> hospitalRead, HospitalDao hospitalDao) {
        this.hospitalRead = hospitalRead;
        this.hospitalDao = hospitalDao;
    }

    @Transactional
    public int insertLargeVolumeHospitalData(String filename) {
        int count = 0;
        hospitalDao.deleteAll();
        List<Hospital> hospitalList = null;
        try {
            hospitalList = hospitalRead.readLines(filename);
            System.out.println("파싱 완료");
            for(Hospital hospital : hospitalList) {
                try {
                    hospitalDao.add(hospital);
                } catch (Exception e) {
                    System.out.printf("%d번 레코드에 문제가 있다\n", hospital.getId());
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!Optional.of(hospitalList).isEmpty()){
            return hospitalList.size();
        }
        else
            return 0;
    }
}
