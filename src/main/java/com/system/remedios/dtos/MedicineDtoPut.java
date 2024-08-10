package com.system.remedios.dtos;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MedicineDtoPut {
    @NotNull(message = "the id cannot be null")
    private Long id;

    @NotBlank(message = "the name cannot be empty")
    private String name;

    @NotNull(message = "the via cannot be null")
    @Enumerated(EnumType.STRING)
    private Via via;

    @NotNull(message = "the quantity cannot be null")
    private int quantity;

    @NotNull(message = "the validity cannot be null")
    @Future(message = "Date is at Future")
    private LocalDate validity;

    @NotNull(message = "the laboratory cannot be null")
    @Enumerated(EnumType.STRING)
    private Laboratory laboratory;

    @NotNull(message = "the ativo cannot be null")
    private Boolean ativo;
}
