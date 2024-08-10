package com.system.remedios.util;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.domain.Medicine;

import java.time.LocalDate;

public class MedicineCreator {
    public static Medicine createValidMedicineData(){
        return Medicine.builder().name("Buscopam")
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
    public static Medicine replaceMedicineDataNameAndOthers(){
        return Medicine.builder().name("Buscopam 2")
                .id(1L)
                .via(Via.NASAL)
                .quantity(150)
                .laboratory(Laboratory.ACHE)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }
    public static Medicine createMedicineDataAtivoFalse(){
        return Medicine.builder().name("Buscopam")
                .id(1L)
                .via(Via.ORAL)
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(false)
                .build();
    }

}
