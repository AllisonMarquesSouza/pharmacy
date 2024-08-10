package com.system.remedios.dtos;

import com.system.remedios.util.MedicineCreator;


public class MedicinePutRequestCreator {
    public static MedicineDtoPut medicinePutRequestBody(){
        return MedicineDtoPut.builder()
                .name(MedicineCreator.replaceMedicineDataNameAndOthers().getName())
                .build();
    }
}
