package com.system.remedios.service;

import com.system.remedios.Repository.MedicineRepository;
import com.system.remedios.domain.MedicineData;
import com.system.remedios.requests.MedicinePostRequestCreator;
import com.system.remedios.util.MedicineCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Log4j2
class MedicineServiceTest {
    @InjectMocks
    private MedicineService medicineService;
    @Mock
    private MedicineRepository medicineRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(medicineRepositoryMock.findAllByAtivoTrue())
                .thenReturn(List.of(MedicineCreator.createValidMedicineData()));

        BDDMockito.when(medicineRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(MedicineCreator.createMedicineDataToBeSaved()));

        BDDMockito.when(medicineRepositoryMock.save(ArgumentMatchers.any(MedicineData.class)))
                .thenReturn(MedicineCreator.createMedicineDataToBeSaved());


        BDDMockito.doNothing().when(medicineRepositoryMock).deleteById(ArgumentMatchers.any());


    }

    @Test
    @DisplayName("ListAll returns MedicineDate when is successful")
    void list_ReturnsListOfMedicineDataActive_WhenSuccessful() {
        MedicineData validMedicineData = MedicineCreator.createValidMedicineData();

        List<MedicineData> medicineResponse = medicineService.listAll();

        Assertions.assertThat(medicineResponse).isNotNull();

        Assertions.assertThat(medicineResponse).isNotEmpty().hasSize(1);

        Assertions.assertThat(medicineResponse.stream().toList().get(0).getName()).isEqualTo(validMedicineData.getName());

    }
    @Test
    @DisplayName("findByIdOrThrowBadRequestException return MedicineDate when is successful")
    void findById_ReturnMedicineDate_WhenSuccessful() {
        Long expectedId = MedicineCreator.createMedicineDataToBeSaved().getId();


        MedicineData byId = medicineService.findByIdOrThrowBadRequestException(1);


        Assertions.assertThat(byId).isNotNull();

        Assertions.assertThat(byId.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Save MedicineDate return when is successful")
    void SaveMedicineDate_ReturnWhenSuccessful() {
        MedicineData expectedMedicine = MedicineCreator.createMedicineDataToBeSaved();
        MedicineData save = medicineService.save(MedicinePostRequestCreator.medicinePostRequestBody());

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save).isEqualTo(expectedMedicine);



    }

    @Test
    @DisplayName("DeleteById MedicineDate when is successful")
    void deleteById_WhenSuccessful() {
        Assertions.assertThatCode(() -> medicineService.deleteByIdFull(1))
                .doesNotThrowAnyException();

    }


}