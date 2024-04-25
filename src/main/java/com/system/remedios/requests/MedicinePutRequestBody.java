package com.system.remedios.requests;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MedicinePutRequestBody {
    private Long id;

    @NotBlank(message = "the name cannot be empty")
    private String name;

    @Enumerated(EnumType.STRING)
    private Via via;


    private String lot;

    private int quantity;

    @Future
    private LocalDate validity;

    @Enumerated(EnumType.STRING)
    private Laboratory laboratory;

    private Boolean ativo;
}
