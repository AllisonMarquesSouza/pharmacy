package com.system.remedios.Repository;

import com.system.remedios.domain.MedicineData;
import com.system.remedios.util.MedicineCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
@DataJpaTest
@ActiveProfiles("test")
@Log4j2
class MedicineRepositoryTest {
    @Autowired
    private MedicineRepository medicineRepository;

    @Test
    @DisplayName("Find by Medicine Data if ativo is true return successful ")
    void findByMedicineData_IfAtivoIsTrue_ReturnSuccessful() {
        MedicineData medicineToBeSaved = MedicineCreator.createValidMedicineData();

        MedicineData medicineToBeSaved2 = MedicineCreator.createValidMedicineData();
        medicineToBeSaved2.inactive();

        MedicineData medicineSaved = medicineRepository.save(medicineToBeSaved);

        MedicineData medicineSaved2 = medicineRepository.save(medicineToBeSaved2);

        List<MedicineData> allByAtivoTrue = medicineRepository.findAllByAtivoTrue();

        Assertions.assertThat(allByAtivoTrue).hasSize(1);

        Assertions.assertThat(allByAtivoTrue.get(0)).isEqualTo(medicineSaved);

        Assertions.assertThat(medicineSaved2.getAtivo()).isFalse();

        Assertions.assertThat(allByAtivoTrue.get(0)).isNotNull();

    }
}