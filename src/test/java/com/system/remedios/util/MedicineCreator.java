package com.system.remedios.util;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.domain.MedicineData;

import java.time.LocalDate;

public class MedicineCreator {
    public static MedicineData createValidMedicineData(){
        return MedicineData.builder().name("Buscopam")
                .via(Via.ORAL)
                .lot("10")
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }
    public static MedicineData createMedicineDataToBeSaved(){
        return MedicineData.builder().name("Buscopam")
                .id(1L)
                .via(Via.ORAL)
                .lot("10")
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }
    public static MedicineData replaceMedicineDataNameAndOthers(){
        return MedicineData.builder().name("Buscopam 2")
                .id(1L)
                .via(Via.NASAL)
                .lot("15")
                .quantity(150)
                .laboratory(Laboratory.ACHE)
                .validity(LocalDate.now())
                .ativo(true)
                .build();
    }
    public static MedicineData createMedicineDataAtivoFalse(){
        return MedicineData.builder().name("Buscopam")
                .id(1L)
                .via(Via.ORAL)
                .lot("10")
                .quantity(100)
                .laboratory(Laboratory.MEDLEY)
                .validity(LocalDate.now())
                .ativo(false)
                .build();
    }

}
