package com.system.remedios.domain;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


//@Table(name = "Remedio") -> form to set name of table in database
@Entity
@Table(name = "medicine_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generate automatically
    private Long id;


    private String name;

    @Enumerated(EnumType.STRING)
    private Via via;

    private String lot;
    private int quantity;
    private LocalDate validity;

    @Enumerated(EnumType.STRING)
    private Laboratory laboratory;

    private Boolean ativo;

    //constructor with all parameters to the method save
    public MedicineData(MedicineData medicineData){
        this.id = medicineData.getId();
        this.name = medicineData.getName();
        this.via = medicineData.getVia();
        this.lot = medicineData.getLot();
        this.quantity = medicineData.getQuantity();
        this.validity = medicineData.getValidity();
        this.laboratory = medicineData.getLaboratory();
        this.ativo = medicineData.getAtivo();
    }

    public void inactive(){
       this.ativo = false;
    }
    public void active(){
       this.ativo = true;
    }


}


