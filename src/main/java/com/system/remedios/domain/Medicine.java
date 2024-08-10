package com.system.remedios.domain;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import com.system.remedios.dtos.MedicineDtoPost;
import com.system.remedios.dtos.MedicineDtoPut;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "via")
    @Enumerated(EnumType.STRING)
    private Via via;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "validity")
    private LocalDate validity;

    @Column(name = "laboratory")
    @Enumerated(EnumType.STRING)
    private Laboratory laboratory;

    @Column(name = "active")
    private Boolean ativo;

    public Medicine(MedicineDtoPost medicineDtoPost) {
        this.name = medicineDtoPost.getName();
        this.via = medicineDtoPost.getVia();
        this.quantity = medicineDtoPost.getQuantity();
        this.validity = medicineDtoPost.getValidity();
        this.laboratory = medicineDtoPost.getLaboratory();
        this.ativo = medicineDtoPost.getAtivo();
    }
    public Medicine(MedicineDtoPut medicineDtoPut) {
        this.id = medicineDtoPut.getId();
        this.name = medicineDtoPut.getName();
        this.via = medicineDtoPut.getVia();
        this.quantity = medicineDtoPut.getQuantity();
        this.validity = medicineDtoPut.getValidity();
        this.laboratory = medicineDtoPut.getLaboratory();
        this.ativo = medicineDtoPut.getAtivo();
    }


}


