package com.system.remedios.requests;
import com.system.remedios.util.MedicineCreator;


public class MedicinePostRequestCreator {
    public static MedicinePostRequestBody medicinePostRequestBody(){
        return MedicinePostRequestBody.builder()
                .name(MedicineCreator.createMedicineDataToBeSaved().getName())
                .build();
    }
}
