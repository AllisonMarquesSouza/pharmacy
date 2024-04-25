package com.system.remedios.domain;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


//@Table(name = "Remedio") -> form to set name of table in database
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}


