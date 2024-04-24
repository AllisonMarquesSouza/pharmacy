package com.system.remedios.DataConnection;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.Medicines.RegisterMedicine;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


//@Table(name = "Remedio") -> form to set name of table in database
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicineData {
    public MedicineData(RegisterMedicine registerMedicine){
        this.name = registerMedicine.name();
        this.via = registerMedicine.via();
        this.lot = registerMedicine.lot();
        this.quantity = registerMedicine.quantity();
        this.validity = registerMedicine.validity();
        this.laboratory = registerMedicine.laboratory();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generate automatically
    private Long id;

    @Enumerated(EnumType.STRING)
    private Via via;

    private String name;
    private String lot;
    private int quantity;
    private LocalDate validity;

    @Enumerated(EnumType.STRING)
    private Laboratory laboratory;
}


