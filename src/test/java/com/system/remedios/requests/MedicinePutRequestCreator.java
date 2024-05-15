package com.system.remedios.requests;

import com.system.remedios.util.MedicineCreator;


public class MedicinePutRequestCreator {
    public static MedicinePutRequestBody medicinePutRequestBody(){
        return MedicinePutRequestBody.builder()
                .name(MedicineCreator.replaceMedicineDataNameAndOthers().getName())
                .build();
    }
}
