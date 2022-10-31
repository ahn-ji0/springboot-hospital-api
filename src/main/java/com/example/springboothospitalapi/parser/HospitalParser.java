package com.example.springboothospitalapi.parser;

import com.example.springboothospitalapi.domain.Hospital;
import io.swagger.models.auth.In;

import java.time.LocalDateTime;

public class HospitalParser implements Parser<Hospital> {
    @Override
    public Hospital parse(String str) {
        //str = "1,의원,01_01_02_P,3620000,PHMA119993620020041100004,19990612,,1,영업/정상,13,영업중,,,,,062-515-2875,,500881,광주광역시 북구 풍향동 565번지 4호 3층,"광주광역시 북구 동문대로 24, 3층 (풍향동)",61205,효치과의원,2.02111E+13,U,2021.11.17 2:40,치과의원,192630.7351,185314.6176,치과의원,1,0,0,52.29,401,치과,,,,0,0,,,0,"
        String[] arr = str.replace(", ",". ").split(",");
        Hospital hospital = new Hospital();
        hospital.setId(Integer.parseInt(arr[0]));
        hospital.setOpenServiceName(arr[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(arr[3]));
        hospital.setManagementNumber(arr[4]);

        int year = Integer.parseInt(arr[5].substring(0,4));
        int month = Integer.parseInt(arr[5].substring(4,6));
        int day = Integer.parseInt(arr[5].substring(6,8));

        hospital.setLicenseDate(LocalDateTime.of(year,month,day,0,0,0));
        hospital.setBusinessStatus(Integer.parseInt(arr[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(arr[9]));
        hospital.setPhone(arr[15]);
        hospital.setFullAddress(arr[18]);
        hospital.setRoadNameAddress(arr[19].replace(".",",").replace("\"",""));
        hospital.setHospitalName(arr[21]);
        hospital.setBusinessTypeName(arr[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(arr[29]));
        hospital.setPatientRoomCount(Integer.parseInt(arr[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(arr[31]));
        hospital.setTotalAreaSize(Integer.parseInt(arr[32]));


        return hospital;
    }
}
