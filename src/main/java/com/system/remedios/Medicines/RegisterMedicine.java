package com.system.remedios.Medicines;

import com.system.remedios.EnumsData.Laboratory;
import com.system.remedios.EnumsData.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RegisterMedicine(
        @NotBlank// not empty and not null
        String name,

        @Enumerated//Via is the Enum
        Via via,

        @NotBlank
        String lot,


        int quantity,

        @Future//cannot be before the actual date
        LocalDate validity,

        @Enumerated
        Laboratory laboratory
         ) {
}
