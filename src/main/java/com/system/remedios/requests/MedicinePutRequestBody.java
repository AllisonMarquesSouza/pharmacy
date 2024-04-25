package com.system.remedios.requests;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MedicinePutRequestBody {
    @NotEmpty(message = "the Id cannot be empty")
    private Long id;

    @NotEmpty(message = "the Via cannot be empty")
    private String name;

    @NotEmpty(message = "the Via cannot be empty")
    @Enumerated
    private Via via;


    private String lot;

    private int quantity;

    @NotEmpty(message = "the LocalDate cannot be empty")
    @Future
    private LocalDate validity;

    @NotEmpty(message = "the Laboratory cannot be empty")
    @Enumerated
    private Laboratory laboratory;
}
