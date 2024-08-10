package com.system.remedios.dtos;
import com.system.remedios.util.MedicineCreator;


public class MedicinePostRequestCreator {
    public static MedicineDtoPost medicinePostRequestBody(){
        return MedicineDtoPost.builder()
                .name(MedicineCreator.createMedicineDataToBeSaved().getName())
                .build();
    }
}
