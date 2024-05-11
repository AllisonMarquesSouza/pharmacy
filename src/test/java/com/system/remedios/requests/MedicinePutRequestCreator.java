package com.system.remedios.requests;

import com.system.remedios.util.MedicineCreator;

//This class only to get the type MedicinePutRequestBody and to insert like param in test MedicineController

public class MedicinePutRequestCreator {
    public static MedicinePutRequestBody medicinePutRequestBody(){
        return MedicinePutRequestBody.builder()
                .name(MedicineCreator.replaceMedicineDataNameAndOthers().getName())
                .build();
    }
}
