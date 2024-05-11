package com.system.remedios.requests;
import com.system.remedios.util.MedicineCreator;

//This class only to get the type MedicinePostRequestBody and to insert like param in test MedicineController

public class MedicinePostRequestCreator {
    public static MedicinePostRequestBody medicinePostRequestBody(){
        return MedicinePostRequestBody.builder()
                .name(MedicineCreator.createMedicineDataToBeSaved().getName())
                .build();
    }
}
