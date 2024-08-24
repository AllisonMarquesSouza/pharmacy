package com.system.remedios.util;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.domain.Medicine;

import java.time.LocalDate;

public class MedicineCreator {
    public static Medicine createValidMedicineData(){
        return Medicine.builder().name("Buscopam")
                .id(2L)
                .via(Via.ORAL)
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }
    public static Medicine createMedicineDataToBeSaved(){
        return Medicine.builder().name("Buscopam")
                .id(1L)
                .via(Via.ORAL)
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }

}
