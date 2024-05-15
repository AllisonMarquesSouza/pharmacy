package com.system.remedios.domain;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "medicine_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public void inactive(){
       this.ativo = false;
    }
    public void active(){
       this.ativo = true;
    }


}


